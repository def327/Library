package by.zyablov.library.dao;

import java.util.List;

import by.zyablov.library.beans.Book;
import by.zyablov.library.dao.interfaces.DaoBehaviorBook;
import by.zyablov.library.exceptions.DaoException;

public class DaoBook extends DaoAbstract implements DaoBehaviorBook {

	@Override
	public List<Book> getListOfBooks() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBookById(int bookId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewBook(Book book) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBookName(Book book) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBookDescription(Book book) throws DaoException {
		// TODO Auto-generated method stub

	}

}
