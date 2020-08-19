package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.Timestamp;

public interface IQuestion {

	public void setDefaults();

	public Timestamp getTimestamp();

	public void setTimestamp(Timestamp timestamp);

	public long getId();

	public void setId(long id);

	public String getTitle();

	public void setTitle(String title);

	public String getText();

	public void setText(String text);

	public QuestionType getType();

	public void setType(QuestionType type);

	public boolean deleteQuestion(IQuestionPersistence questionDB, long questionID);

	public long createQuestion(IQuestionPersistence questionDB, String bannerID);

}
