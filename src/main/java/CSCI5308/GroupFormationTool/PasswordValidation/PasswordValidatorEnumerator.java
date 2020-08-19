package CSCI5308.GroupFormationTool.PasswordValidation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.PasswordValidation.States.LengthState;
import CSCI5308.GroupFormationTool.PasswordValidation.States.PasswordPolicyState;

public class PasswordValidatorEnumerator implements IPasswordValidatorEnumerator {
	private IPasswordValidatorPersistence validatorDB;
	private List<PasswordValidator> activeValidators;
	private HashMap<Long, String> validators;
	private IUser user;

	public PasswordValidatorEnumerator(IPasswordValidatorPersistence validatorDB) {
		this.validatorDB = validatorDB;
		activeValidators = new ArrayList<PasswordValidator>();
		validators = validatorDB.loadActivePasswordValidators();
	}

	public List<PasswordValidator> getActiveValidators(IUser user) {
		System.out.println("Validators active: \n" + validators.values());
		PasswordPolicyState currentState = new LengthState();
		this.user = user;
		activeValidators = new ArrayList<PasswordValidator>();

		do {
			activeValidators.addAll(currentState.handle(this));
			currentState = currentState.nextState;
		} while (currentState != null);

		return activeValidators;
	}

	@Override
	public boolean isValidatorActive(PasswordValidatorType passwordValidator) {
		List<String> passwordValidatorList = new ArrayList<String>(validators.values());
		return passwordValidatorList.contains(passwordValidator.toString());
	}

	@Override
	public String getConstraint(PasswordValidatorType passwordValidator) {
		List<Long> policyIds = new ArrayList<Long>(validators.keySet());
		List<String> passwordValidatorList = new ArrayList<String>(validators.values());
		int index = passwordValidatorList.indexOf(passwordValidator.toString());
		String constraint = validatorDB.loadConstraintByValidatorId(policyIds.get(index));
		return constraint;
	}

	@Override
	public IUser getUserForValidation() {
		return user;
	}

}
