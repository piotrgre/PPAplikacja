package database;

import java.sql.*;

public class DatabaseLogic {

	private String dbName = "SURVEYANSWERS";
	private final String DB_LOCATION = "jdbc:hsqldb:file:testdb;if_exists=true;";

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
	
	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbLocation() {
		return DB_LOCATION;
	}
	
	

}
