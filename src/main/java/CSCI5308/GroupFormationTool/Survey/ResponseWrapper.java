package CSCI5308.GroupFormationTool.Survey;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.ErrorHandling.ErrorHandlerAbstractFactory;
import CSCI5308.GroupFormationTool.ErrorHandling.ErrorNotificationHandler;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

public class ResponseWrapper implements IResponseWrapper {

	long courseId;
	String bannerId;
	List<Response> responses;

	public ResponseWrapper() {
		this.courseId = -1;
		this.bannerId = "";
		responses = new ArrayList<Response>();
	}

	@Override
	public long getCourseId() {
		return courseId;
	}

	@Override
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	@Override
	public String getBannerId() {
		return bannerId;
	}

	@Override
	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	@Override
	public List<Response> getResponses() {
		return responses;
	}

	@Override
	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	@Override
	public void addResponses(String bannerId, long courseId, IQuestionBank questions) {
		for (int i = 0; i < questions.getQuestion().size(); i++) {
			IQuestion question = questions.getQuestion().get(i);
			this.bannerId = bannerId;
			this.courseId = courseId;
			this.responses.add((Response) SurveyFactory.instance().makeResponse(question));
		}
	}

	@Override
	public boolean saveResponse(IResponsePersistence responseDB) {
		try {
			for (IResponse response : this.responses) {
				responseDB.addSurveyResponse(this.bannerId, this.courseId, response);
			}
		} catch (SQLException e) {

			ErrorNotificationHandler errorNotificationHandler = ErrorHandlerAbstractFactory.instance()
					.makeSurveyErrorNotificationHandler();
			
			//Used System.env just for an example - will be replaced by user's email address
			errorNotificationHandler.sendNotification(String.valueOf(System.getenv("email")));
			responseDB.deleteSurveyResponse(this.bannerId, this.courseId);
			return false;
		}
		return true;
	}
}