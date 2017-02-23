package by.zyablov.library.commands;

import javax.servlet.http.HttpServletRequest;

import by.zyablov.library.beans.AuthorityType;
import by.zyablov.library.beans.User;
import by.zyablov.library.dao.DaoUser;
import by.zyablov.library.dao.interfaces.DaoBehaviorUser;
import by.zyablov.library.exceptions.DaoException;

public class CommandRegistration implements CommandBehavior {

	@Override
	public String execute(HttpServletRequest request) {

		String userFirstName = request.getParameter("firstName");
		String userLastName = request.getParameter("lastName");
		String userEmail = request.getParameter("email");
		String userPassword = request.getParameter("password");

		User newUser = new User();
		newUser.setFirstName(userFirstName);
		newUser.setLastName(userLastName);
		newUser.setEmail(userEmail);
		newUser.setPassword(userPassword);

		// default AUTHORUTY TYPE = 1
		newUser.setAuthorityType(new AuthorityType());
		newUser.getAuthorityType().setId(1);

		DaoBehaviorUser dao = new DaoUser();

		try {
			dao.addNewUserClient(newUser);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "/jsp/main.jsp";
	}

}
