package CSCI5308.GroupFormationTool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import CSCI5308.GroupFormationTool.AccessControl.AccessControlFactory;
import CSCI5308.GroupFormationTool.AccessControl.DefaultAccessControlFactory;
import CSCI5308.GroupFormationTool.Courses.CoursesFactory;
import CSCI5308.GroupFormationTool.Courses.DefaultCoursesFactory;
import CSCI5308.GroupFormationTool.PasswordValidation.DefaultPasswordValidationFactory;
import CSCI5308.GroupFormationTool.PasswordValidation.PasswordValidationFactory;
import CSCI5308.GroupFormationTool.QuestionManager.DefaultQuestionManagerFactory;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionManagerFactory;
import CSCI5308.GroupFormationTool.Security.DefaultSecurityFactory;
import CSCI5308.GroupFormationTool.Security.SecurityFactory;
import CSCI5308.GroupFormationTool.Survey.DefaultSurveyFactory;
import CSCI5308.GroupFormationTool.Survey.SurveyFactory;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@SpringBootApplication
public class GroupFormationToolApplication
{
	public static void main(String[] args)
	{
		Logger logger = LoggerFactory.getLogger(GroupFormationToolApplication.class);
		configureFactories(logger);
		SpringApplication.run(GroupFormationToolApplication.class, args);
		logger.info("Group Formation Tool startup complete!");
	}
	
	private static void configureFactories(Logger logger)
	{
		logger.info("Allocating concrete factories...");
		SecurityFactory.setSecurityFactory(new DefaultSecurityFactory());
		AccessControlFactory.setConcreteFactory(new DefaultAccessControlFactory());
		CoursesFactory.setCoursesFactory(new DefaultCoursesFactory());
		PasswordValidationFactory.setPasswordValidationFactory(new DefaultPasswordValidationFactory());
		QuestionManagerFactory.setQuestionManagerFactory(new DefaultQuestionManagerFactory());
		SurveyFactory.setSurveyFactory(new DefaultSurveyFactory());
	}
}