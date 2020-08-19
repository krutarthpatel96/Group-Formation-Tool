package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.QuestionManager.IOptions;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

public class QuestionBank implements IQuestionBank {

	private List<IQuestion> question;
	private List<IOptions> option;
	
	public QuestionBank() {
		setDefaults();
	}
	
	@Override
	public void setDefaults() {
		question = new ArrayList<IQuestion>();
		option = new ArrayList<IOptions>();
	}
	
	@Override
	public List<IQuestion> getQuestion() {
		return question;
	}

	@Override
	public void setQuestion(List<IQuestion> question) {
		this.question = question;
	}

	@Override
	public List<IOptions> getOption() {
		return option;
	}

	@Override
	public void setOption(List<IOptions> option) {
		this.option = option;
	}

	@Override
	public void createQuestion(IQuestion question, IOptions option) {
		this.question.add(question);
		this.option.add(option);
	}

	@Override
	public void loadAllSurveyQuestions(ISurveyInstructorRelationshipPersistence surveryInstructorRelationshipDB,
			String bannerId, long courseId) {
		IOptions options;
		List<IQuestion> questions = surveryInstructorRelationshipDB.loadInstructorSurveyQuestions(courseId);

		for (IQuestion q : questions) {
			options = surveryInstructorRelationshipDB.loadInstructorSurveyQuestionOptions(q.getId());
			this.createQuestion(q, options);
		}
	}

}