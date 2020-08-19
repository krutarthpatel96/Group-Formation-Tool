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
class RandomStrategyTest {
	@BeforeAll
	public static void setup()
	{
		QuestionManagerFactory.setQuestionManagerFactory(new TestQuestionManagerFactory());
		SurveyFactory.setSurveyFactory(new TestSurveyFactory());
	}

	@Test
	public void formGroups() {
		ICriteria criteria = SurveyFactory.instance().makeCriteria();
		IStrategy strategy = new RandomStrategy();
		List<IResponseWrapper> responses = new ArrayList<IResponseWrapper>();
		criteria.setGroupSize(1);
		HashMap<Integer, List<String>> groups = strategy.formGroups(criteria, responses);
		Assert.isTrue(groups.size() > 0);
	}

}