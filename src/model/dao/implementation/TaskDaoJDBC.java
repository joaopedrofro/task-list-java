package model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DataBase;
import model.dao.TaskDao;
import model.entities.Task;

public class TaskDaoJDBC implements TaskDao {

	private Connection conn;

	public TaskDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Integer insert(Task t) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("INSERT INTO tasks (title, date, done) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, t.getTitle());
			st.setDate(2, new java.sql.Date(t.getDate().getTime()));
			st.setInt(3, t.isDone() ? 1 : 0);

			int rowsAffected = st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			
			if (rowsAffected > 0) {
				return rs.getInt(1);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.closeStatement(st);
		}
		
		return null;
	}

	@Override
	public void update(Task t) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("UPDATE tasks SET title=?, date=?, done=? WHERE id = ?");

			st.setString(1, t.getTitle());
			st.setDate(2, new java.sql.Date(t.getDate().getTime()));
			st.setInt(3, t.isDone() ? 1 : 0);
			st.setInt(4, t.getID());

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.closeStatement(st);
		}
	}

	@Override
	public void delete(Task t) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("DELETE FROM tasks WHERE id = ?");
			st.setInt(1, t.getID());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.closeStatement(st);
		}
	}

	@Override
	public List<Task> getAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Task> taskList = null;

		try {
			st = conn.prepareStatement("SELECT * from tasks ORDER BY date");
			rs = st.executeQuery();

			taskList = new ArrayList<Task>();

			while (rs.next()) {
				Task t = instantiateTask(rs);
				taskList.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.closeStatement(st);
			DataBase.closeResultSet(rs);
		}

		return taskList;
	}

	@Override
	public Task getById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		Task t = null;

		try {
			st = conn.prepareStatement("SELECT * from tasks WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next())
				t = instantiateTask(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.closeStatement(st);
			DataBase.closeResultSet(rs);
		}

		return t;
	}

	private static Task instantiateTask(ResultSet rs) throws SQLException {
		Task t = new Task();

		t.setID(rs.getInt(1));
		t.setTitle(rs.getString(2));
		t.setDate(rs.getDate(3));

		int done = rs.getInt(4);
		if (done == 1)
			t.done();

		return t;
	}

}
