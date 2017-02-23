package by.zyablov.library.dao.interfaces;

import by.zyablov.library.beans.AuthorityType;
import by.zyablov.library.exceptions.DaoException;

public interface DaoBehaviorAuthorityType {
	
	AuthorityType getAuthorityTypeById(int authorityTypeId) throws DaoException;

}
