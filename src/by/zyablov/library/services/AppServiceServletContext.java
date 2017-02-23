package by.zyablov.library.services;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import by.zyablov.library.beans.Book;
import by.zyablov.library.beans.Categorie;
import by.zyablov.library.dao.DaoBook;
import by.zyablov.library.dao.DaoCategorie;
import by.zyablov.library.dao.interfaces.DaoBehaviorBook;
import by.zyablov.library.dao.interfaces.DaoBehaviorCategorie;
import by.zyablov.library.exceptions.DaoException;

public class AppServiceServletContext {

	private static final int DEFAULT_CATEGORIES_AMOUNT = 0;
	private static final String BOOKS_LIST_SERVLET_CONTEXT_ATRIBUTE = "booksList";
	private static final String BOOKS_COUNT_SERVLET_CONTEXT_ATRIBUTE = "booksCount";
	private static final String CATEGORIES_LIST_SERVLET_CONTEXT_ATRIBUTE = "categoriesList";
	private static final String CATEGORIES_COUNT_SERVLET_CONTEXT_ATRIBUTE = "categoriesCount";

	public static void setServLetContextObjectCategoriesList(ServletContext context) throws DaoException {

		DaoBehaviorCategorie dao = new DaoCategorie();
		List<Categorie> categoriesList = dao.getListOfCategories();
		int categoriesCount = DEFAULT_CATEGORIES_AMOUNT;

		if (categoriesList == null) {
			context.setAttribute(CATEGORIES_COUNT_SERVLET_CONTEXT_ATRIBUTE, categoriesCount);

		} else {
			categoriesCount = categoriesList.size();
			context.setAttribute(CATEGORIES_COUNT_SERVLET_CONTEXT_ATRIBUTE, categoriesCount);
			context.setAttribute(CATEGORIES_LIST_SERVLET_CONTEXT_ATRIBUTE, categoriesList);
		}

	}

	public static void setServLetContextObjectBooksList(ServletContext context) throws DaoException {
		DaoBehaviorBook dao = new DaoBook();
		List<Book> booksList = dao.getListOfBooks();
		int booksCount = 0;

		if (booksList == null) {
			context.setAttribute(BOOKS_COUNT_SERVLET_CONTEXT_ATRIBUTE, booksCount);

		} else {
			booksCount = booksList.size();
			context.setAttribute(BOOKS_COUNT_SERVLET_CONTEXT_ATRIBUTE, booksCount);
			setCategoriesForBookList(booksList);
			context.setAttribute(BOOKS_LIST_SERVLET_CONTEXT_ATRIBUTE, booksList);
		}
	}

	private static void setCategoriesForBookList(List<Book> booksList) throws DaoException {
		DaoBehaviorCategorie dao = new DaoCategorie();

		for (Iterator<Book> iterator = booksList.iterator(); iterator.hasNext();) {
			Book book = (Book) iterator.next();

			Categorie categorie = dao.getCategorieById(book.getBookCategorie().getId());

			book.setBookCategorie(categorie);
		}
	}

}
