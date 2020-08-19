package CSCI5308.GroupFormationTool.Database;

import java.sql.SQLException;

public class DatabaseAbstractFactory {

	private static DatabaseAbstractFactory databaseAbstractFactory;
	private static ICallStoredProcedure nullCallStoredProcedure;

	private DatabaseAbstractFactory() {
		nullCallStoredProcedure = new NullCallStoredProcedure();
	}

	public static DatabaseAbstractFactory instance() {
		if (databaseAbstractFactory == null) {
			databaseAbstractFactory = new DatabaseAbstractFactory();
		}
		return databaseAbstractFactory;
	}

	public ICallStoredProcedure makeCallStoredProcedure(String storedProcedureName) throws SQLException {
		return new CallStoredProcedure(storedProcedureName);
	}

	public ICallStoredProcedure makeCallStoredProcedure(ICallStoredProcedure proc) {
		if (proc == null) {
			return nullCallStoredProcedure;
		}
		return proc;
	}
}
