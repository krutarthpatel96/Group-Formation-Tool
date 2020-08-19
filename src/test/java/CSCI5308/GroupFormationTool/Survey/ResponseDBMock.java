package CSCI5308.GroupFormationTool.Survey;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResponseDBMock implements IResponsePersistence {

	@Override
	public boolean addSurveyResponse(String bannerId, long courseId, IResponse response) throws SQLException {
		if (courseId == -1) {
			return false;
		}
		IResponse surveyResponse = SurveyFactory.instance().makeResponse();
		surveyResponse.setDefaults();
		return true;
	}

	@Override
	public boolean deleteSurveyResponse(String bannerId, long courseId) {
		if (courseId == -1) {
			return false;
		}
		return true;
	}

	@Override
	public List<String> getStudentRespondents(long courseId) {
		List<String> students = new ArrayList<String>();
		if(courseId == -1) {
			return null;
		}
		students.add("B-111111");
		return students;
	}

	@Override
	public List<Response> getStudentResponses(String bannerId, long courseId) {
		List<Response> responses = new ArrayList<Response>();
		IResponse response = SurveyFactory.instance().makeResponse();
		response.setDefaults();
		responses.add((Response) response);
		return responses;
	}

}
