package CSCI5308.GroupFormationTool.PasswordValidation;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUser;

public interface IPasswordValidatorEnumerator {
	public List<PasswordValidator> getActiveValidators(IUser user);

	public boolean isValidatorActive(PasswordValidatorType passwordValidator);

	public String getConstraint(PasswordValidatorType passwordValidator);

	public IUser getUserForValidation();
}
