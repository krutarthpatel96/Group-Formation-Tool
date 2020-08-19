package CSCI5308.GroupFormationTool.Courses;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.AccessControlFactory;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.SecurityFactory;

public class StudentFileImport implements IStudentFileImport
{
	private List<String> successResults;
	private List<String> failureResults;
	private ICourse course;
	private IUserPersistence userDB;
	private IPasswordEncryption passwordEncryption;
	private IStudentFileParser parser;
	
	public StudentFileImport(IStudentFileParser parser, ICourse course)
	{
		this.course = course;
		successResults = new ArrayList<String>();
		failureResults = new ArrayList<String>();
		userDB = AccessControlFactory.instance().makePersistenceObject();
		passwordEncryption = SecurityFactory.instance().makePasswordEncryption();
		this.parser = parser;
		enrollStudentFromRecord();
	}
	
	@Override
	public void enrollStudentFromRecord()
	{
		List<IUser> studentList = parser.parseFile(failureResults);
		for(IUser u : studentList)
		{	
			String bannerID = u.getBanner();
			String firstName = u.getFirstName();
			String lastName = u.getLastName();
			String email = u.getEmail();
			String userDetails = bannerID + " " + firstName + " " + lastName +" " + email;
			
			IUser user = AccessControlFactory.instance().makeUser();
			userDB.loadUserByBannerID(bannerID, user);
			
			if (user.isValidUser() == false)
			{
				user.setBannerID(bannerID);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				if (user.createUser(userDB, passwordEncryption, null))
				{
					successResults.add("Created: " + userDetails);
					userDB.loadUserByBannerID(bannerID, user);
				}
				else
				{
					failureResults.add("Unable to save this user to DB: " + userDetails);
					return;
				}
			}
			if (course.enrollUserInCourse(Role.STUDENT, user))
			{
				successResults.add("User enrolled in course: " + userDetails);
			}else 
			{
				failureResults.add("Unable to enroll user in course: " + userDetails);
			}
		}
	}
	
	@Override
	public List<String> getSuccessResults()
	{
		return successResults;
	}
	
	@Override
	public List<String> getFailureResults()
	{
		return failureResults;
	}
	
}
