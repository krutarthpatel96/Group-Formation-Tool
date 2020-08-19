package CSCI5308.GroupFormationTool.Courses;

import org.springframework.web.multipart.MultipartFile;

public abstract class CoursesFactory
{
	private static CoursesFactory uniqueInstance;

	protected CoursesFactory()
	{
	}

	public static CoursesFactory instance()
	{
		return uniqueInstance;
	}
	
	public static void setCoursesFactory(CoursesFactory factory)
	{
		uniqueInstance = factory;
	}

	public abstract ICourse makeCourse();
	public abstract ICourse makeCourse(long id);
	public abstract ICourseUserRelationship makeCourseUserRelationship();
	public abstract ICoursePersistence makeCourseDB();
	public abstract ICourseUserRelationshipPersistence makeCourseUserRelationshipDB();
	public abstract IStudentFileParser makeStudentCSVParser(MultipartFile file);
	public abstract IStudentFileImport makeStudentFileImport(IStudentFileParser parser, ICourse course);
}
