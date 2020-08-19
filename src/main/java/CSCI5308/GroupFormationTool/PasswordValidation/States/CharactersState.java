package CSCI5308.GroupFormationTool.PasswordValidation.States;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.PasswordValidation.IPasswordValidatorEnumerator;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordValidator;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordValidationFactory;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordValidatorType;

public class CharactersState extends PasswordPolicyState {

	private List<PasswordValidator> validators;
	private final PasswordValidatorType minSymbols = PasswordValidatorType.MINSYMBOLS;
	private final PasswordValidatorType restrictedChar = PasswordValidatorType.RESTRICTEDCHAR;

	public CharactersState() {
		validators = new ArrayList<PasswordValidator>();
	}

	@Override
	public List<PasswordValidator> handle(IPasswordValidatorEnumerator passwordValidatorEnumerator) {
		this.nextState = new PasswordState();
		String constraint;

		if (passwordValidatorEnumerator.isValidatorActive(minSymbols)) {
			constraint = passwordValidatorEnumerator.getConstraint(minSymbols);
			validators.add(PasswordValidationFactory.instance().makeMinSymbolValidator(constraint));
		}

		if (passwordValidatorEnumerator.isValidatorActive(restrictedChar)) {
			constraint = passwordValidatorEnumerator.getConstraint(restrictedChar);
			validators.add(PasswordValidationFactory.instance().makeRestrictedCharValidator(constraint));
		}

		return validators;
	}
}
