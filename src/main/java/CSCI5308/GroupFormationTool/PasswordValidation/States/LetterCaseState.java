package CSCI5308.GroupFormationTool.PasswordValidation.States;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.PasswordValidation.IPasswordValidatorEnumerator;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordValidator;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordValidationFactory;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordValidatorType;

public class LetterCaseState extends PasswordPolicyState {

	private List<PasswordValidator> validators;
	private final PasswordValidatorType minLowerCase = PasswordValidatorType.MINLOWERCASE;
	private final PasswordValidatorType minUpperCase = PasswordValidatorType.MINUPPERCASE;

	public LetterCaseState() {
		validators = new ArrayList<PasswordValidator>();
	}

	@Override
	public List<PasswordValidator> handle(IPasswordValidatorEnumerator passwordValidatorEnumerator) {
		this.nextState = new CharactersState();
		String constraint;

		if (passwordValidatorEnumerator.isValidatorActive(minLowerCase)) {
			constraint = passwordValidatorEnumerator.getConstraint(minLowerCase);
			validators.add(PasswordValidationFactory.instance().makeMinLowerCaseValidator(constraint));
		}

		if (passwordValidatorEnumerator.isValidatorActive(minUpperCase)) {
			constraint = passwordValidatorEnumerator.getConstraint(minUpperCase);
			validators.add(PasswordValidationFactory.instance().makeMinUpperCaseValidator(constraint));
		}

		return validators;
	}
}
