package CSCI5308.GroupFormationTool.Survey;

public interface ISurveyPersistence {

	public int checkSurveyStatus(long courseId);

	public boolean addQuestionToSurvey(long courseId, long questionId);

	public boolean deleteQuestionFromSurvey(long courseId, long questionId);

	public boolean toggleSurvey(long courseId, int surveyStatus);

}
