package CSCI5308.GroupFormationTool.SecurityTest;

import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.SecurityFactory;

public class TestSecurityFactory extends SecurityFactory
{
	public IPasswordEncryption makePasswordEncryption()
	{
		return new PasswordEncryptionMock();
	}

}
