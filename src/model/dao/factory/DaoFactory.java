package model.dao.factory;

import java.sql.Connection;

import model.dao.TaskDao;
import model.dao.implementation.TaskDaoJDBC;

public class DaoFactory {

	public static TaskDao getTaskDao(Connection conn) {
		return new TaskDaoJDBC(conn);
	}
	
}
