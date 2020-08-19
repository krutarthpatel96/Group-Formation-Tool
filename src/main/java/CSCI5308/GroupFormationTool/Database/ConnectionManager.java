package CSCI5308.GroupFormationTool.Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Singleton for retrieving connections.
public class ConnectionManager
{
	private static ConnectionManager uniqueInstance = null;
	
	private String dbURL;
	private String dbUserName;
	private String dbPassword;
	private Connection nullConnection;
	private CallableStatement nullStatement;
	
	public ConnectionManager()
	{
		IDatabaseConfiguration config = new DefaultDatabaseConfiguration();
		dbURL = config.getDatabaseURL();
		dbUserName = config.getDatabaseUserName();
		dbPassword = config.getDatabasePassword();
		nullConnection = new NullConnection();
		nullStatement = new NullStatement();
	}
	
	public static ConnectionManager instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new ConnectionManager();
		}
		return uniqueInstance;
	}
	
	public Connection getDBConnection() throws SQLException
	{
		return DriverManager.getConnection(dbURL, dbUserName, dbPassword);
	}
	
	public Connection getDBConnection(Connection connection) throws SQLException
	{
		if(connection == null) {
			return nullConnection;
		}
		return connection;
	}
	
	public CallableStatement getDBCallableStatement(CallableStatement statement) throws SQLException
	{
		if(statement == null) {
			return nullStatement;
		}
		return statement;
	}
}
