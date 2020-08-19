package CSCI5308.GroupFormationTool.Survey;

import java.sql.SQLException;
import java.util.List;

public interface IResponsePersistence {

	public boolean addSurveyResponse(String bannerId, long courseId, IResponse response) throws SQLException;
	
	public boolean deleteSurveyResponse(String bannerId, long courseId);

	public List<String> getStudentRespondents(long courseId);

	public List<Response> getStudentResponses(String bannerId, long courseId);

}
