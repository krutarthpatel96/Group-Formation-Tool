package CSCI5308.GroupFormationTool.AccessControl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.ICallStoredProcedure;

public class UserDB implements IUserPersistence
{	
	public void loadUserByID(long id, IUser user)
	{
		ICallStoredProcedure proc = null;
		try
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spLoadUser(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long userID = results.getLong(1);
					String bannerID = results.getString(2);
					String password = results.getString(3);
					String firstName = results.getString(4);
					String lastName = results.getString(5);
					String email = results.getString(6);
					user.setID(userID);
					user.setBannerID(bannerID);
					user.setPassword(password);
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setEmail(email);
				}
			}
		}
		catch (SQLException e)
		{
			Logger logger = LoggerFactory.getLogger(UserDB.class);
			logger.error("Failed to load user with ID = " + id + ": " + e.getMessage(), e);
		}
		finally
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
	}

	public void loadUserByBannerID(String bannerID, IUser user)
	{
		ICallStoredProcedure proc = null;
		long userID = -1;
		try
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spFindUserByBannerID(?)");
			proc.setParameter(1, bannerID);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					userID = results.getLong(1);
				}
			}
		}
		catch (SQLException e)
		{
			Logger logger = LoggerFactory.getLogger(UserDB.class);
			logger.error("Failed to load user with BannerID = " + bannerID + ": " + e.getMessage(), e);
		}
		finally
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		// If we found the ID load the full details.
		if (userID > -1)
		{
			loadUserByID(userID, user);
		}
	}
	
	public boolean createUser(IUser user)
	{
		ICallStoredProcedure proc = null;
		try
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spCreateUser(?, ?, ?, ?, ?, ?)");
			proc.setParameter(1, user.getBannerID());
			proc.setParameter(2, user.getPassword());
			proc.setParameter(3, user.getFirstName());
			proc.setParameter(4, user.getLastName());
			proc.setParameter(5, user.getEmail());
			proc.registerOutputParameterLong(6);
			proc.execute();
		}
		catch (SQLException e)
		{
			Logger logger = LoggerFactory.getLogger(UserDB.class);
			logger.error("Failed to create new user: " + e.getMessage(), e);
			return false;
		}
		finally
		{
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return true;
	}
	
	public boolean updateUser(IUser user)
	{
		return false;
	}
}
