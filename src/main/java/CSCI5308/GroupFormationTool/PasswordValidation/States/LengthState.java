package CSCI5308.GroupFormationTool.PasswordValidation.States;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.PasswordValidation.IPasswordValidatorEnumerator;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordValidator;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordValidationFactory;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordValidatorType;

public class LengthState extends PasswordPolicyState {

	private List<PasswordValidator> validators;
	private final PasswordValidatorType minLength = PasswordValidatorType.MINLENGTH;
	private final PasswordValidatorType maxLength = PasswordValidatorType.MAXLENGTH;

	public LengthState() {
		validators = new ArrayList<PasswordValidator>();
	}

	@Override
	public List<PasswordValidator> handle(IPasswordValidatorEnumerator passwordValidatorEnumerator) {
		this.nextState = new LetterCaseState();
		String constraint;

		if (passwordValidatorEnumerator.isValidatorActive(minLength)) {
			constraint = passwordValidatorEnumerator.getConstraint(minLength);
			validators.add(PasswordValidationFactory.instance().makeMinLengthValidator(constraint));
		}

		if (passwordValidatorEnumerator.isValidatorActive(maxLength)) {
			constraint = passwordValidatorEnumerator.getConstraint(maxLength);
			validators.add(PasswordValidationFactory.instance().makeMaxLengthValidator(constraint));
		}

		return validators;
	}

}
