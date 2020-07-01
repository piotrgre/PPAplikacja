package database;

import java.sql.*;

public class DatabaseLogic {

	public static final String DB_LOCATION = "jdbc:hsqldb:file:testdb;if_exists=true;";

	public static interface RunnableWithConnection {
		public void run(Connection c) throws SQLException;
	}

	public void runWithConnection(RunnableWithConnection rc) {
		try (Connection c = DriverManager.getConnection(DB_LOCATION, "SA", "")) {
			rc.run(c);
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
