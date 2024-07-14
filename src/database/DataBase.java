package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataBase {

	private static Connection conn;
	
	public static Connection getConnection() {
		if (conn != null) {
			return conn;
		} else {
			Properties props = loadProperties();
			String url = props.getProperty("url");
			try {
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	private static Properties loadProperties() {
		Properties props = new Properties();
		
		try {
			props.load(new FileInputStream("src/db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return props;
	}
	
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
