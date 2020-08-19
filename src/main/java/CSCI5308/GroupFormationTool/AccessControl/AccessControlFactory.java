package CSCI5308.GroupFormationTool.AccessControl;

public abstract class AccessControlFactory
{
	private static AccessControlFactory uniqueInstance = null;

	protected AccessControlFactory()
	{
	}

	public static AccessControlFactory instance()
	{
		return uniqueInstance;
	}
	
	public static void setConcreteFactory(AccessControlFactory factory)
	{
		uniqueInstance = factory;
	}

	public abstract IUser makeUser();
	public abstract IUser makeUser(long id);
	public abstract IUser makeUser(String bannerID);
	public abstract IUserPersistence makePersistenceObject();
}
