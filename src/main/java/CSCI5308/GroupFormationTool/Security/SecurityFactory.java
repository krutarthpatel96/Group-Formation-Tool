package CSCI5308.GroupFormationTool.Security;

public abstract class SecurityFactory {

	private static SecurityFactory uniqueInstance;

	protected SecurityFactory()
	{
	}

	public static SecurityFactory instance()
	{
		return uniqueInstance;
	}
	
	public static void setSecurityFactory(SecurityFactory factory)
	{
		uniqueInstance = factory;
	}

	public abstract IPasswordEncryption makePasswordEncryption();
}
