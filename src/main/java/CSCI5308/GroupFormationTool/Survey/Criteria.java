package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Criteria implements ICriteria {

	private int groupSize;
	private List<String> questionId;
	private List<String> weight;
	private List<String> criteria;

	public Criteria() {
		questionId = new ArrayList<String>();
		weight = new ArrayList<String>();
		criteria = new ArrayList<String>();
	}

	@Override
	public int getGroupSize() {
		return groupSize;
	}

	@Override
	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	@Override
	public List<String> getQuestionId() {
		return questionId;
	}

	@Override
	public void setQuestionId(List<String> questionId) {
		this.questionId = questionId;
	}

	@Override
	public List<String> getWeight() {
		return weight;
	}

	@Override
	public void setWeight(List<String> weight) {
		this.weight = weight;
	}

	@Override
	public List<String> getCriteria() {
		return criteria;
	}

	@Override
	public void setCriteria(List<String> type) {
		this.criteria = type;
	}

	@Override
	public HashMap<Integer, List<String>> formGroups(long courseId, IResponsePersistence responseDB) {
		HashMap<Integer, List<String>> groups = new HashMap<Integer, List<String>>();
		IResponseWrapper studentResponse;
		List<IResponseWrapper> responses = new ArrayList<IResponseWrapper>();
		List<String> students = responseDB.getStudentRespondents(courseId);
		IStrategyContext strategyContext = SurveyFactory.instance().makeStrategyContext();
		IStrategy strategy = SurveyFactory.instance().makeGroupFormationStrategy();
		
		for (String bannerId : students) {
			List<Response> studentQuestionResponses = responseDB.getStudentResponses(bannerId, courseId);
			studentResponse = SurveyFactory.instance().makeResponseWrapper();
			studentResponse.setBannerId(bannerId);
			studentResponse.setCourseId(courseId);
			studentResponse.setResponses(studentQuestionResponses);
			responses.add(studentResponse);
		}

		strategyContext.setStrategy(strategy);
		groups = strategyContext.executeStrategy(this, responses);
		return groups;
	}
}
