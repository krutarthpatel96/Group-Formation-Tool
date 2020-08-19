package CSCI5308.GroupFormationTool.PasswordValidation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.ICallStoredProcedure;

public class PasswordValidatorDB implements IPasswordValidatorPersistence {
	@Override
	public HashMap<Long, String> loadActivePasswordValidators() {
		HashMap<Long, String> activeValidators = new HashMap<Long, String>();
		ICallStoredProcedure proc = null;
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spLoadActivePasswordValidators()");
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					long id = results.getLong(1);
					String name = results.getString(2);
					activeValidators.put(id, name);
				}
			}
		} catch (SQLException e) {
			Logger logger = LoggerFactory.getLogger(PasswordValidatorDB.class);
			logger.error("Failed to load active password validators:" + e.getMessage(), e);
		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return activeValidators;
	}

	@Override
	public String loadConstraintByValidatorId(long id) {
		ICallStoredProcedure proc = null;
		String constraint = null;
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spLoadConstraintByValidator(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					constraint = results.getString(1);
				}
			}
		} catch (SQLException e) {
			Logger logger = LoggerFactory.getLogger(PasswordValidatorDB.class);
			logger.error("Failed to load constraint with validator ID = " + id + ":" + e.getMessage(), e);
		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return constraint;
	}

	@Override
	public List<String> fetchPreviousPasswordsByBannerID(String bannerID, int constraint) {
		ICallStoredProcedure proc = null;
		List<String> passwordList = new ArrayList<String>();
		try {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure("spFetchPreviousPasswords(?,?)");
			proc.setParameter(1, bannerID);
			proc.setParameter(2, constraint);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					passwordList.add(results.getString(1));
				}
			}
		} catch (SQLException e) {
			Logger logger = LoggerFactory.getLogger(PasswordValidatorDB.class);
			logger.error("Failed to fetch previous passwords for user with BannerID = " + bannerID + ":" + e.getMessage(), e);
		} finally {
			proc = DatabaseAbstractFactory.instance().makeCallStoredProcedure(proc);
			proc.cleanup();
		}
		return passwordList;
	}

}
