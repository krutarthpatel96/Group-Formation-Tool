package CSCI5308.GroupFormationTool.PasswordValidationTest;

import CSCI5308.GroupFormationTool.PasswordValidation.IPasswordValidatorPersistence;

public class PasswordPolicyTestAbstractFactory
{

	private static PasswordPolicyTestAbstractFactory passwordPolicyAbstractFactory;

	private IPasswordValidatorPersistence passwordValidatorDB;

	private PasswordPolicyTestAbstractFactory() {
		passwordValidatorDB = new PasswordValidatorDBMock();
	}

	public static PasswordPolicyTestAbstractFactory instance() {
		if (passwordPolicyAbstractFactory == null) {
			passwordPolicyAbstractFactory = new PasswordPolicyTestAbstractFactory();
		}
		return passwordPolicyAbstractFactory;
	}

	public IPasswordValidatorPersistence makePasswordValidatorDB() {
		return passwordValidatorDB;
	}

}
