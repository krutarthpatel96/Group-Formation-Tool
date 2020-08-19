package CSCI5308.GroupFormationTool.Survey;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.QuestionManager.IOptions;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionManagerFactory;
import CSCI5308.GroupFormationTool.QuestionManager.TestQuestionManagerFactory;

class ResponseWrapperTest {
	@BeforeAll
	public static void setup()
	{
		QuestionManagerFactory.setQuestionManagerFactory(new TestQuestionManagerFactory());
		SurveyFactory.setSurveyFactory(new TestSurveyFactory());
	}

	@Test
	public void getCourseId() {
		IResponseWrapper responseWrapper = SurveyFactory.instance().makeResponseWrapper();
		responseWrapper.setCourseId(7);
		assertThat(responseWrapper.getCourseId() == 7).isTrue();
	}

	@Test
	public void setCourseId() {
		IResponseWrapper responseWrapper = SurveyFactory.instance().makeResponseWrapper();
		responseWrapper.setCourseId(7);
		assertThat(responseWrapper.getCourseId() == 7).isTrue();
	}

	@Test
	public void getBannerId() {
		IResponseWrapper responseWrapper = SurveyFactory.instance().makeResponseWrapper();
		responseWrapper.setBannerId("B-999999");
		assertThat(responseWrapper.getBannerId().equals("B-999999")).isTrue();
	}

	@Test
	public void setBannerId() {
		IResponseWrapper responseWrapper = SurveyFactory.instance().makeResponseWrapper();
		responseWrapper.setBannerId("B-999999");
		assertThat(responseWrapper.getBannerId().equals("B-999999")).isTrue();
	}

	@Test
	public void getResponses() {
		IResponseWrapper responseWrapper = SurveyFactory.instance().makeResponseWrapper();
		IResponse responses = SurveyFactory.instance().makeResponse();
		responses.setDefaults();
		List<Response> responseList = new ArrayList<Response>();
		responseWrapper.setResponses(responseList);
		assertThat(responseWrapper.getResponses() == responseList).isTrue();
	}

	@Test
	public void setResponses() {
		IResponseWrapper responseWrapper = SurveyFactory.instance().makeResponseWrapper();
		IResponse responses = SurveyFactory.instance().makeResponse();
		responses.setDefaults();
		List<Response> responseList = new ArrayList<Response>();
		responseWrapper.setResponses(responseList);
		assertThat(responseWrapper.getResponses() == responseList).isTrue();
	}

	@Test
	public void addResponses() {
		IResponseWrapper responseWrapper = SurveyFactory.instance().makeResponseWrapper();
		IQuestionBank questionBank = SurveyFactory.instance().makeQuestionBank();
		IQuestion question = QuestionManagerFactory.instance().makeQuestion();
		IOptions option = QuestionManagerFactory.instance().makeOptions();
		questionBank.createQuestion(question, option);
		responseWrapper.addResponses("B-999999", 7, questionBank);
		assertThat(responseWrapper.getBannerId().equals("B-999999")).isTrue();
		assertThat(responseWrapper.getCourseId() == 7).isTrue();
		assertThat(responseWrapper.getResponses().size() > 0).isTrue();
	}

	@Test
	public void saveResponse() {
		IResponseWrapper responseWrapper = SurveyFactory.instance().makeResponseWrapper();
		IResponsePersistence responseDB = TestSurveyFactory.instance().makeResponseDB();
		assertThat(responseWrapper.saveResponse(responseDB)).isTrue();
	}

}
