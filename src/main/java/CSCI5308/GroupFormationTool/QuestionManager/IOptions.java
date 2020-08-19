package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.List;

public interface IOptions {

	public void setDefault();

	public List<OptionValue> getOptionList();

	public void setOptionList(List<OptionValue> optionList);

	public void addOption();

	public void saveOptions(IQuestionPersistence questionDB, long questionID);

	public boolean isStringEmpty(String s);

}
