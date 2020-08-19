package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.QuestionManager.QuestionManagerFactory;
import CSCI5308.GroupFormationTool.QuestionManager.TestQuestionManagerFactory;

@SuppressWarnings("deprecation")
class CriteriaTest {
	@BeforeAll
	public static void setup()
	{
		QuestionManagerFactory.setQuestionManagerFactory(new TestQuestionManagerFactory());
		SurveyFactory.setSurveyFactory(new TestSurveyFactory());
	}

	@Test
	public void getGroupSize() {
		ICriteria criteria = SurveyFactory.instance().makeCriteria();
		criteria.setGroupSize(7);
		Assert.isTrue(criteria.getGroupSize() == 7);
	}

	@Test
	public void setGroupSize() {
		ICriteria criteria = SurveyFactory.instance().makeCriteria();
		criteria.setGroupSize(7);
		Assert.isTrue(criteria.getGroupSize() == 7);
	}

	@Test
	public void getQuestionId() {
		ICriteria criteria = SurveyFactory.instance().makeCriteria();
		List<String> questions = new ArrayList<String>();
		questions.add("1");
		criteria.setQuestionId(questions);
		Assert.isTrue(criteria.getQuestionId() == questions);
	}

	@Test
	public void setQuestionId() {
		ICriteria criteria = SurveyFactory.instance().makeCriteria();
		List<String> questions = new ArrayList<String>();
		questions.add("1");
		criteria.setQuestionId(questions);
		Assert.isTrue(criteria.getQuestionId() == questions);
	}

	@Test
	public void getWeight() {
		ICriteria criteria = SurveyFactory.instance().makeCriteria();
		List<String> weight = new ArrayList<String>();
		weight.add("1");
		criteria.setWeight(weight);
		Assert.isTrue(criteria.getWeight() == weight);

	}

	@Test
	public void setWeight() {
		ICriteria criteria = SurveyFactory.instance().makeCriteria();
		List<String> weight = new ArrayList<String>();
		weight.add("1");
		criteria.setWeight(weight);
		Assert.isTrue(criteria.getWeight() == weight);

	}

	@Test
	public void getCriteria() {
		ICriteria criteria = SurveyFactory.instance().makeCriteria();
		List<String> groupCriteria = new ArrayList<String>();
		groupCriteria.add("Similar");
		criteria.setCriteria(groupCriteria);
		Assert.isTrue(criteria.getCriteria() == groupCriteria);
	}

	@Test
	public void setCriteria() {
		ICriteria criteria = SurveyFactory.instance().makeCriteria();
		List<String> groupCriteria = new ArrayList<String>();
		groupCriteria.add("Similar");
		criteria.setCriteria(groupCriteria);
		Assert.isTrue(criteria.getCriteria() == groupCriteria);
	}

	@Test
	public void formGroups() {
		ICriteria criteria = SurveyFactory.instance().makeCriteria();
		IResponsePersistence responseDB = TestSurveyFactory.instance().makeResponseDB();
		criteria.setGroupSize(1);
		HashMap<Integer, List<String>> groups = criteria.formGroups(1, responseDB);
		Assert.isTrue(groups.size() > 0);
	}

}