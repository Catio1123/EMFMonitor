package DAO;

import DAO.TES1394DAO.TES1394DAO;
import DAO.TES1394DAO.TES1394DAOImpl.TES1394DAOImpl;



public class DAOFactory {
	
	public static TES1394DAO getTES1394DAO() {
		return new TES1394DAOImpl();
	}
}
