package application;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import model.entities.Task;

public class TaskManager {
	private List<Task> taskList = new ArrayList<>();
	private static Integer numberOfCreatedTasks = 0;
	
	public void createTask(String title) throws TaskCreationException {
		int newId = numberOfCreatedTasks + 1;
		taskList.add(new Task(title, newId));
		numberOfCreatedTasks++;
	}
	
	public void deleteTask(Integer id) throws TaskException {
		Task task = getTaskById(id);
		taskList.remove(task);
	}
	
	public void renameTask(Integer id, String newTitle) throws TaskException {
		Task task = getTaskById(id);
		task.setTitle(newTitle);
	}
	
	public void completeTask(Integer id) throws TaskException {
		Task task = getTaskById(id);
		task.complete();
	}
	
	public void uncompleteTask(Integer id) throws TaskException {
		Task task = getTaskById(id);
		task.uncomplete();
	}
	
	private Task getTaskById(Integer id) throws TaskException {
		try {
			return taskList.stream().filter(x -> x.getID() == id).findFirst().get();
		} catch (NoSuchElementException e) {
			throw new TaskException("ID inválido!");
		}
	}
	
	public Boolean taskExists(Integer id) {
		return taskList.stream().filter(x -> x.getID() == id).findFirst().isPresent();
	}
	
	public List<Task> getAllTasks() {
		return taskList;
	}
	
	public List<Task> getCompletedTasks() {
		return taskList.stream().filter(x -> x.isComplete()).sorted().toList();
	}
	
	public List<Task> getUncompletedTasks() {
		return taskList.stream().filter(x -> !x.isComplete()).sorted().toList();
	}
	
	public Integer getNumberOfCreatedTasks() {
		return numberOfCreatedTasks;
	}
	
}
