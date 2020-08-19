package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserNotifications;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.AccessControlFactory;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.SecurityFactory;
import CSCI5308.GroupFormationTool.SecurityTest.TestSecurityFactory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@SuppressWarnings("deprecation")
public class UserTest
{
	@BeforeEach
	public void setup()
	{
		AccessControlFactory.setConcreteFactory(new TestAccessControlFactory());
		SecurityFactory.setSecurityFactory(new TestSecurityFactory());
	}
	
	@Test
	public void ConstructorTests()
	{
		IUser u = AccessControlFactory.instance().makeUser();
		Assert.isTrue(u.getBannerID().isEmpty());
		Assert.isTrue(u.getFirstName().isEmpty());
		Assert.isTrue(u.getLastName().isEmpty());
		Assert.isTrue(u.getEmail().isEmpty());
		
		u = AccessControlFactory.instance().makeUser(1);
		Assert.isTrue(u.getBannerID().equals("B00000000"));
		
		u = AccessControlFactory.instance().makeUser("B00000000");
		Assert.isTrue(u.getBannerID().equals("B00000000"));
	}
	
	@Test
	public void setIDTest()
	{
		IUser u = AccessControlFactory.instance().makeUser();
		u.setID(10);
		Assert.isTrue(10 == u.getID());
	}
	
	@Test
	public void getIDTest()
	{
		IUser u = AccessControlFactory.instance().makeUser();
		u.setID(10);
		Assert.isTrue(10 == u.getID());
	}
	
	@Test
	public void setBannerIDTest()
	{
		IUser u = AccessControlFactory.instance().makeUser();
		u.setBannerID("B00000000");
		Assert.isTrue(u.getBannerID().equals("B00000000"));
	}
	
	@Test
	public void getBannerIDTest()
	{
		IUser u = AccessControlFactory.instance().makeUser();
		u.setBannerID("B00000000");
		Assert.isTrue(u.getBannerID().equals("B00000000"));
	}
	
	@Test
	public void setFirstNameTest()
	{
		IUser u = AccessControlFactory.instance().makeUser();
		u.setFirstName("Rob");
		Assert.isTrue(u.getFirstName().equals("Rob"));
	}
	
	@Test
	public void getFirstNameTest()
	{
		IUser u = AccessControlFactory.instance().makeUser();
		u.setFirstName("Rob");
		Assert.isTrue(u.getFirstName().equals("Rob"));
	}

	@Test
	public void setLastNameTest()
	{
		IUser u = AccessControlFactory.instance().makeUser();
		u.setLastName("Hawkey");
		Assert.isTrue(u.getLastName().equals("Hawkey"));
	}

	@Test
	public void getLastNameTest()
	{
		IUser u = AccessControlFactory.instance().makeUser();
		u.setLastName("Hawkey");
		Assert.isTrue(u.getLastName().equals("Hawkey"));
	}
	
	@Test
	public void setEmailTest()
	{
		IUser u = AccessControlFactory.instance().makeUser();
		u.setEmail("rhawkey@dal.ca");
		Assert.isTrue(u.getEmail().equals("rhawkey@dal.ca"));
	}
	
	@Test
	public void getEmailTest()
	{
		IUser u = AccessControlFactory.instance().makeUser();
		u.setEmail("rhawkey@dal.ca");
		Assert.isTrue(u.getEmail().equals("rhawkey@dal.ca"));
	}
	
	@Test
	public void createUser()
	{
		IUserPersistence userDB = AccessControlFactory.instance().makePersistenceObject();
		IPasswordEncryption passwordEncryption = SecurityFactory.instance().makePasswordEncryption();
		IUserNotifications notification = null;
		IUser u = AccessControlFactory.instance().makeUser();
		u.createUser(userDB, passwordEncryption, notification);
		Assert.isTrue(u.getId() == 0);
		Assert.isTrue(u.getBannerID().equals("B00000000"));
	}

	@Test
	public void isBannerIDValidTest()
	{
		IUser u = AccessControlFactory.instance().makeUser();
		Assert.isTrue(u.isBannerIDValid("B000000000"));
		assertThat(u.isBannerIDValid(null)).isFalse();
		assertThat(u.isBannerIDValid("")).isFalse();
	}
		
	@Test
	public void isFirstNameValidTest()
	{
		IUser u = AccessControlFactory.instance().makeUser();
		Assert.isTrue(u.isFirstNameValid("rob"));
		assertThat(u.isFirstNameValid(null)).isFalse();
		assertThat(u.isFirstNameValid("")).isFalse();
	}
	
	@Test
	public void isLastNameValidTest()
	{
		IUser u = AccessControlFactory.instance().makeUser();
		Assert.isTrue(u.isLastNameValid("hawkey"));
		assertThat(u.isLastNameValid(null)).isFalse();
		assertThat(u.isLastNameValid("")).isFalse();
	}
	
	@Test
	public void isEmailValidTest()
	{
		IUser u = AccessControlFactory.instance().makeUser();
		Assert.isTrue(u.isEmailValid("rhawkey@dal.ca"));
		assertThat(u.isEmailValid(null)).isFalse();
		assertThat(u.isEmailValid("")).isFalse();
		assertThat(u.isEmailValid("@dal.ca")).isFalse();
		assertThat(u.isEmailValid("rhawkey@")).isFalse();
	}	
}
