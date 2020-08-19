package CSCI5308.GroupFormationTool.AccessControl;

public class DefaultAccessControlFactory extends AccessControlFactory
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
			userDB = new UserDB();
		}
		return userDB;
	}
}
