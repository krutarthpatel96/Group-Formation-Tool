package CSCI5308.GroupFormationTool.CoursesTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.AccessControl.AccessControlFactory;
import CSCI5308.GroupFormationTool.AccessControlTest.TestAccessControlFactory;
import CSCI5308.GroupFormationTool.Courses.CoursesFactory;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;

@SpringBootTest
@SuppressWarnings("deprecation")
class CourseTest {
	@BeforeAll
	public static void setup()
	{
		AccessControlFactory.setConcreteFactory(new TestAccessControlFactory());
		CoursesFactory.setCoursesFactory(new TestCoursesFactory());
	}
	
	@Test
	public void ConstructorTests() {
		ICourse course = CoursesFactory.instance().makeCourse();
		Assert.isTrue(course.getId() == -1);
		Assert.isTrue(course.getTitle().isEmpty());
		course = CoursesFactory.instance().makeCourse(0);
		Assert.isTrue(course.getId() == 0);
		Assert.isTrue(course.getTitle().equals("Software Engineering"));
	}

	@Test
	public void setIdTest() {
		ICourse course = CoursesFactory.instance().makeCourse();
		course.setId(7);
		Assert.isTrue(course.getId() == 7);
	}

	@Test
	public void getIdTest() {
		ICourse course = CoursesFactory.instance().makeCourse();
		course.setId(7);
		Assert.isTrue(course.getId() == 7);
	}

	@Test
	public void setTitleTest() {
		ICourse course = CoursesFactory.instance().makeCourse();
		course.setTitle("Advanced Topics in Software Development");
		Assert.isTrue(course.getTitle().equals("Advanced Topics in Software Development"));
	}

	@Test
	public void getTitleTest() {
		ICourse course = CoursesFactory.instance().makeCourse();
		course.setTitle("Advanced Topics in Software Development");
		Assert.isTrue(course.getTitle().equals("Advanced Topics in Software Development"));
	}

	@Test
	public void deleteCourseTest() {
		ICoursePersistence courseDB = TestCoursesFactory.instance().makeCourseDB();
		ICourse course = CoursesFactory.instance().makeCourse();
		boolean status = course.delete(courseDB);
		Assert.isTrue(status);
	}

	@Test
	public void createCourseTest() {
		ICoursePersistence courseDB = TestCoursesFactory.instance().makeCourseDB();
		ICourse course = CoursesFactory.instance().makeCourse();
		course.setId(0);
		course.setTitle("Software Engineering");
		course.createCourse(courseDB);
		Assert.isTrue(course.getId() == 0);
		Assert.isTrue(course.getTitle().equals("Software Engineering"));
	}

}
