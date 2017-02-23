package by.zyablov.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.zyablov.library.beans.AuthorityType;
import by.zyablov.library.beans.User;
import by.zyablov.library.dao.interfaces.DaoBehaviorUser;
import by.zyablov.library.datasource.DataBaseManager;
import by.zyablov.library.datasource.tools.ManagerSQL;
import by.zyablov.library.exceptions.DaoException;

public class DaoUser extends DaoAbstract implements DaoBehaviorUser {

	@Override
	public User getUserByEmail(String userLogin) throws DaoException {

		final int QUERY_POSITION_EMAIL = 1;

		final int ID_USER = 1;

		final int EMAIL = 2;

		final int PASSWORD = 3;

		final int FIRST_NAME = 4;

		final int LAST_NAME = 5;

		final int ID_AUTHORITY_TYPE = 6;

		User userObjectFromDataBase = null;

		Connection connectionToDataBase = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {

			connectionToDataBase = super.dataSource.getConnection();

			preparedStatement = connectionToDataBase
					.prepareStatement(super.managerSQL.getPreparedSqlRequest(ManagerSQL.SQL_GET_USER));

			preparedStatement.setString(QUERY_POSITION_EMAIL, userLogin);
			result = preparedStatement.executeQuery();

			if (result.next()) {

				userObjectFromDataBase = new User();

				do {

					userObjectFromDataBase.setId(result.getInt(ID_USER));
					userObjectFromDataBase.setEmail(result.getString(EMAIL));
					userObjectFromDataBase.setPassword(result.getString(PASSWORD));
					userObjectFromDataBase.setFirstName(result.getString(FIRST_NAME));
					userObjectFromDataBase.setLastName(result.getString(LAST_NAME));

					userObjectFromDataBase.setAuthorityType(new AuthorityType());
					userObjectFromDataBase.getAuthorityType().setId(result.getInt(ID_AUTHORITY_TYPE));

				} while (result.next());
			}

		} catch (SQLException e) {

			// Logging

			DataBaseManager.getInstance().closeDataBaseManager();
			ManagerSQL.getInstance().closeManagerSql();

			throw new DaoException();

		} finally {

			if (result != null)

				try {
					result.close();

				} catch (SQLException ignore) {

					// Logging
				}

			if (preparedStatement != null)

				try {
					preparedStatement.close();

				} catch (SQLException ignore) {

					// Logging
				}

			if (connectionToDataBase != null)

				try {
					connectionToDataBase.close();

				} catch (SQLException ignore) {

					// Logging
				}

		}

		return userObjectFromDataBase;
	}

	@Override
	public void addNewUserClient(User user) throws DaoException {

		final int QUERY_POSITION_EMAIL = 1;

		final int QUERY_POSITION_PASSWORD = 2;

		final int QUERY_POSITION_FIRST_NAME = 3;

		final int QUERY_POSITION_LAST_NAME = 4;

		final int QUERY_POSITION_ID_AUTHORITY_TYPE = 5;

		Connection connectionToDataBase = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {

			connectionToDataBase = super.dataSource.getConnection();

			preparedStatement = connectionToDataBase
					.prepareStatement(super.managerSQL.getPreparedSqlRequest(ManagerSQL.SQL_ADD_NEW_USER));

			preparedStatement.setString(QUERY_POSITION_EMAIL, user.getEmail());
			preparedStatement.setString(QUERY_POSITION_PASSWORD, user.getPassword());
			preparedStatement.setString(QUERY_POSITION_FIRST_NAME, user.getFirstName());
			preparedStatement.setString(QUERY_POSITION_LAST_NAME, user.getLastName());
			preparedStatement.setInt(QUERY_POSITION_ID_AUTHORITY_TYPE, user.getAuthorityType().getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			// Logging

			DataBaseManager.getInstance().closeDataBaseManager();
			ManagerSQL.getInstance().closeManagerSql();

			throw new DaoException();

		} finally {

			if (result != null)

				try {
					result.close();

				} catch (SQLException ignore) {

					DataBaseManager.getInstance().closeDataBaseManager();
					ManagerSQL.getInstance().closeManagerSql();

					// Logging
				}

			if (preparedStatement != null)

				try {
					preparedStatement.close();

				} catch (SQLException ignore) {

					DataBaseManager.getInstance().closeDataBaseManager();
					ManagerSQL.getInstance().closeManagerSql();

					// Logging
				}

			if (connectionToDataBase != null)

				try {
					connectionToDataBase.close();

				} catch (SQLException ignore) {

					DataBaseManager.getInstance().closeDataBaseManager();
					ManagerSQL.getInstance().closeManagerSql();

					// Logging
				}

		}

	}

}
