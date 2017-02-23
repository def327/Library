package by.zyablov.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import by.zyablov.library.beans.Book;
import by.zyablov.library.beans.Categorie;
import by.zyablov.library.dao.interfaces.DaoBehaviorBook;
import by.zyablov.library.datasource.DataBaseManager;
import by.zyablov.library.datasource.tools.ManagerSQL;
import by.zyablov.library.exceptions.DaoException;

public class DaoBook extends DaoAbstract implements DaoBehaviorBook {

	@Override
	public List<Book> getListOfBooks() throws DaoException {

		final int ID_BOOK = 1;

		final int BOOK_NAME = 2;

		final int BOOK_PRICE = 3;

		final int BOOK_DESCRIPTION = 4;

		final int BOOK_RELEASE_DATE = 5;

		final int CATEGORIE_ID = 6;

		List<Book> listOfBooks = null;

		Connection connectionToDataBase = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {

			connectionToDataBase = super.dataSource.getConnection();

			preparedStatement = connectionToDataBase
					.prepareStatement(super.managerSQL.getPreparedSqlRequest(ManagerSQL.SQL_GET_BOOK_LIST));

			result = preparedStatement.executeQuery();

			if (result.next()) {

				listOfBooks = new LinkedList<>();

				do {

					Book bookObjectFromDataBase = new Book();

					bookObjectFromDataBase.setId(result.getInt(ID_BOOK));
					bookObjectFromDataBase.setBookName(result.getString(BOOK_NAME));
					bookObjectFromDataBase.setBookPrice(result.getString(BOOK_PRICE));
					bookObjectFromDataBase.setBookDescription(result.getString(BOOK_DESCRIPTION));
					bookObjectFromDataBase.setBookReleaseDate(result.getString(BOOK_RELEASE_DATE));

					bookObjectFromDataBase.setBookCategorie(new Categorie());
					bookObjectFromDataBase.getBookCategorie().setId(result.getInt(CATEGORIE_ID));

					listOfBooks.add(bookObjectFromDataBase);

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

		return listOfBooks;
	}

	@Override
	public Book getBookById(int bookId) throws DaoException {

		final int QUERY_POSITION_BOOK_ID = 1;

		final int ID_BOOK = 1;

		final int BOOK_NAME = 2;

		final int BOOK_PRICE = 3;

		final int BOOK_DESCRIPTION = 4;

		final int BOOK_RELEASE_DATE = 5;

		final int CATEGORIE_ID = 6;

		Book bookObjectFromDataBase = null;

		Connection connectionToDataBase = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {

			connectionToDataBase = super.dataSource.getConnection();

			preparedStatement = connectionToDataBase
					.prepareStatement(super.managerSQL.getPreparedSqlRequest(ManagerSQL.SQL_GET_BOOK));

			preparedStatement.setInt(QUERY_POSITION_BOOK_ID, bookId);
			result = preparedStatement.executeQuery();

			if (result.next()) {

				bookObjectFromDataBase = new Book();

				do {

					bookObjectFromDataBase.setId(result.getInt(ID_BOOK));
					bookObjectFromDataBase.setBookName(result.getString(BOOK_NAME));
					bookObjectFromDataBase.setBookPrice(result.getString(BOOK_PRICE));
					bookObjectFromDataBase.setBookDescription(result.getString(BOOK_DESCRIPTION));
					bookObjectFromDataBase.setBookReleaseDate(result.getString(BOOK_RELEASE_DATE));

					bookObjectFromDataBase.setBookCategorie(new Categorie());
					bookObjectFromDataBase.getBookCategorie().setId(result.getInt(CATEGORIE_ID));

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

		return bookObjectFromDataBase;
	}

	@Override
	public void addNewBook(Book book) throws DaoException {

		final int QUERY_POSITION_BOOK_NAME = 1;

		final int QUERY_POSITION_BOOK_PRICE = 2;

		final int QUERY_POSITION_BOOK_DESCIPTION = 3;

		final int QUERY_POSITION_BOOK_RELEASE_DATE = 4;

		final int QUERY_POSITION_ID_CATEGORIE = 5;

		Connection connectionToDataBase = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {

			connectionToDataBase = super.dataSource.getConnection();

			preparedStatement = connectionToDataBase
					.prepareStatement(super.managerSQL.getPreparedSqlRequest(ManagerSQL.SQL_ADD_NEW_BOOK));

			preparedStatement.setString(QUERY_POSITION_BOOK_NAME, book.getBookName());
			preparedStatement.setInt(QUERY_POSITION_BOOK_PRICE,Integer.parseInt(book.getBookPrice()));
			preparedStatement.setString(QUERY_POSITION_BOOK_DESCIPTION, book.getBookReleaseDate());
			preparedStatement.setString(QUERY_POSITION_BOOK_RELEASE_DATE, book.getBookReleaseDate());
			preparedStatement.setInt(QUERY_POSITION_ID_CATEGORIE, book.getBookCategorie().getId());

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

	@Override
	public void updateBookName(Book book) throws DaoException {

		final int QUERY_POSITION_BOOK_NAME = 1;

		final int QUERY_POSITION_BOOK_ID = 2;

		Connection connectionToDataBase = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {

			connectionToDataBase = super.dataSource.getConnection();

			preparedStatement = connectionToDataBase
					.prepareStatement(super.managerSQL.getPreparedSqlRequest(ManagerSQL.SQL_UPDATE_BOOK_NAME));

			preparedStatement.setString(QUERY_POSITION_BOOK_NAME, book.getBookName());
			preparedStatement.setInt(QUERY_POSITION_BOOK_ID, book.getId());

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

	}

	@Override
	public void updateBookDescription(Book book) throws DaoException {

		final int QUERY_POSITION_BOOK_DESCRIPTION = 1;

		final int QUERY_POSITION_BOOK_ID = 2;

		Connection connectionToDataBase = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {

			connectionToDataBase = super.dataSource.getConnection();

			preparedStatement = connectionToDataBase
					.prepareStatement(super.managerSQL.getPreparedSqlRequest(ManagerSQL.SQL_UPDATE_BOOK_DESCRIPTION));

			preparedStatement.setString(QUERY_POSITION_BOOK_DESCRIPTION, book.getBookDescription());
			preparedStatement.setInt(QUERY_POSITION_BOOK_ID, book.getId());

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

	}

}
