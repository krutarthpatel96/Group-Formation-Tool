package CSCI5308.GroupFormationTool.Survey;

import java.util.List;

import CSCI5308.GroupFormationTool.QuestionManager.IOptions;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

public interface ISurveyInstructorRelationshipPersistence {
	public List<IQuestion> loadAvailabeInstructorSurveyQuestions(String bannerId, long courseId);

	public List<IQuestion> loadInstructorSurveyQuestions(long courseId);

	public IOptions loadInstructorSurveyQuestionOptions(long questionId);
}
