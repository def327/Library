package by.zyablov.library.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.zyablov.library.beans.User;
import by.zyablov.library.dao.DaoUser;
import by.zyablov.library.dao.interfaces.DaoBehaviorUser;
import by.zyablov.library.exceptions.DaoException;

/**
 * {@code CommandUserLogin} provides methods to allow user to authorize at the
 * bank system.
 * 
 * @author Дмитрий
 *
 */
public class CommandUserLogin implements CommandBehavior {

	private static final String MAIN_PAGE = "/index.jsp";
	private static final String JSP_CLIENT_PROFILE_PAGE = "/jsp/client_profile.jsp";
	private static final String JSP_ADMIN_PROFILE_PAGE = "/jsp/admin_profile.jsp";
	private static final int CLIENT_AUTHORITY_TYPE = 1;
	private static final int ADMIN_AUTHORITY_TYPE = 2;

	@Override
	public String execute(HttpServletRequest request) {

		String emailUserToCheck = request.getParameter("email");
		String passwordUserToCheck = request.getParameter("password");

		DaoBehaviorUser dao = new DaoUser();

		try {

			User userFromDataSource = dao.getUserByEmail(emailUserToCheck);

			if (userFromDataSource != null) {

				boolean authenticationStatus = authenticateUser(userFromDataSource, passwordUserToCheck);

				if (authenticationStatus) {

					return getPageByUserAuthorityType(userFromDataSource, request);

				} else {

					// TODO set message to http response correct your login of
					// password user

					return MAIN_PAGE;

				}

			}

		} catch (DaoException e) {
			e.printStackTrace();
		}
		return MAIN_PAGE;
	}

	/**
	 * Return's an http page path for user by its authority type.
	 *
	 * @param userFromDataSource
	 *            An initialized {@code User} object from datasource.
	 * @param request
	 * @throws DaoException
	 */
	private String getPageByUserAuthorityType(User userFromDataSource, HttpServletRequest request) throws DaoException {

		int userAuthorityType = userFromDataSource.getAuthorityType().getId();

		switch (userAuthorityType) {

		case CLIENT_AUTHORITY_TYPE: {

			setAttributeElementsToSession(userFromDataSource, request);
			return JSP_CLIENT_PROFILE_PAGE;
		}

		case ADMIN_AUTHORITY_TYPE: {

			return JSP_ADMIN_PROFILE_PAGE;
		}

		default: {
			return MAIN_PAGE;
		}
		}
	}

	private void setAttributeElementsToSession(User userFromDataSource, HttpServletRequest request)
			throws DaoException {
		HttpSession session = request.getSession(true);
		session.setAttribute("user", userFromDataSource);
	}

	/**
	 * Authenticates user's password.
	 * 
	 * @param userFromDataSource
	 *            A {@code User} object from data source.
	 * 
	 * @param passwordUserToCheck
	 *            A user's password.
	 * 
	 * @return Status of authentication
	 */
	private boolean authenticateUser(User userFromDataSource, String passwordUserToCheck) {
		return userFromDataSource.getPassword().equals(passwordUserToCheck);
	}

}
