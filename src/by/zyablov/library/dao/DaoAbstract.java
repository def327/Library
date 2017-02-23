package by.zyablov.library.dao;

import by.zyablov.library.datasource.DataBaseManager;
import by.zyablov.library.datasource.DataSourceBehavior;
import by.zyablov.library.datasource.tools.ManagerSQL;
import by.zyablov.library.datasource.tools.ManagerSQLBehavior;

/**
 * Class {@code DaoAbstract} provides methods to work with datasource.
 * 
 * @author Дмитрий
 * 
 * @see DataSourceBehavior
 *
 */
public abstract class DaoAbstract {

	/**
	 * A datasource for objects.
	 */
	protected DataSourceBehavior dataSource;

	/**
	 * A manager for SQL requests.
	 */
	protected ManagerSQLBehavior managerSQL;

	/**
	 * Constructs a default <code>DaoAbstract</code> with an initialized
	 * datasource and SQL-request manager.
	 */
	public DaoAbstract() {
		this.dataSource = DataBaseManager.getInstance();
		this.managerSQL = ManagerSQL.getInstance();
	}

}
