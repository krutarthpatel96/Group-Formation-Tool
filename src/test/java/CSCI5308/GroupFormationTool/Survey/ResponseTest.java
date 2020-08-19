package CSCI5308.GroupFormationTool.Survey;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.Question;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionManagerFactory;
import CSCI5308.GroupFormationTool.QuestionManager.TestQuestionManagerFactory;

class ResponseTest {
	@BeforeAll
	public static void setup()
	{
		QuestionManagerFactory.setQuestionManagerFactory(new TestQuestionManagerFactory());
		SurveyFactory.setSurveyFactory(new TestSurveyFactory());
	}

	@Test
	public void setDefaults() {
		IResponse response = SurveyFactory.instance().makeResponse();
		response.setDefaults();
		assertThat(response.getText().equals("")).isTrue();
		assertThat(response.getQuestion() == null).isTrue();
	}

	@Test
	public void getText() {
		IResponse response = SurveyFactory.instance().makeResponse();
		response.setText("Test Text");
		assertThat(response.getText().equals("Test Text")).isTrue();
	}

	@Test
	public void setText() {
		IResponse response = SurveyFactory.instance().makeResponse();
		response.setText("Test Text");
		assertThat(response.getText().equals("Test Text")).isTrue();
	}

	@Test
	public void getQuestion() {
		IResponse response = SurveyFactory.instance().makeResponse();
		IQuestion question = QuestionManagerFactory.instance().makeQuestion();
		response.setQuestion((Question) question);
		assertThat(response.getQuestion() == question).isTrue();
	}

	@Test
	public void setQuestion() {
		IResponse response = SurveyFactory.instance().makeResponse();
		IQuestion question = QuestionManagerFactory.instance().makeQuestion();
		response.setQuestion((Question) question);
		assertThat(response.getQuestion() == question).isTrue();
	}

	@Test
	public void createResponse() {
		IResponse response = SurveyFactory.instance().makeResponse();
		IQuestion question = QuestionManagerFactory.instance().makeQuestion();
		response.createResponse(question, "Test Text");
		assertThat(response.getText().equals("Test Text")).isTrue();
		assertThat(response.getQuestion() == question).isTrue();
	}

}
