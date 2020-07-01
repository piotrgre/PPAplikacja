package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import database.DatabaseLogic.RunnableWithConnection;

public class NonTestDatabaseLogic {
	
	public static final String DB_LOCATION = "jdbc:hsqldb:file:mydb;if_exists=true;hsqldb.lock_file=false";
		
	public void runWithConnection(RunnableWithConnection rc) {
		try (Connection c = DriverManager.getConnection(DB_LOCATION, "SA", "")) {
			rc.run(c);
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	


}
