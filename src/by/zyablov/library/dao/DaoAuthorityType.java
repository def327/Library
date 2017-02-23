package by.zyablov.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.zyablov.library.beans.AuthorityType;
import by.zyablov.library.dao.interfaces.DaoBehaviorAuthorityType;
import by.zyablov.library.datasource.DataBaseManager;
import by.zyablov.library.datasource.tools.ManagerSQL;
import by.zyablov.library.exceptions.DaoException;

public class DaoAuthorityType extends DaoAbstract implements DaoBehaviorAuthorityType {

	@Override
	public AuthorityType getAuthorityTypeById(int authorityTypeId) throws DaoException {

		final int QUERY_POSITION_ID_AUTHORITY_TYPE = 1;

		final int AUTHORITY_TYPE_ID = 1;

		final int AUTHORITY_TYPE_NAME = 2;

		AuthorityType authorityTypeObjectFromDataBase = null;

		Connection connectionToDataBase = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {

			connectionToDataBase = super.dataSource.getConnection();

			preparedStatement = connectionToDataBase
					.prepareStatement(super.managerSQL.getPreparedSqlRequest(ManagerSQL.SQL_GET_AUTHORITY_TYPE));

			preparedStatement.setInt(QUERY_POSITION_ID_AUTHORITY_TYPE, authorityTypeId);
			result = preparedStatement.executeQuery();

			if (result.next()) {

				authorityTypeObjectFromDataBase = new AuthorityType();

				do {

					authorityTypeObjectFromDataBase.setId(result.getInt(AUTHORITY_TYPE_ID));
					authorityTypeObjectFromDataBase.setAuthorityTypeTitle(result.getString(AUTHORITY_TYPE_NAME));

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

		return authorityTypeObjectFromDataBase;
	}

}
