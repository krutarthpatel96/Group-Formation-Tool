package CSCI5308.GroupFormationTool.SecurityTest;

import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

public class PasswordEncryptionMock implements IPasswordEncryption {

	@Override
	public String encryptPassword(String rawPassword) {
		return "encrypted";
	}

	@Override
	public boolean matches(String rawPassword, String encryptedPassword) {
		if (null == rawPassword || rawPassword.isEmpty()) {
			return false;
		}
		if (null == encryptedPassword || encryptedPassword.isEmpty()) {
			return false;
		}
		return encryptedPassword.equals("encrypted");
	}
}
