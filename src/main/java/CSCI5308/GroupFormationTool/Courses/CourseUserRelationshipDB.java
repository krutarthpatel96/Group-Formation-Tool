package CSCI5308.GroupFormationTool.Courses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.AccessControlFactory;
import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.ICallStoredProcedure;

public class CourseUserRelationshipDB implements ICourseUserRelationshipPersistence
{
	public List<IUser> findAllUsersWithoutCourseRole(Role role, long courseID)
	{
		List<IUser> users = new ArrayList<IUser>();
		ICallStoredProcedure proc = null;
		try
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spFindUsersWithoutCourseRole(?, ?)");
			proc.setParameter(1, role.toString());
			proc.setParameter(2,  courseID);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long userID = results.getLong(1);
					String bannerID = results.getString(2);
					String firstName = results.getString(3);
					String lastName = results.getString(4);
					IUser u = AccessControlFactory.instance().makeUser();
					u.setID(userID);
					u.setBannerID(bannerID);
					u.setFirstName(firstName);
					u.setLastName(lastName);
					users.add(u);
				}
			}
		}
		catch (SQLException e)
		{
			Logger logger = LoggerFactory.getLogger(CourseUserRelationshipDB.class);
			logger.error("Failed to find all users without course role = " +
							 role +
							 ", for course ID = " +
							 courseID +
							 ": " + e.getMessage(),
							 e);
		}
		finally
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return users;
	}

	public List<IUser> findAllUsersWithCourseRole(Role role, long courseID)
	{
		List<IUser> users = new ArrayList<IUser>();
		ICallStoredProcedure proc = null;
		try
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spFindUsersWithCourseRole(?, ?)");
			proc.setParameter(1, role.toString());
			proc.setParameter(2,  courseID);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long userID = results.getLong(1);
					IUser u = AccessControlFactory.instance().makeUser();
					u.setID(userID);
					users.add(u);
				}
			}
		}
		catch (SQLException e)
		{
			Logger logger = LoggerFactory.getLogger(CourseUserRelationshipDB.class);
			logger.error("Failed to find all users with course role = " +
							 role +
							 ", for course ID = " +
							 courseID +
							 ": " + e.getMessage(),
							 e);
		}
		finally
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return users;
	}
	
	public boolean enrollUser(ICourse course, IUser user, Role role)
	{
		ICallStoredProcedure proc = null;
		try
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spEnrollUser(?, ?, ?)");
			proc.setParameter(1, course.getId());
			proc.setParameter(2, user.getID());
			proc.setParameter(3, role.toString());
			proc.execute();
		}
		catch (SQLException e)
		{
			Logger logger = LoggerFactory.getLogger(CourseUserRelationshipDB.class);
			logger.error("Failed to enroll user with course role = " +
							 role +
							 ", for course ID = " +
							 course.getId() +
							 ", for user ID = " +
							 user.getID() +
							 ": " + e.getMessage(),
							 e);
			return false;
		}
		finally
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return true;
	}

	public List<Role> loadUserRolesForCourse(ICourse course, IUser user)
	{
		List<Role> roles = new ArrayList<Role>();
		ICallStoredProcedure proc = null;
		try
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spLoadUserRolesForCourse(?, ?)");
			proc.setParameter(1, course.getId());
			proc.setParameter(2, user.getID());
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					Role role = Role.valueOf(results.getString(1).toUpperCase());
					roles.add(role);
				}
			}
		}
		catch (SQLException e)
		{
			Logger logger = LoggerFactory.getLogger(CourseUserRelationshipDB.class);
			logger.error("Failed to load user roles for course = " +
							 course.getId() +
							 ", for user ID = " +
							 user.getId() +
							 ": " + e.getMessage(),
							 e);
		}
		finally
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return roles;
	}
}
