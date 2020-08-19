package CSCI5308.GroupFormationTool.Survey;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.ICallStoredProcedure;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.Question;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionManagerFactory;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionType;

public class ResponseDB implements IResponsePersistence {

	@Override
	public boolean addSurveyResponse(String bannerId, long courseId, IResponse response) throws SQLException {
		ICallStoredProcedure proc = null;
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spAddResponseToSurvey(?,?,?,?)");
			proc.setParameter(1, courseId);
			proc.setParameter(2, response.getQuestion().getId());
			proc.setParameter(3, bannerId);
			proc.setParameter(4, response.getText());
			proc.execute();

		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return true;
	}

	@Override
	public boolean deleteSurveyResponse(String bannerId, long courseId) {
		ICallStoredProcedure proc = null;
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spDeleteSurveyResponse(?,?)");
			proc.setParameter(1, courseId);
			proc.setParameter(2, bannerId);
			proc.execute();

		} catch (SQLException e) {
			System.out.println("spDeleteSurveyResponse: " + e);
			return false;
		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return true;
	}

	@Override
	public List<String> getStudentRespondents(long courseId) {
		List<String> banners = new ArrayList<String>();
		ICallStoredProcedure proc = null;
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spFetchStudentRespondents(?)");
			proc.setParameter(1, courseId);
			ResultSet results = proc.executeWithResults();

			if (null != results) {
				while (results.next()) {
					String bannerId = results.getString(1);
					banners.add(bannerId);
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return banners;
	}

	@Override
	public List<Response> getStudentResponses(String bannerId, long courseId) {
		List<Response> responses = new ArrayList<Response>();
		IQuestion question;
		Response studentResponse;
		ICallStoredProcedure proc = null;
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spFetchResponsesByBanner(?,?)");
			proc.setParameter(1, bannerId);
			proc.setParameter(2, courseId);
			ResultSet results = proc.executeWithResults();

			if (null != results) {
				while (results.next()) {
					long id = results.getLong(1);
					String title = results.getString(2);
					String text = results.getString(3);
					QuestionType type = QuestionType.valueOf(results.getString(4).toUpperCase());
					String response = results.getString(5);

					question = QuestionManagerFactory.instance().makeQuestion();
					question.setId(id);
					question.setTitle(title);
					question.setText(text);
					question.setType(type);

					studentResponse = (Response) SurveyFactory.instance().makeResponse();
					studentResponse.setQuestion((Question) question);
					studentResponse.setText(response);
					responses.add(studentResponse);
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return responses;
	}
}
