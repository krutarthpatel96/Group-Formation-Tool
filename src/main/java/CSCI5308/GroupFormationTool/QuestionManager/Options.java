package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.ArrayList;
import java.util.List;

//wrapper class to pass lists from thymeleaf to controllers
public class Options implements IOptions {
	public List<OptionValue> optionList;

	public Options() {
		setDefault();
	}

	@Override
	public void setDefault() {
		optionList = new ArrayList<OptionValue>();
	}

	@Override
	public List<OptionValue> getOptionList() {
		return optionList;
	}

	@Override
	public void setOptionList(List<OptionValue> optionList) {
		this.optionList = optionList;
	}

	@Override
	public void addOption() 
	{
		String index = String.valueOf(optionList.size()+1);
		optionList.add((OptionValue) QuestionManagerFactory.instance().makeOptionValue(index, index));
	}

	@Override
	public void saveOptions(IQuestionPersistence questionDB, long questionID) {
		int order = 1;
		if (questionID != -1) {
			for (IOptionValue option : optionList) {
				String text = option.getText();
				String storedAs = option.getStoredAs();
				if (isStringEmpty(text) || isStringEmpty(storedAs)) {
					continue;
				}
				questionDB.createQuestionOption(option, order++, questionID);
			}
		}

	}

	@Override
	public boolean isStringEmpty(String s) {
		return s.replaceAll(" ", "").length() == 0;
	}

}
