package CSCI5308.GroupFormationTool.PasswordValidation.States;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.PasswordValidation.IPasswordValidatorEnumerator;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordValidator;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordValidationFactory;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordValidatorType;

public class PasswordState extends PasswordPolicyState {

	private List<PasswordValidator> validators;
	private final PasswordValidatorType passwordHistory = PasswordValidatorType.PASSWORDHISTORY;

	public PasswordState() {
		validators = new ArrayList<PasswordValidator>();
	}

	@Override
	public List<PasswordValidator> handle(IPasswordValidatorEnumerator passwordValidatorEnumerator) {
		String constraint;
		IUser user;

		if (passwordValidatorEnumerator.isValidatorActive(passwordHistory)) {
			constraint = passwordValidatorEnumerator.getConstraint(passwordHistory);
			user = passwordValidatorEnumerator.getUserForValidation();
			validators.add(PasswordValidationFactory.instance().makePasswordHistoryValidator(constraint, user));
		}

		return validators;
	}

}
