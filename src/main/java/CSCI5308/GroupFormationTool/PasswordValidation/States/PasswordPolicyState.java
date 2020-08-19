package CSCI5308.GroupFormationTool.PasswordValidation.States;

import java.util.List;

import CSCI5308.GroupFormationTool.PasswordValidation.IPasswordValidatorEnumerator;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordValidator;

public abstract class PasswordPolicyState {
	public PasswordPolicyState nextState = null;

	public abstract List<PasswordValidator> handle(IPasswordValidatorEnumerator passwordValidatorEnumerator);
}
