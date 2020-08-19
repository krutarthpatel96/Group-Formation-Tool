package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.QuestionManager.IOptions;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionManagerFactory;

public class SurveyInstructorRelationshipDBMock implements ISurveyInstructorRelationshipPersistence {

	private List<IQuestion> questionList;

	public SurveyInstructorRelationshipDBMock() {
		IQuestion question = QuestionManagerFactory.instance().makeQuestion();
		question.setDefaults();
		questionList = new ArrayList<IQuestion>();
		questionList.add(question);
	}

	@Override
	public List<IQuestion> loadAvailabeInstructorSurveyQuestions(String bannerID, long courseID) {
		return questionList;
	}

	@Override
	public List<IQuestion> loadInstructorSurveyQuestions(long courseID) {
		return questionList;
	}

	@Override
	public IOptions loadInstructorSurveyQuestionOptions(long questionID) {
		IOptions options = QuestionManagerFactory.instance().makeOptions();
		options.setDefault();
		return options;
	}

}
