package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

public class DefaultSurveyFactory extends SurveyFactory
{
	protected ISurveyPersistence surveyDB;
	protected ISurveyInstructorRelationshipPersistence surveyInstructorRelationshipDB;
	protected IResponsePersistence responseDB;

	public DefaultSurveyFactory()
	{
		super();
		surveyDB = new SurveyDB();
		surveyInstructorRelationshipDB = new SurveyInstructorRelationshipDB();
		responseDB = new ResponseDB();
	}

	public IQuestionBank makeQuestionBank()
	{
		return new QuestionBank();
	}

	public IResponse makeResponse()
	{
		return new Response();
	}

	public IResponse makeResponse(IQuestion question)
	{
		return new Response(question);
	}

	public IResponseWrapper makeResponseWrapper()
	{
		return new ResponseWrapper();
	}

	public ISurveyPersistence makeSurveyDB()
	{
		return surveyDB;
	}

	public ISurveyInstructorRelationshipPersistence makeSurveyInstructorRelationshipDB()
	{
		return surveyInstructorRelationshipDB;
	}

	public IResponsePersistence makeResponseDB()
	{
		return responseDB;
	}

	public ICriteria makeCriteria()
	{
		return new Criteria();
	}
	
	public IStrategyContext makeStrategyContext()
	{
		return new StrategyContext();
	}
	
	public IStrategy makeGroupFormationStrategy()
	{
		return new RandomStrategy();
	}
}
