package CSCI5308.GroupFormationTool.PasswordValidation;

import CSCI5308.GroupFormationTool.AccessControl.IUser;

public class DefaultPasswordValidationFactory extends PasswordValidationFactory
{
	public MinimumLengthValidator makeMinLengthValidator(String constraint)
	{
		return new MinimumLengthValidator(constraint);
	}

	public MaximumLengthValidator makeMaxLengthValidator(String constraint)
	{
		return new MaximumLengthValidator(constraint);
	}

	public MinimumLowercaseValidator makeMinLowerCaseValidator(String constraint)
	{
		return new MinimumLowercaseValidator(constraint);
	}

	public MinimumUppercaseValidator makeMinUpperCaseValidator(String constraint)
	{
		return new MinimumUppercaseValidator(constraint);
	}

	public MinimumSymbolValidator makeMinSymbolValidator(String constraint)
	{
		return new MinimumSymbolValidator(constraint);
	}

	public RestrictedCharacterValidator makeRestrictedCharValidator(String constraint)
	{
		return new RestrictedCharacterValidator(constraint);
	}

	public PasswordHistoryValidator makePasswordHistoryValidator(String constraint, IUser user)
	{
		return new PasswordHistoryValidator(constraint, user);
	}
}
