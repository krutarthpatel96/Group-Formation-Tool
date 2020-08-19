package CSCI5308.GroupFormationTool.QuestionManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

@SuppressWarnings("deprecation")
class OptionValueTest {
	@BeforeAll
	public static void setup() {
		QuestionManagerFactory.setQuestionManagerFactory(new TestQuestionManagerFactory());
	}

	@Test
	public void ConstructorTests() {
		IOptionValue value = QuestionManagerFactory.instance().makeOptionValue();
		Assert.isTrue(isStringEmpty(value.getText()));
		Assert.isTrue(isStringEmpty(value.getStoredAs()));
	}

	@Test
	public void getText() {
		IOptionValue value = QuestionManagerFactory.instance().makeOptionValue();
		value.setText("Test Text");
		Assert.isTrue(value.getText().equals("Test Text"));
	}

	@Test
	public void setText() {
		IOptionValue value = QuestionManagerFactory.instance().makeOptionValue();
		value.setText("Test Text");
		Assert.isTrue(value.getText().equals("Test Text"));
	}

	@Test
	public void getStoredAs() {
		IOptionValue value = QuestionManagerFactory.instance().makeOptionValue();
		value.setStoredAs("Test");
		Assert.isTrue(value.getStoredAs().equals("Test"));
	}

	@Test
	public void setStoredAs() {
		IOptionValue value = QuestionManagerFactory.instance().makeOptionValue();
		value.setStoredAs("Test");
		Assert.isTrue(value.getStoredAs().equals("Test"));
	}

	@Test
	public void createOption() {
		IOptionValue value = QuestionManagerFactory.instance().makeOptionValue();
		value.createOption("Test Text", "1");
		Assert.isTrue(value.getText().equals("Test Text"));
		Assert.isTrue(value.getStoredAs().equals("1"));
	}

	public boolean isStringEmpty(String s) {
		return s.replaceAll(" ", "").length() == 0;
	}

}
