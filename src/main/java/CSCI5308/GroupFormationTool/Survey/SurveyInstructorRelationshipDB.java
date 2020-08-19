package CSCI5308.GroupFormationTool.Survey;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.ICallStoredProcedure;
import CSCI5308.GroupFormationTool.QuestionManager.IOptions;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.OptionValue;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionManagerFactory;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionType;

public class SurveyInstructorRelationshipDB implements ISurveyInstructorRelationshipPersistence {

	@Override
	public List<IQuestion> loadAvailabeInstructorSurveyQuestions(String bannerID, long courseID) {
		List<IQuestion> surveyQuestionList = new ArrayList<IQuestion>();
		ICallStoredProcedure proc = null;
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spFindInstructorAvailableQuestions(?,?)");
			proc.setParameter(1, bannerID);
			proc.setParameter(2, courseID);
			ResultSet results = proc.executeWithResults();
			IQuestion question;

			if (null != results) {
				while (results.next()) {
					long id = results.getLong(1);
					String title = results.getString(2);
					String text = results.getString(3);
					QuestionType type = QuestionType.valueOf(results.getString(4).toUpperCase());
					Timestamp timestamp = results.getTimestamp(5);

					question = QuestionManagerFactory.instance().makeQuestion();
					question.setId(id);
					question.setTitle(title);
					question.setText(text);
					question.setType(type);
					question.setTimestamp(timestamp);
					surveyQuestionList.add(question);
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return surveyQuestionList;
	}

	@Override
	public List<IQuestion> loadInstructorSurveyQuestions(long courseID) {
		List<IQuestion> surveyQuestionList = new ArrayList<IQuestion>();
		ICallStoredProcedure proc = null;
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spFindInstructorSurveyQuestionsByCourseID(?)");
			proc.setParameter(1, courseID);
			ResultSet results = proc.executeWithResults();
			IQuestion question;

			if (null != results) {
				while (results.next()) {
					long id = results.getLong(1);
					String title = results.getString(2);
					String text = results.getString(3);
					QuestionType type = QuestionType.valueOf(results.getString(4).toUpperCase());
					Timestamp timestamp = results.getTimestamp(5);

					question = QuestionManagerFactory.instance().makeQuestion();
					question.setId(id);
					question.setTitle(title);
					question.setText(text);
					question.setType(type);
					question.setTimestamp(timestamp);
					surveyQuestionList.add(question);
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return surveyQuestionList;
	}

	@Override
	public IOptions loadInstructorSurveyQuestionOptions(long questionID) {
		IOptions options = QuestionManagerFactory.instance().makeOptions();
		List<OptionValue> optionValues = new ArrayList<OptionValue>();
		ICallStoredProcedure proc = null;
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spFindInstructorSurveyQuestionOptions(?)");
			proc.setParameter(1, questionID);
			ResultSet results = proc.executeWithResults();

			if (null != results) {
				while (results.next()) {
					long id = results.getLong(1);
					String displayText = results.getString(2);
					String storedAs = results.getString(3);

					OptionValue value = (OptionValue) QuestionManagerFactory.instance().makeOptionValue();
					value.createOption(id, displayText, storedAs);
					optionValues.add(value);
				}
				options.setOptionList(optionValues);
			}
		} catch (SQLException e) {
			System.out.println("spFindInstructorSurveyQuestionOptions: " + e);
		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return options;
	}
}
