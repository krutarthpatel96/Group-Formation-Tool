package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

public abstract class SurveyFactory 
{

	private static SurveyFactory uniqueInstance;


	protected SurveyFactory()
	{
	}

	public static SurveyFactory instance()
	{
		return uniqueInstance;
	}
	
	public static void setSurveyFactory(SurveyFactory factory)
	{
		uniqueInstance = factory;
	}

	public abstract IQuestionBank makeQuestionBank();
	public abstract IResponse makeResponse();
	public abstract IResponse makeResponse(IQuestion question);
	public abstract IResponseWrapper makeResponseWrapper();
	public abstract ISurveyPersistence makeSurveyDB();
	public abstract ISurveyInstructorRelationshipPersistence makeSurveyInstructorRelationshipDB();
	public abstract IResponsePersistence makeResponseDB();
	public abstract ICriteria makeCriteria();
	public abstract IStrategyContext makeStrategyContext();
	public abstract IStrategy makeGroupFormationStrategy();
}