package CSCI5308.GroupFormationTool.AccessControl;

public interface IUserPersistence
{
	public void loadUserByID(long id, IUser user);
	public void loadUserByBannerID(String bannerID, IUser user);
	public boolean createUser(IUser user);
	public boolean updateUser(IUser user);
}
