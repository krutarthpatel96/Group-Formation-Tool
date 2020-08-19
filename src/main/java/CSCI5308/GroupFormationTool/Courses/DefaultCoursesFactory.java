package CSCI5308.GroupFormationTool.Courses;

import org.springframework.web.multipart.MultipartFile;

public class DefaultCoursesFactory extends CoursesFactory
{
	protected ICoursePersistence courseDB;
	protected ICourseUserRelationshipPersistence courseUserRelationshipDB;

	public DefaultCoursesFactory()
	{
		super();
		courseDB = new CourseDB();
		courseUserRelationshipDB = new CourseUserRelationshipDB();
	}

	public ICourse makeCourse()
	{
		return new Course();
	}

	public ICourse makeCourse(long id)
	{
		return new Course(id, makeCourseDB());
	}

	public ICourseUserRelationship makeCourseUserRelationship()
	{
		return new CourseUserRelationship();
	}
	
	public ICoursePersistence makeCourseDB()
	{
		return courseDB;
	}

	public ICourseUserRelationshipPersistence makeCourseUserRelationshipDB()
	{
		return courseUserRelationshipDB;
	}

	public IStudentFileParser makeStudentCSVParser(MultipartFile file)
	{
		return new StudentFileParser(file);
	}
	
	public IStudentFileImport makeStudentFileImport(IStudentFileParser parser, ICourse course)
	{
		return new StudentFileImport(parser, course);
	}
}
