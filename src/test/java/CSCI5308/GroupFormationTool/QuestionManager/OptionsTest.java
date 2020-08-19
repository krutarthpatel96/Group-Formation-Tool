package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

@SuppressWarnings("deprecation")
class OptionsTest {
	@BeforeAll
	public static void setup() {
		QuestionManagerFactory.setQuestionManagerFactory(new TestQuestionManagerFactory());
	}

	@Test
	public void ConstructorTests() {
		IOptions options = QuestionManagerFactory.instance().makeOptions();
		Assert.isTrue(options.getOptionList().size() == 0);
	}

	@Test
	public void getOptionList() {
		IOptions options = QuestionManagerFactory.instance().makeOptions();
		List<OptionValue> list = new ArrayList<OptionValue>();
		list.add(new OptionValue("test", "test"));
		options.setOptionList(list);
		Assert.isTrue(options.getOptionList() == list);

	}

	@Test
	public void setOptionList() {
		IOptions options = QuestionManagerFactory.instance().makeOptions();
		List<OptionValue> list = new ArrayList<OptionValue>();
		list.add(new OptionValue("test", "test"));
		options.setOptionList(list);
		Assert.isTrue(options.getOptionList() == list);
	}

	@Test
	public void addOption() {
		IOptions options = QuestionManagerFactory.instance().makeOptions();
		options.addOption();
		Assert.isTrue(options.getOptionList().size() > 0);
	}

	@Test
	public void saveOptions() {

		IOptions options = QuestionManagerFactory.instance().makeOptions();
		IQuestionPersistence questionDB = QuestionManagerFactory.instance().makeQuestionDB();
		List<OptionValue> list = new ArrayList<OptionValue>();
		list.add(new OptionValue("test", "test"));
		options.setOptionList(list);
		options.saveOptions(questionDB, 1);
		Assert.isTrue(options.getOptionList().size() > 0);
	}
}