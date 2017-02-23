package by.zyablov.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.zyablov.library.beans.Categorie;
import by.zyablov.library.dao.interfaces.DaoBehaviorCategorie;
import by.zyablov.library.datasource.DataBaseManager;
import by.zyablov.library.datasource.tools.ManagerSQL;
import by.zyablov.library.exceptions.DaoException;

public class DaoCategorie extends DaoAbstract implements DaoBehaviorCategorie {

	@Override
	public Categorie getCategorieById(int categorieId) throws DaoException {

		final int QUERY_POSITION_ID_CREDIT_CARD_TYPE = 1;

		final int ID_CATEGORIE = 1;

		final int CATEGORIE_NAME = 2;

		Categorie categorieObjectFromDataBase = null;

		Connection connectionToDataBase = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {

			connectionToDataBase = super.dataSource.getConnection();

			preparedStatement = connectionToDataBase
					.prepareStatement(super.managerSQL.getPreparedSqlRequest(ManagerSQL.SQL_GET_CATEGORIE));

			preparedStatement.setInt(QUERY_POSITION_ID_CREDIT_CARD_TYPE, categorieId);
			result = preparedStatement.executeQuery();

			if (result.next()) {

				categorieObjectFromDataBase = new Categorie();

				do {

					categorieObjectFromDataBase.setId(result.getInt(ID_CATEGORIE));
					categorieObjectFromDataBase.setCategorie_name(result.getString(CATEGORIE_NAME));

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

		return categorieObjectFromDataBase;
	}

}
