package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.Question;

public class Response implements IResponse {

	Question question;
	String text;

	public Response() {
		setDefaults();
	}

	public Response(IQuestion question) {
		setDefaults();
		this.question = (Question) question;
	}

	@Override
	public void setDefaults() {
		this.text = "";
		this.question = null;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public Question getQuestion() {
		return question;
	}

	@Override
	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public void createResponse(IQuestion question, String text) {
		this.question = (Question) question;
		this.text = text;
	}
}