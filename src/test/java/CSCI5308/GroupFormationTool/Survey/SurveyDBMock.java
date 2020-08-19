package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionManagerFactory;

public class SurveyDBMock implements ISurveyPersistence {

	@Override
	public int checkSurveyStatus(long courseId) {
		if (courseId == -1) {
			return 0;
		}
		return 1;
	}

	@Override
	public boolean addQuestionToSurvey(long courseId, long questionId) {
		if (courseId == -1 || questionId == -1) {
			return false;
		}

		IQuestion question = QuestionManagerFactory.instance().makeQuestion();
		question.setId(questionId);
		return true;
	}

	@Override
	public boolean deleteQuestionFromSurvey(long courseId, long questionId) {
		if (courseId == -1 || questionId == -1) {
			return false;
		}

		return true;
	}

	@Override
	public boolean toggleSurvey(long courseId, int surveyStatus) {
		if (courseId == -1) {
			return false;
		}
		return true;
	}

}
