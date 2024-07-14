package application;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import database.DataBase;
import model.dao.TaskDao;
import model.dao.factory.DaoFactory;
import model.entities.Task;

public class TaskList {

	public static void main(String[] args) throws IOException {
		Connection conn = DataBase.getConnection();
		
		TaskDao taskDao = DaoFactory.getTaskDao(conn);
		
		List<Task> tasks = taskDao.getAll();
		
		tasks.forEach(System.out::println);
	}

}
