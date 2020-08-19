package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.ICallStoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDB implements ICoursePersistence
{
	public List<ICourse> loadAllCourses()
	{
		List<ICourse> courses = new ArrayList<ICourse>();
		ICallStoredProcedure proc = null;
		try
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spLoadAllCourses()");
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long id = results.getLong(1);
					String title = results.getString(2);
					ICourse c = CoursesFactory.instance().makeCourse();
					c.setId(id);
					c.setTitle(title);
					courses.add(c);
				}
			}
		}
		catch (SQLException e)
		{
			Logger logger = LoggerFactory.getLogger(CourseDB.class);
			logger.error("Failed to load all courses: " + e.getMessage(), e);
		}
		finally
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return courses;
	}

	public void loadCourseByID(long id, ICourse course)
	{
		ICallStoredProcedure proc = null;
		try
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spFindCourseByID(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					String title = results.getString(2);
					course.setId(id);
					course.setTitle(title);
				}
			}
		}
		catch (SQLException e)
		{
			Logger logger = LoggerFactory.getLogger(CourseDB.class);
			logger.error("Failed to course with ID = " + id + ": " + e.getMessage(), e);
		}
		finally
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
	}
	
	public boolean createCourse(ICourse course)
	{
		ICallStoredProcedure proc = null;
		try
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spCreateCourse(?, ?)");
			proc.setParameter(1, course.getTitle());
			proc.registerOutputParameterLong(2);
			proc.execute();
		}
		catch (SQLException e)
		{
			Logger logger = LoggerFactory.getLogger(CourseDB.class);
			logger.error("Failed to create a course: " + e.getMessage(), e);
			return false;
		}
		finally
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return true;
	}
	
	public boolean deleteCourse(long id)
	{
		ICallStoredProcedure proc = null;
		try
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spDeleteCourse(?)");
			proc.setParameter(1, id);
			proc.execute();
		}
		catch (SQLException e)
		{
			Logger logger = LoggerFactory.getLogger(CourseDB.class);
			logger.error("Failed to delete course with ID = " + id + ": " + e.getMessage(), e);
			return false;
		}
		finally
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return true;
	}
}
