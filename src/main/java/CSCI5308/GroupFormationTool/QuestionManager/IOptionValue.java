package CSCI5308.GroupFormationTool.QuestionManager;

public interface IOptionValue {

	public void setDefault();

	public String getText();

	public void setText(String text);

	public String getStoredAs();

	public void setStoredAs(String storedAs);

	public OptionValue createOption(String text, String storedAs);
	
	public OptionValue createOption(long id, String text, String storedAs);
}
