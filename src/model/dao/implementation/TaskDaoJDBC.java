package model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
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
	public void insert(Task e) {

	}

	@Override
	public void update(Task e) {

	}

	@Override
	public void delete(Task e) {

	}

	@Override
	public List<Task> getAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		HashSet<Task> set = null;

		try {
			st = conn.prepareStatement("SELECT * from tasks ORDER BY date");
			rs = st.executeQuery();

			set = new HashSet<Task>();

			while (rs.next()) {
				Task t = new Task();

				t.setID(rs.getInt(1));
				t.setTitle(rs.getString(2));
				t.setDate(rs.getDate(3));

				int done = rs.getInt(4);
				if (done == 1)
					t.done();

				set.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.closeStatement(st);
			DataBase.closeResultSet(rs);
		}

		return new ArrayList<Task>(set);
	}

	@Override
	public Task getById() {
		return null;
	}

}
