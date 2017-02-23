package by.zyablov.library.events;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.zyablov.library.beans.Book;
import by.zyablov.library.beans.Categorie;
import by.zyablov.library.dao.DaoBook;
import by.zyablov.library.dao.DaoCategorie;
import by.zyablov.library.dao.interfaces.DaoBehaviorBook;
import by.zyablov.library.dao.interfaces.DaoBehaviorCategorie;
import by.zyablov.library.datasource.DataBaseManager;
import by.zyablov.library.datasource.tools.ManagerSQL;
import by.zyablov.library.exceptions.DaoException;

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
			setServLetContextObjectBooksList(context);
			setServLetContextObjectCategoriesList(context);

		} catch (DaoException e) {
			// Logging
		}
	}

	private void setServLetContextObjectCategoriesList(ServletContext context) throws DaoException {
		DaoBehaviorCategorie dao = new DaoCategorie();
		List<Categorie> categoriesList = dao.getListOfCategories();
		int categoriesCount = 0;

		if (categoriesList == null) {
			context.setAttribute("categoriesCount", categoriesCount);

		} else {
			categoriesCount = categoriesList.size();
			context.setAttribute("categoriesCount", categoriesCount);
			context.setAttribute("categoriesList", categoriesList);
		}

	}

	/**
	 * @param context
	 * @throws DaoException
	 */
	private void setServLetContextObjectBooksList(ServletContext context) throws DaoException {
		DaoBehaviorBook dao = new DaoBook();
		List<Book> booksList = dao.getListOfBooks();
		int booksCount = 0;

		if (booksList == null) {
			context.setAttribute("booksCount", booksCount);

		} else {
			booksCount = booksList.size();
			context.setAttribute("booksCount", booksCount);
			setCategoriesForBookList(booksList);
			context.setAttribute("booksList", booksList);
		}
	}

	private void setCategoriesForBookList(List<Book> booksList) throws DaoException {
		DaoBehaviorCategorie dao = new DaoCategorie();

		for (Iterator<Book> iterator = booksList.iterator(); iterator.hasNext();) {
			Book book = (Book) iterator.next();

			Categorie categorie = dao.getCategorieById(book.getBookCategorie().getId());

			book.setBookCategorie(categorie);
		}
	}

}
