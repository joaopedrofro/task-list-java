package application;

import java.io.IOException;

import model.entities.TaskManager;

public class TaskList {

	public static void main(String[] args) throws IOException {
		TaskManager taskManager = new TaskManager();
		UI.mainMenu(taskManager);
	}

}
