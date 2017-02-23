package by.zyablov.library.dao.interfaces;

import by.zyablov.library.beans.Categorie;
import by.zyablov.library.exceptions.DaoException;

public interface DaoBehaviorCategorie {

	Categorie getCategorieById(int authorityTypeId) throws DaoException;
}
