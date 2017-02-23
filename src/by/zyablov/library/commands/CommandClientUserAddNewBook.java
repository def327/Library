package by.zyablov.library.commands;

import javax.servlet.http.HttpServletRequest;

import by.zyablov.library.beans.Book;
import by.zyablov.library.beans.Categorie;
import by.zyablov.library.dao.DaoBook;
import by.zyablov.library.dao.interfaces.DaoBehaviorBook;
import by.zyablov.library.exceptions.DaoException;
import by.zyablov.library.services.AppServiceServletContext;

public class CommandClientUserAddNewBook implements CommandBehavior {

	private static final String JSP_CLIENT_PROFILE_PAGE = "/jsp/client_profile.jsp";

	@Override
	public String execute(HttpServletRequest request) {

		String bookName = request.getParameter("bookName");
		String bookPrice = request.getParameter("bookPrice");
		String bookDescription = request.getParameter("bookDescription");
		String bookReleaseDate = request.getParameter("bookReleaseDate");
		int bookCategorieId = Integer.parseInt(request.getParameter("bookCategorieId"));

		Book newBook = new Book();
		Categorie categorie = new Categorie();
		categorie.setId(bookCategorieId);

		newBook.setBookName(bookName);
		newBook.setBookPrice(bookPrice);
		newBook.setBookDescription(bookDescription);
		newBook.setBookReleaseDate(bookReleaseDate);

		newBook.setBookCategorie(categorie);

		DaoBehaviorBook dao = new DaoBook();

		try {
			dao.addNewBook(newBook);

			AppServiceServletContext.setServLetContextObjectBooksList(request.getServletContext());

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return JSP_CLIENT_PROFILE_PAGE;

	}

}
