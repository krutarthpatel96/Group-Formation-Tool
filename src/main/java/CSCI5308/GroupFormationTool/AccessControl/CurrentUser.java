package CSCI5308.GroupFormationTool.AccessControl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser
{
	private static CurrentUser uniqueInstance = null;
	
	private CurrentUser()
	{
		
	}
	
	public static CurrentUser instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new CurrentUser();
		}
		return uniqueInstance;
	}
	
	public IUser getCurrentAuthenticatedUser()
	{
		IUserPersistence userDB = AccessControlFactory.instance().makePersistenceObject();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.isAuthenticated())
		{
			String bannerID = authentication.getPrincipal().toString();
			IUser u = AccessControlFactory.instance().makeUser();
			userDB.loadUserByBannerID(bannerID, u);
			if (u.isValidUser())
			{
				return u;
			}
		}
		return null;
	}
	
}
