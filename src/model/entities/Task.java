package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task implements Comparable<Task> {
	private String title;
	private final Integer ID;
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private LocalDate creationDate;
	private Boolean complete;
	
	public Task(String title, Integer id) throws TaskCreationException {
		if (title.isEmpty()) {
			throw new TaskCreationException("O título da tarefa não por ser vazio!");
		}
		this.title = title;
		this.creationDate = LocalDate.now();
		this.ID = id;
		this.complete = false;
	}

	@Override
	public int compareTo(Task otherTask) {
		LocalDate otherTaskCreationDate = otherTask.getCreationDate();
		return creationDate.compareTo(otherTaskCreationDate);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) throws TaskException {
		if (title.isEmpty()) {
			throw new TaskException("O título da tarefa não por ser vazio!");
		}
		this.title = title;
	}

	public Boolean isComplete() {
		return complete;
	}

	public void complete() {
		this.complete = true;
	}
	
	public void uncomplete() {
		this.complete = false;
	}

	public Integer getID() {
		return ID;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}
	
	@Override
	public String toString() {
		if (complete) {
			return String.format("%d - [x] - %s - %s", ID, title, creationDate.format(dateFormatter));
		} else {
			return String.format("%d - [ ] - %s - %s", ID, title, creationDate.format(dateFormatter));
		}
	}
}
