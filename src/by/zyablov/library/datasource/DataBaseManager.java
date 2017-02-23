package by.zyablov.library.datasource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import by.zyablov.library.xml.DataBaseMangerConfigParser;



/**
 * Class {@code DataBaseManager} provides methods to work with connections to
 * database.
 * 
 * <p>
 * Class {@code DataBaseManager} uses a
 * <a href="https://github.com/brettwooldridge/HikariCP">HikariCP connection
 * pool</a>.
 * 
 * <p>
 * Class {@code ConfigParser} load a default configuration for HikariCP
 * connection pool from XML file using StAX parser.
 * 
 * @author Дмитрий
 * 
 * @see DataBaseMangerConfigParser
 *
 */
public class DataBaseManager implements DataSourceBehavior {

	/**
	 * An instance of {@code DataBaseManager} object.
	 */
	private static volatile DataBaseManager dataBaseManager;

	/**
	 * A maximum pool size of connection pool.
	 */
	private static final String DATA_SOURCE_MAX_POOL_SIZE = "dataSource.maxPoolSize";

	/**
	 * A password to data base.
	 */
	private static final String DATA_SOURCE_PASSWORD = "dataSource.password";

	/**
	 * An user's name of data base.
	 */
	private static final String DATA_SOURCE_USER = "dataSource.user";

	/**
	 * An URL of data base.
	 */
	private static final String DATA_SOURCE_URL = "dataSource.url";

	/**
	 * A name of driver class to load for data base connection.
	 */
	private static final String DATA_SOURCE_DRIVER_CLASS_NAME = "dataSource.driverClassName";

	/**
	 * A name of path to XML file document.
	 */
	private static final String PATH_TO_XML_FILE = "config.xml";

	/**
	 * HikariCP pooled data source.
	 */
	private HikariDataSource dataSource;

	/**
	 * Return's an instance of {@code DataBaseManager} object.
	 * 
	 * @return A {@code DataBaseManager} object.
	 */
	public static DataBaseManager getInstance() {

		DataBaseManager localInstance = dataBaseManager;

		if (localInstance == null) {

			synchronized (DataBaseManager.class) {
				localInstance = dataBaseManager;

				if (localInstance == null) {
					dataBaseManager = localInstance = new DataBaseManager();
				}
			}
		}
		return localInstance;
	}

	/**
	 * Return's a proxy connection to database.
	 * 
	 * <p>
	 * <i> When you call method {@link Connection#close() close()} - a proxy
	 * connection returns to connection pool.</i>
	 */
	@Override
	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}

	/**
	 * Initializes a data config by parsed configuration from XML file.
	 * 
	 * @return a data config for data source
	 * 
	 * @throws NumberFormatException
	 */
	private HikariConfig initDataSourceConfig() throws NumberFormatException {

		DataBaseMangerConfigParser dataBaseMangerConfigParser = new DataBaseMangerConfigParser(PATH_TO_XML_FILE);
		HashMap<String, String> configProperties = dataBaseMangerConfigParser.getProperties();

		HikariConfig config = new HikariConfig();

		config.setDriverClassName(configProperties.get(DATA_SOURCE_DRIVER_CLASS_NAME));
		config.setJdbcUrl(configProperties.get(DATA_SOURCE_URL));
		config.setUsername(configProperties.get(DATA_SOURCE_USER));
		config.setPassword(configProperties.get(DATA_SOURCE_PASSWORD));
		config.setMaximumPoolSize(Integer.parseInt(configProperties.get(DATA_SOURCE_MAX_POOL_SIZE)));

		return config;
	}

	/**
	 * Shutdown a connection pool.
	 */
	public void closeDataBaseManager() {
		this.dataSource.close();
	}

	/**
	 * Constructs and initializes by {@code ConfigParser} a
	 * {@code DataBaseManager} object.
	 * 
	 * @see DataBaseMangerConfigParser
	 */
	private DataBaseManager() {
		this.dataSource = new HikariDataSource((HikariConfig) initDataSourceConfig());
	}

}
