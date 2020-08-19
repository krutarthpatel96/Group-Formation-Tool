package CSCI5308.GroupFormationTool.Survey;

public class TestSurveyFactory extends DefaultSurveyFactory
{
	public TestSurveyFactory()
	{
		surveyDB = new SurveyDBMock();
		surveyInstructorRelationshipDB = new SurveyInstructorRelationshipDBMock();
		responseDB = new ResponseDBMock();
	}
}