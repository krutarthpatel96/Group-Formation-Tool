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

class QuestionBankTest {
	@BeforeAll
	public static void setup()
	{
		QuestionManagerFactory.setQuestionManagerFactory(new TestQuestionManagerFactory());
		SurveyFactory.setSurveyFactory(new TestSurveyFactory());
	}

	@Test
	public void getQuestion() {
		IQuestionBank questionBank = SurveyFactory.instance().makeQuestionBank();
		assertThat(questionBank.getQuestion().size() == 0).isTrue();

		List<IQuestion> questionList = new ArrayList<IQuestion>();
		IQuestion question = QuestionManagerFactory.instance().makeQuestion();
		question.setDefaults();
		questionList.add(question);
		questionBank.setQuestion(questionList);
		assertThat(questionBank.getQuestion() == questionList).isTrue();
	}

	@Test
	public void setQuestion() {
		IQuestionBank questionBank = SurveyFactory.instance().makeQuestionBank();
		assertThat(questionBank.getQuestion().size() == 0).isTrue();

		List<IQuestion> questionList = new ArrayList<IQuestion>();
		IQuestion question = QuestionManagerFactory.instance().makeQuestion();
		question.setDefaults();
		questionList.add(question);
		questionBank.setQuestion(questionList);
		assertThat(questionBank.getQuestion() == questionList).isTrue();
	}

	@Test
	public void getOption() {
		IQuestionBank questionBank = SurveyFactory.instance().makeQuestionBank();
		assertThat(questionBank.getOption().size() == 0).isTrue();
		List<IOptions> optionList = new ArrayList<IOptions>();
		IOptions options = QuestionManagerFactory.instance().makeOptions();
		options.setDefault();
		optionList.add(options);
		questionBank.setOption(optionList);
		assertThat(questionBank.getOption() == optionList).isTrue();
	}

	@Test
	public void setOption() {
		IQuestionBank questionBank = SurveyFactory.instance().makeQuestionBank();
		assertThat(questionBank.getOption().size() == 0).isTrue();
		List<IOptions> optionList = new ArrayList<IOptions>();
		IOptions options = QuestionManagerFactory.instance().makeOptions();
		options.setDefault();
		optionList.add(options);
		questionBank.setOption(optionList);
		assertThat(questionBank.getOption() == optionList).isTrue();
	}

	@Test
	public void createQuestion() {
		IQuestionBank questionBank = SurveyFactory.instance().makeQuestionBank();
		assertThat(questionBank.getOption().size() == 0).isTrue();
		assertThat(questionBank.getQuestion().size() == 0).isTrue();

		IQuestion question = QuestionManagerFactory.instance().makeQuestion();
		IOptions options = QuestionManagerFactory.instance().makeOptions();
		question.setDefaults();
		options.setDefault();
		questionBank.createQuestion(question, options);
		assertThat(questionBank.getQuestion().size() > 0).isTrue();
		assertThat(questionBank.getOption().size() > 0).isTrue();
	}

	@Test
	public void loadAllSurveyQuestions() {
		ISurveyInstructorRelationshipPersistence surveyInstructorRelationshipDB = TestSurveyFactory.instance()
				.makeSurveyInstructorRelationshipDB();
		IQuestionBank questionBank = SurveyFactory.instance().makeQuestionBank();
		assertThat(questionBank.getOption().size() == 0).isTrue();
		assertThat(questionBank.getQuestion().size() == 0).isTrue();

		questionBank.loadAllSurveyQuestions(surveyInstructorRelationshipDB, "B-000000", 1);
		assertThat(questionBank.getOption().size() > 0).isTrue();
		assertThat(questionBank.getQuestion().size() > 0).isTrue();

	}

}