package CSCI5308.GroupFormationTool.QuestionManager;

public abstract class QuestionManagerFactory 
{
	private static QuestionManagerFactory uniqueInstance = null;

	protected QuestionManagerFactory()
	{
	}

	public static QuestionManagerFactory instance()
	{
		return uniqueInstance;
	}
	
	public static void setQuestionManagerFactory(QuestionManagerFactory factory)
	{
		uniqueInstance = factory;
	}

	public abstract IQuestion makeQuestion();
	public abstract IOptions makeOptions();
	public abstract IOptionValue makeOptionValue();
	public abstract IOptionValue makeOptionValue(String text, String storedAs);
	public abstract IQuestionPersistence makeQuestionDB();
}
