package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.List;

public interface IQuestionPersistence {
	public List<IQuestion> loadQuestionsSortedByTitle(String bannerId);

	public List<IQuestion> loadSortedQuestionsSortedByDate(String bannerId);

	public boolean deleteQuestionByQuestionId(long questionId);

	public long createQuestion(IQuestion question, String bannerID);

	public boolean createQuestionOption(IOptionValue option, int order, long questionID);

}
