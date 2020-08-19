package CSCI5308.GroupFormationTool.PasswordValidation;

public class PasswordPolicyAbstractFactory {

	private static PasswordPolicyAbstractFactory passwordPolicyAbstractFactory;

	private IPasswordValidatorPersistence passwordValidatorDB;
	private IPasswordValidatorEnumerator passwordValidatorEnumerator;

	private PasswordPolicyAbstractFactory() {
		passwordValidatorDB = new PasswordValidatorDB();
		passwordValidatorEnumerator = new PasswordValidatorEnumerator(passwordValidatorDB);
	}

	public static PasswordPolicyAbstractFactory instance() {
		if (passwordPolicyAbstractFactory == null) {
			passwordPolicyAbstractFactory = new PasswordPolicyAbstractFactory();
		}
		return passwordPolicyAbstractFactory;
	}

	public IPasswordValidatorPersistence makePasswordValidatorDB() {
		return passwordValidatorDB;
	}

	public IPasswordValidatorEnumerator makePasswordValidatorEnumerator() {
		return passwordValidatorEnumerator;
	}

}
