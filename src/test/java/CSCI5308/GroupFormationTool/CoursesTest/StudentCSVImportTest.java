package CSCI5308.GroupFormationTool.CoursesTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.AccessControlFactory;
import CSCI5308.GroupFormationTool.AccessControlTest.TestAccessControlFactory;
import CSCI5308.GroupFormationTool.Courses.CoursesFactory;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Courses.Role;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.SecurityFactory;
import CSCI5308.GroupFormationTool.SecurityTest.TestSecurityFactory;

@SpringBootTest
@SuppressWarnings("deprecation")
class StudentCSVImportTest {
	@BeforeAll
	public static void setup()
	{
		AccessControlFactory.setConcreteFactory(new TestAccessControlFactory());
		CoursesFactory.setCoursesFactory(new TestCoursesFactory());
		SecurityFactory.setSecurityFactory(new TestSecurityFactory());
	}

	@Test
	public void enrollStudentFromRecord() {
		IUser user = AccessControlFactory.instance().makeUser();
		ICourse course = CoursesFactory.instance().makeCourse();
		IUserPersistence userDB = AccessControlFactory.instance().makePersistenceObject();
		IPasswordEncryption passwordEncryption = SecurityFactory.instance().makePasswordEncryption();
		Assert.isTrue(user.createUser(userDB, passwordEncryption, null));
		Assert.isTrue(course.enrollUserInCourse(Role.STUDENT, user));
	}

	@Test
	public void getSuccessResults() {
		List<String> successResults = new ArrayList<String>();
		successResults.add("Created record");
		assertThat(successResults).isNotNull();
		assertThat(successResults).isNotEmpty();
		Assert.isTrue(successResults.size() > 0);
	}

	@Test
	public void getFailureResults() {
		List<String> failureResults = new ArrayList<String>();
		failureResults.add("Created record");
		assertThat(failureResults).isNotNull();
		assertThat(failureResults).isNotEmpty();
		Assert.isTrue(failureResults.size() > 0);
	}

}
