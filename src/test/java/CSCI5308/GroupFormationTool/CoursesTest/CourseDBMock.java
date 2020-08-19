package CSCI5308.GroupFormationTool.CoursesTest;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Courses.CoursesFactory;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;

public class CourseDBMock implements ICoursePersistence {

	@Override
	public List<ICourse> loadAllCourses() {
		List<ICourse> courseList = new ArrayList<>();
		ICourse course = CoursesFactory.instance().makeCourse();
		course.setId(0);
		course.setTitle("Software Engineering");
		courseList.add(course);
		course = CoursesFactory.instance().makeCourse();
		course.setId(1);
		course.setTitle("Advanced Topics in Software Development");
		courseList.add(course);
		return courseList;
	}

	@Override
	public void loadCourseByID(long id, ICourse course) {
		course.setId(id);
		course.setTitle("Software Engineering");
	}

	@Override
	public boolean createCourse(ICourse course) {
		course.setId(0);
		course.setTitle("Software Engineering");
		return true;
	}

	@Override
	public boolean deleteCourse(long id) {
		ICourse course = CoursesFactory.instance().makeCourse();
		course.setId(id);
		course.setTitle("Software Engineering");
		course.setDefaults();
		return true;
	}

}
