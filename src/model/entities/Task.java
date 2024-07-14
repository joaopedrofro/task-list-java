package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer ID;
	private String title;
	private LocalDate date;
	private Boolean done;
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
	
	public Task() {
		super();
	}

	public Task(Integer iD, String title, LocalDate date, Boolean done) {
		super();
		ID = iD;
		this.title = title;
		this.date = date;
		this.done = done;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Boolean isDone() {
		return done;
	}

	public void done() {
		this.done = true;
	}
	
	public void undone() {
		this.done = false;
	}

	public Integer getID() {
		return ID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(ID, other.ID);
	}

	@Override
	public String toString() {
		return "Task [ID=" + ID + ", title=" + title + ", date=" + dateFormatter.format(date) + ", done=" + done + "]";
	}

}
