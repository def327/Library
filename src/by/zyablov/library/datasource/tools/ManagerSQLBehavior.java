package by.zyablov.library.datasource.tools;

/**
 * Interface {@code ManagerSQLBehavior} describes a behavior of a SQL-request
 * manager.
 * 
 * @author Дмитрий
 *
 */
public interface ManagerSQLBehavior {

	/**
	 * Return's a SQL request.
	 * 
	 * @param requestName
	 *            A name of request
	 * 
	 * @return A SQL request
	 */
	String getPreparedSqlRequest(String requestName);
	
}
