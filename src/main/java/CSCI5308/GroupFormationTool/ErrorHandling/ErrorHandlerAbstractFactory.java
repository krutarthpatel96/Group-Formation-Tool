package CSCI5308.GroupFormationTool.ErrorHandling;

public class ErrorHandlerAbstractFactory {

	private static ErrorHandlerAbstractFactory errorHandlerAbstractFactory;
	private static SurveyErrorNotification surveyErrorNotification;
	
	private ErrorHandlerAbstractFactory() {
		surveyErrorNotification = new SurveyErrorNotification();
	}
	
	public static ErrorHandlerAbstractFactory instance() {
		if(errorHandlerAbstractFactory == null) {
			errorHandlerAbstractFactory = new ErrorHandlerAbstractFactory();
		}
		return errorHandlerAbstractFactory;
	}
	
	public ErrorNotificationHandler makeSurveyErrorNotificationHandler() {
		return surveyErrorNotification;
	}
}
