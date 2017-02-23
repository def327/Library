package by.zyablov.library.events;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.zyablov.library.datasource.DataBaseManager;
import by.zyablov.library.datasource.tools.ManagerSQL;
import by.zyablov.library.exceptions.DaoException;
import by.zyablov.library.services.AppServiceServletContext;

public class AppContextEvent implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		DataBaseManager.getInstance().closeDataBaseManager();
		ManagerSQL.getInstance().closeManagerSql();

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		try {

			ServletContext context = arg0.getServletContext();
			AppServiceServletContext.setServLetContextObjectCategoriesList(context);
			AppServiceServletContext.setServLetContextObjectBooksList(context);

		} catch (DaoException e) {
			// Logging
		}
	}

}
