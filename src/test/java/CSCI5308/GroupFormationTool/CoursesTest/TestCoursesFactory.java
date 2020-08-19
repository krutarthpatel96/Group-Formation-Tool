package CSCI5308.GroupFormationTool.CoursesTest;

import CSCI5308.GroupFormationTool.Courses.DefaultCoursesFactory;

public class TestCoursesFactory extends DefaultCoursesFactory
{	
	public TestCoursesFactory()
	{
		courseDB = new CourseDBMock();
		courseUserRelationshipDB = new CourseUserRelationshipDBMock();
	}
}
