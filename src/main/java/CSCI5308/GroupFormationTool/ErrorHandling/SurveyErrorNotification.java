package CSCI5308.GroupFormationTool.ErrorHandling;

import javax.mail.MessagingException;

public class SurveyErrorNotification extends ErrorNotificationHandler {

	@Override
	protected void setMessage() throws MessagingException {
		String message = System.getenv("surveyerror");
		this.msg.setText(message);
	}

}
