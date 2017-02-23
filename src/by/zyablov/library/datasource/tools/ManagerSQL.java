package by.zyablov.library.datasource.tools;

import java.util.ResourceBundle;

/**
 * Class {@code ManagerSQL} provides methods to get prepared SQL requests from
 * propertie file.
 * 
 * @author Дмитрий
 *
 */
public class ManagerSQL implements ManagerSQLBehavior {

	/**
	 * A name of propertie file with prepared SQL requests.
	 */
	private static final String BUNDLE_NAME = "sql_requests";

	// AuthorityType DAO
	public static final String SQL_GET_AUTHORITY_TYPE = "SQL_GET_AUTHORITY_TYPE";

	// User DAO
	public static final String SQL_GET_USER = "SQL_GET_USER";
	public static final String SQL_ADD_NEW_USER = "SQL_ADD_NEW_USER";

	// Categorie DAO
	public static final String SQL_GET_CATEGORIE = "SQL_GET_CATEGORIE";

	// Book DAO
	public static final String SQL_GET_BOOK_LIST = "SQL_GET_BOOK_LIST";
	public static final String SQL_GET_BOOK = "SQL_GET_BOOK";
	public static final String SQL_ADD_NEW_BOOK = "SQL_ADD_NEW_BOOK";
	public static final String SQL_UPDATE_BOOK_NAME = "SQL_UPDATE_BOOK_NAME";
	public static final String SQL_UPDATE_BOOK_DESCRIPTION = "SQL_UPDATE_BOOK_DESCRIPTION";

	/**
	 * An instance of {@code ManagerSQL} object.
	 */
	private static volatile ManagerSQL managerSql;

	/**
	 * A instance of {@code ResourceBundle} object.
	 */
	private static volatile ResourceBundle bundle;

	/**
	 * Return's an instance of {@code ManagerSQL} object.
	 * 
	 * @return A {@code ManagerSQL} object.
	 */
	public static ManagerSQL getInstance() {

		ManagerSQL localInstance = managerSql;

		if (localInstance == null) {

			synchronized (ManagerSQL.class) {
				localInstance = managerSql;

				if (localInstance == null) {
					managerSql = localInstance = new ManagerSQL();
					bundle = ResourceBundle.getBundle(BUNDLE_NAME);
				}
			}
		}
		return localInstance;
	}

	/**
	 * Returns a prepared SQL request from property file.
	 */
	@Override
	public String getPreparedSqlRequest(String requestName) {
		return bundle.getString(requestName);
	}

	/**
	 * Shutdown a SQL manager.
	 */
	public void closeManagerSql() {
		ManagerSQL.managerSql = null;
		ManagerSQL.bundle = null;
	}

	/**
	 * Constructs a default {@code ManagerSQL}.
	 */
	private ManagerSQL() {
	}

}
