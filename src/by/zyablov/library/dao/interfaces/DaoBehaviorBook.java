package by.zyablov.library.dao.interfaces;

import java.util.List;

import by.zyablov.library.beans.Book;
import by.zyablov.library.exceptions.DaoException;

public interface DaoBehaviorBook {

	List<Book> getListOfBooks() throws DaoException;

	Book getBookById(int bookId) throws DaoException;

	void addNewBook(Book book) throws DaoException;

	void updateBookName(Book book) throws DaoException;

	void updateBookDescription(Book book) throws DaoException;

}
