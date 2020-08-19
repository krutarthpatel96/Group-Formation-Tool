package CSCI5308.GroupFormationTool.PasswordValidation;

import CSCI5308.GroupFormationTool.AccessControl.IUser;

public abstract class PasswordValidationFactory
{
	private static PasswordValidationFactory uniqueInstance;

	public static PasswordValidationFactory instance()
	{
		return uniqueInstance;
	}
	
	public static void setPasswordValidationFactory(PasswordValidationFactory factory)
	{
		uniqueInstance = factory;
	}

	public abstract MinimumLengthValidator makeMinLengthValidator(String constraint);
	public abstract MaximumLengthValidator makeMaxLengthValidator(String constraint);
	public abstract MinimumLowercaseValidator makeMinLowerCaseValidator(String constraint);
	public abstract MinimumUppercaseValidator makeMinUpperCaseValidator(String constraint);
	public abstract MinimumSymbolValidator makeMinSymbolValidator(String constraint);
	public abstract RestrictedCharacterValidator makeRestrictedCharValidator(String constraint);
	public abstract PasswordHistoryValidator makePasswordHistoryValidator(String constraint, IUser user);
}
