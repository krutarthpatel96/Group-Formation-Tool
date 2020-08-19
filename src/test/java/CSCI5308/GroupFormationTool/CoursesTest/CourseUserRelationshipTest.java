package CSCI5308.GroupFormationTool.CoursesTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.AccessControl.AccessControlFactory;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControlTest.CurrentUserMock;
import CSCI5308.GroupFormationTool.AccessControlTest.TestAccessControlFactory;
import CSCI5308.GroupFormationTool.Courses.*;

@SpringBootTest
@SuppressWarnings("deprecation")
class CourseUserRelationshipTest {
	@BeforeAll
	public static void setup() {
		AccessControlFactory.setConcreteFactory(new TestAccessControlFactory());
		CoursesFactory.setCoursesFactory(new TestCoursesFactory());
	}

	@Test
	public void userHasRoleInCourse() {
		ICourse course = CoursesFactory.instance().makeCourse();
		course.setId(0);
		IUser user = CurrentUserMock.instance().getCurrentAuthenticatedUser();
		CourseUserRelationship relationship = new CourseUserRelationship();
		Assert.isTrue(relationship.userHasRoleInCourse(user, Role.STUDENT, course));
	}

	@Test
	public void loadAllRoluesForUserInCourse() {
		ICourse course = CoursesFactory.instance().makeCourse();
		course.setId(0);
		IUser user = CurrentUserMock.instance().getCurrentAuthenticatedUser();
		CourseUserRelationship relationship = new CourseUserRelationship();
		Assert.isTrue(relationship.loadAllRoluesForUserInCourse(user, course).isEmpty() == false);
	}

	@Test
	public void enrollUserInCourse() {
		ICourse course = CoursesFactory.instance().makeCourse();
		IUser user = CurrentUserMock.instance().getCurrentAuthenticatedUser();
		CourseUserRelationship relationship = new CourseUserRelationship();
		Assert.isTrue(relationship.enrollUserInCourse(user, course, Role.STUDENT));
	}
}
