package CSCI5308.GroupFormationTool.QuestionManager;

public class OptionValue implements IOptionValue
{
	private long id;
	private String text;
	private String storedAs;
	
	public OptionValue() 
	{
		setDefault();
	}

	public OptionValue(String text, String storedAs) {
		createOption(text, storedAs);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDefault() 
	{
		text="";
		storedAs="";
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getStoredAs() {
		return storedAs;
	}

	public void setStoredAs(String storedAs) {
		this.storedAs = storedAs;
	}

	public OptionValue createOption(String text, String storedAs) {
		this.text = text;
		this.storedAs = storedAs;
		return this;
	}
	
	public OptionValue createOption(long id, String text, String storedAs) 
	{
		this.setId(id);
		this.text = text;
		this.storedAs = storedAs;
		return this;
	}
	
}
