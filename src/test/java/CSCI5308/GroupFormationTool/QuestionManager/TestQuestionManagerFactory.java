package CSCI5308.GroupFormationTool.QuestionManager;

public class TestQuestionManagerFactory extends DefaultQuestionManagerFactory
{
	public TestQuestionManagerFactory()
	{
		questionDB = new QuestionDBMock();
	}
}
