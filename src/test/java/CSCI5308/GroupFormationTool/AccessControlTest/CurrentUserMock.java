package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.AccessControlFactory;

public class CurrentUserMock {
	private static CurrentUserMock currentUserMock;

	private CurrentUserMock() {

	}

	public static CurrentUserMock instance() {
		if (currentUserMock == null) {
			currentUserMock = new CurrentUserMock();
		}
		return currentUserMock;
	}

	public IUser getCurrentAuthenticatedUser() {
		IUserPersistence userDB = AccessControlFactory.instance().makePersistenceObject();
		String bannerID = "B00000000";
		IUser u = AccessControlFactory.instance().makeUser();
		userDB.loadUserByBannerID(bannerID, u);
		return u;
	}

}
