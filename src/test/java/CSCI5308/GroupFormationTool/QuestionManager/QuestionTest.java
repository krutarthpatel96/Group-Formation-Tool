package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

@SuppressWarnings("deprecation")
public class QuestionTest {
	@BeforeAll
	public static void setup() {
		QuestionManagerFactory.setQuestionManagerFactory(new TestQuestionManagerFactory());
	}

	@Test
	public void ConstructorTests() {
		IQuestion q = QuestionManagerFactory.instance().makeQuestion();
		Assert.isTrue(q.getTitle().isEmpty());
		Assert.isTrue(q.getText().isEmpty());
		Assert.isNull(q.getType());
		Assert.isNull(q.getTimestamp());
	}

	@Test
	public void getTimestamp() {
		IQuestion q = QuestionManagerFactory.instance().makeQuestion();
		Timestamp time = Timestamp.valueOf("2020-06-16 00:00:00");
		q.setTimestamp(time);
		Assert.isTrue(time == q.getTimestamp());
	}

	@Test
	public void setTimestamp() {
		IQuestion q = QuestionManagerFactory.instance().makeQuestion();
		Timestamp time = Timestamp.valueOf("2020-06-16 00:00:00");
		q.setTimestamp(time);
		Assert.isTrue(time == q.getTimestamp());
	}

	@Test
	public void getId() {
		IQuestion q = QuestionManagerFactory.instance().makeQuestion();
		q.setId(7);
		Assert.isTrue(q.getId() == 7);
	}

	@Test
	public void setId() {
		IQuestion q = QuestionManagerFactory.instance().makeQuestion();
		q.setId(7);
		Assert.isTrue(q.getId() == 7);
	}

	@Test
	public void getTitle() {
		IQuestion q = QuestionManagerFactory.instance().makeQuestion();
		q.setTitle("Test title");
		Assert.isTrue(q.getTitle().equals("Test title"));
	}

	@Test
	public void setTitle() {
		IQuestion q = QuestionManagerFactory.instance().makeQuestion();
		q.setTitle("Test title");
		Assert.isTrue(q.getTitle().equals("Test title"));
	}

	@Test
	public void getText() {
		IQuestion q = QuestionManagerFactory.instance().makeQuestion();
		q.setText("Test text");
		Assert.isTrue(q.getText().equals("Test text"));
	}

	@Test
	public void setText() {
		IQuestion q = QuestionManagerFactory.instance().makeQuestion();
		q.setText("Test text");
		Assert.isTrue(q.getText().equals("Test text"));
	}

	@Test
	public void getType() {
		IQuestion q = QuestionManagerFactory.instance().makeQuestion();
		q.setType(QuestionType.TEXT);
		Assert.isTrue(q.getType() == QuestionType.TEXT);
	}

	@Test
	public void setType() {
		IQuestion q = QuestionManagerFactory.instance().makeQuestion();
		q.setType(QuestionType.TEXT);
		Assert.isTrue(q.getType() == QuestionType.TEXT);
	}

	@Test
	public void deleteQuestion() {
		IQuestion q = QuestionManagerFactory.instance().makeQuestion();
		IQuestionPersistence questionDB = QuestionManagerFactory.instance().makeQuestionDB();
		q.setDefaults();
		boolean status = questionDB.deleteQuestionByQuestionId(q.getId());
		Assert.isTrue(status == false);

		q.setId(1);
		q.setTitle("Test title");
		q.setText("Test text");
		q.setType(QuestionType.TEXT);
		q.setTimestamp(Timestamp.valueOf("2020-06-16 00:00:00"));
		status = questionDB.deleteQuestionByQuestionId(q.getId());
		Assert.isTrue(status == true);
	}
}
