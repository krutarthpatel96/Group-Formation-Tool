package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.Question;

public interface IResponse {

	public void setDefaults();

	public String getText();

	public void setText(String text);

	public Question getQuestion();

	public void setQuestion(Question question);

	public void createResponse(IQuestion question, String text);

}
