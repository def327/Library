package by.zyablov.library.commands;

import javax.servlet.http.HttpServletRequest;

import by.zyablov.library.exceptions.DaoException;

/**
 * Interface {@code CommandBehavior} defines a behavior of a command, which
 * executes an users action.
 * 
 * 
 * @author Дмитрий
 *
 */
public interface CommandBehavior {

	/**
	 * Performs a user's action and returns a web page path to redirect with a
	 * result of a command.
	 * 
	 * @param request
	 *            A http request object from a client
	 * 
	 * @return A page's path to redirect
	 * @throws DaoException 
	 */
	String execute(HttpServletRequest request);

}
