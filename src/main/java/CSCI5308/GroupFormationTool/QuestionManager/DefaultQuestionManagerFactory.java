package CSCI5308.GroupFormationTool.QuestionManager;

public class DefaultQuestionManagerFactory extends QuestionManagerFactory
{

	protected IQuestionPersistence questionDB;

	public DefaultQuestionManagerFactory()
	{
		super();
		questionDB = new QuestionDB();
	}

	public IQuestion makeQuestion()
	{
		return new Question();
	}

	public IOptions makeOptions()
	{
		return new Options();
	}

	public IOptionValue makeOptionValue()
	{
		return new OptionValue();
	}

	public IOptionValue makeOptionValue(String text, String storedAs)
	{
		return new OptionValue(text, storedAs);
	}

	public IQuestionPersistence makeQuestionDB()
	{
		return questionDB;
	}
}
