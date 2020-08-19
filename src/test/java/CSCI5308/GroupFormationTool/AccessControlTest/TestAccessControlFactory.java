package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.*;

public class TestAccessControlFactory extends AccessControlFactory
{
	private IUserPersistence userDB = null;
	
	public IUser makeUser()
	{
		return new User();
	}

	public IUser makeUser(long id)
	{
		return new User(id, makePersistenceObject());
	}

	public IUser makeUser(String bannerID)
	{
		return new User(bannerID, makePersistenceObject());
	}

	public IUserPersistence makePersistenceObject()
	{
		if (null == userDB)
		{
			userDB = new UserDBMock();
		}
		return userDB;
	}

}
