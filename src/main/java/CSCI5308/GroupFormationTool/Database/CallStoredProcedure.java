package CSCI5308.GroupFormationTool.Database;

import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallStoredProcedure implements ICallStoredProcedure
{
	private String storedProcedureName;
	private Connection connection;
	private CallableStatement statement;
	
	public CallStoredProcedure(String storedProcedureName) throws SQLException
	{
		this.storedProcedureName = storedProcedureName;
		connection = null;
		statement = null;
		openConnection();
		createStatement();
	}
	
	private void createStatement() throws SQLException
	{
		statement = connection.prepareCall("{call " + storedProcedureName + "}");
	}
	
	private void openConnection() throws SQLException
	{
		connection = ConnectionManager.instance().getDBConnection();
	}
	
	@Override
	public void cleanup()
	{
		try
		{
			statement = ConnectionManager.instance().getDBCallableStatement(statement);
			statement.close();
			
			connection = ConnectionManager.instance().getDBConnection(connection);
			if (connection.isClosed() == false)
			{
				connection.close();
			}
			
		}
		catch (Exception e)
		{
			Logger logger = LoggerFactory.getLogger(CallStoredProcedure.class);
			logger.error("Failed to perform DB cleanup:" + e.getMessage(), e);
		}
	}
	
	@Override
	public void setParameter(int paramIndex, String value) throws SQLException
	{
		statement.setString(paramIndex, value);
	}
	
	@Override
	public void registerOutputParameterString(int paramIndex) throws SQLException
	{
		statement.registerOutParameter(paramIndex, java.sql.Types.VARCHAR);
	}
	
	@Override
	public void setParameter(int paramIndex, long value) throws SQLException
	{
		statement.setLong(paramIndex, value);
	}
	
	@Override
	public void registerOutputParameterLong(int paramIndex) throws SQLException
	{
		statement.registerOutParameter(paramIndex, java.sql.Types.BIGINT);
	}
	
	@Override
	public ResultSet executeWithResults() throws SQLException
	{
		if (statement.execute())
		{
			return statement.getResultSet();
		}
		return null;
	}
	
	@Override
	public void execute() throws SQLException
	{
		statement.execute();
	}
}
