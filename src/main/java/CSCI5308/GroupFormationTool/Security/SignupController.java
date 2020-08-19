package CSCI5308.GroupFormationTool.Security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.AccessControlFactory;
import CSCI5308.GroupFormationTool.PasswordValidation.IPasswordValidatorEnumerator;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordPolicyAbstractFactory;

@Controller
public class SignupController
{
	private final String USERNAME = "username";
	private final String PASSWORD = "password";
	private final String PASSWORD_CONFIRMATION = "passwordConfirmation";
	private final String FIRST_NAME = "firstName";
	private final String LAST_NAME = "lastName";
	private final String EMAIL = "email";
	private IPasswordValidatorEnumerator passwordValidatorEnumerator;
	
	public SignupController() {
		passwordValidatorEnumerator = PasswordPolicyAbstractFactory.instance().makePasswordValidatorEnumerator();
	}
	
	@GetMapping("/signup")
	public String displaySignup(Model model)
	{
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST) 
	public ModelAndView processSignup(
   	@RequestParam(name = USERNAME) String bannerID,
   	@RequestParam(name = PASSWORD) String password,
   	@RequestParam(name = PASSWORD_CONFIRMATION) String passwordConfirm,
   	@RequestParam(name = FIRST_NAME) String firstName,
   	@RequestParam(name = LAST_NAME) String lastName,
   	@RequestParam(name = EMAIL) String email)
	{
		boolean success = false;
		List<String> errorMessages = new ArrayList<String>();
		IUser u = AccessControlFactory.instance().makeUser();
		u.setBannerID(bannerID);
		u.setPassword(password);
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setEmail(email);
		
		if (u.isBannerIDValid(bannerID) &&
				u.isEmailValid(email) &&
				u.isFirstNameValid(firstName) &&
				u.isLastNameValid(lastName) &&
				password.equals(passwordConfirm))
		{
			
			IUserPersistence userDB = AccessControlFactory.instance().makePersistenceObject();
			IPasswordEncryption passwordEncryption = SecurityFactory.instance().makePasswordEncryption();
			success = u.createUser(userDB, passwordValidatorEnumerator, passwordEncryption, null, errorMessages);
		}
		ModelAndView m = new ModelAndView("login");
		if (success == false)
		{
			m = new ModelAndView("signup");
			m.addObject("errorMessage", "Invalid data, please check your values.");
			m.addObject("passwordInvalid",errorMessages);
			System.out.println(errorMessages);
		}
		return m;
	}
}