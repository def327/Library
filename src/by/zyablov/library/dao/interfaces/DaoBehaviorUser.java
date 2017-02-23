package by.zyablov.library.dao.interfaces;

import by.zyablov.library.beans.User;
import by.zyablov.library.exceptions.DaoException;

public interface DaoBehaviorUser {

	User getUserByEmail(String userLogin) throws DaoException;

	void addNewUserClient(User user) throws DaoException;

}
