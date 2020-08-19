package CSCI5308.GroupFormationTool.Survey;

import java.util.List;

import CSCI5308.GroupFormationTool.QuestionManager.IOptions;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

public interface IQuestionBank {

	public void setDefaults();
	
	public List<IQuestion> getQuestion();

	public void setQuestion(List<IQuestion> question);

	public List<IOptions> getOption();

	public void setOption(List<IOptions> option);

	public void createQuestion(IQuestion question, IOptions option);

	public void loadAllSurveyQuestions(ISurveyInstructorRelationshipPersistence surveryInstructorRelationshipDB,
			String bannerId, long courseId);
}
