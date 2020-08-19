package CSCI5308.GroupFormationTool.Survey;

import java.sql.ResultSet;
import java.sql.SQLException;

import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.ICallStoredProcedure;

public class SurveyDB implements ISurveyPersistence {

	@Override
	public int checkSurveyStatus(long courseId) {
		ICallStoredProcedure proc = null;
		int status = -1;
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spCheckSurveyStatus(?)");
			proc.setParameter(1, courseId);
			ResultSet results = proc.executeWithResults();

			if (null != results) {
				while (results.next()) {
					status = results.getInt(1);
				}
			}
		} catch (SQLException e) {
			return status;
		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}

		return status;
	}

	@Override
	public boolean addQuestionToSurvey(long courseId, long questionId) {
		ICallStoredProcedure proc = null;
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spAddQuestionToSurvey(?,?)");
			proc.setParameter(1, courseId);
			proc.setParameter(2, questionId);
			proc.execute();
		} catch (SQLException e) {
			System.out.println("spAddQuestionToSurvey: " + e);
			return false;
		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return true;
	}

	@Override
	public boolean deleteQuestionFromSurvey(long courseId, long questionId) {
		ICallStoredProcedure proc = null;
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spDeleteQuestionFromSurvey(?,?)");
			proc.setParameter(1, courseId);
			proc.setParameter(2, questionId);
			proc.execute();
		} catch (SQLException e) {
			System.out.println("spDeleteQuestionFromSurvey: " + e);
			return false;
		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return true;
	}

	@Override
	public boolean toggleSurvey(long courseId, int status) {
		ICallStoredProcedure proc = null;
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spToggleSurvey(?,?)");
			proc.setParameter(1, courseId);
			proc.setParameter(2, status);
			proc.execute();
		} catch (SQLException e) {
			System.out.println("spToggleSurvey: " + e);
			return false;
		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return true;
	}

}
