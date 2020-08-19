package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.ICallStoredProcedure;

public class QuestionDB implements IQuestionPersistence {
	@Override
	public List<IQuestion> loadQuestionsSortedByTitle(String bannerID) {
		List<IQuestion> questionList = new ArrayList<IQuestion>();
		ICallStoredProcedure proc = null;
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spFindSortedQuestionsByTitle(?)");
			proc.setParameter(1, bannerID);
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
					questionList.add(question);
				}
			}
		} catch (SQLException e) {
			Logger logger = LoggerFactory.getLogger(QuestionDB.class);
			logger.error("Failed to load questions sorted by title for bannerID = " + bannerID + ":" + e.getMessage(), e);
		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return questionList;
	}

	@Override
	public List<IQuestion> loadSortedQuestionsSortedByDate(String bannerID) {
		List<IQuestion> questionList = new ArrayList<IQuestion>();
		ICallStoredProcedure proc = null;
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spFindSortedQuestionsByDate(?)");
			proc.setParameter(1, bannerID);
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
					questionList.add(question);
				}
			}
		} catch (SQLException e) {
			Logger logger = LoggerFactory.getLogger(QuestionDB.class);
			logger.error("Failed to load questions sorted by date for bannerID = " + bannerID + ":" + e.getMessage(), e);
		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return questionList;
	}

	@Override
	public boolean deleteQuestionByQuestionId(long questionID) {
		ICallStoredProcedure proc = null;
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spDeleteQuestionsByQuestionID(?)");
			proc.setParameter(1, questionID);
			proc.execute();
		} catch (SQLException e) {
			Logger logger = LoggerFactory.getLogger(QuestionDB.class);
			logger.error("Failed to delete question with ID = " + questionID + ":" + e.getMessage(), e);
			return false;
		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return true;
	}

	@Override
	public long createQuestion(IQuestion question, String bannerID) {
		long id = -1;
		ICallStoredProcedure proc = null;
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spCreateQuestion(?,?,?,?)");
			proc.setParameter(1, question.getTitle());
			proc.setParameter(2, question.getText());
			proc.setParameter(3, question.getType().toString());
			proc.setParameter(4, bannerID);
			ResultSet results = proc.executeWithResults();

			if (null != results) {
				while (results.next()) {
					id = results.getLong(1);
				}
			}
		} catch (SQLException e) {
			Logger logger = LoggerFactory.getLogger(QuestionDB.class);
			logger.error("Failed to create question for bannerID = " + bannerID + ":" + e.getMessage(), e);
		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return id;
	}

	@Override
	public boolean createQuestionOption(IOptionValue option, int order, long questionID) {
		ICallStoredProcedure proc = null;
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spCreateOptions(?,?,?,?)");
			proc.setParameter(1, questionID);
			proc.setParameter(2, option.getText());
			proc.setParameter(3, option.getStoredAs());
			proc.setParameter(4, order);
			proc.execute();

		} catch (SQLException e) {
			Logger logger = LoggerFactory.getLogger(QuestionDB.class);
			logger.error("Failed to create question option for question ID = " + questionID + ":" + e.getMessage(), e);
			return false;
		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return true;
	}

}
