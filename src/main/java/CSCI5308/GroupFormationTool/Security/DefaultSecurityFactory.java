package CSCI5308.GroupFormationTool.Security;

public class DefaultSecurityFactory extends SecurityFactory
{
	public IPasswordEncryption makePasswordEncryption()
	{
		return new BCryptPasswordEncryption();
	}
}
