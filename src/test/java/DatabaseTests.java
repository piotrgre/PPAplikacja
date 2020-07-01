import static java.sql.DriverManager.getConnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import dao.AnswerRecordHsqlDAO;

public class DatabaseTests {

	private static final String DBDESC = "jdbc:hsqldb:file:testdb";

	@BeforeAll
	public static void prepareDatabase() {
		try (Connection c = getConnection(DBDESC, "SA", "")) {
			c.createStatement().executeQuery("DELETE FROM SURVEYANSWERS");
			try (PreparedStatement ps = c
					.prepareStatement("INSERT INTO SURVEYANSWERS (QUESTION, ANSWER) VALUES (?, ?)")) {
				ps.setString(1, "a");
				ps.setString(2, "a");
				ps.execute();
				ps.setString(1, "a");
				ps.setString(2, "c");
				ps.execute();
				ps.setString(1, "b");
				ps.setString(2, "b");
				ps.execute();
				ps.setString(1, "b");
				ps.setString(2, "b");
				ps.execute();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@AfterAll
	public static void destroyDatabase() {
		try (Connection c = getConnection(DBDESC, "SA", "")) {
			c.createStatement().execute("DROP TABLE SurveyAnswers");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Test
	public void testFetchRecordsByQuestionAndAnswer() {
		int manual = 0;
		AnswerRecordHsqlDAO rec = new AnswerRecordHsqlDAO();

		int method = rec.fetchRecordsByQuestionAndAnswer("a", "c").size();

		try (Connection c = getConnection(DBDESC, "SA", "")) {
			try (ResultSet rs = c.createStatement()
					.executeQuery("SELECT * FROM SURVEYANSWERS WHERE (QUESTION = 'a' AND ANSWER = 'c')")) {
				while (rs.next()) {
					manual++;
				}
			}
			Assertions.assertEquals(method, manual);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testLoadDatabaseFromFile() {
		int check = 0;
		try (Connection c = getConnection("jdbc:hsqldb:file:mydb;if_exists=true;", "SA", "")) {
			DatabaseMetaData dbm = c.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "SURVEYANSWERS", null);
			if (tables.next()) {
				check = 1;
			}
			c.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		Assertions.assertEquals(check, 1);

	}

	@Test
	public void testFetchRecordsByQuestion() {
		int manual = 0;
		AnswerRecordHsqlDAO rec = new AnswerRecordHsqlDAO();
		try (Connection c = getConnection(DBDESC, "SA", "")) {
			try (ResultSet rs = c.createStatement().executeQuery("SELECT * FROM SURVEYANSWERS WHERE QUESTION = 'a'")) {
				while (rs.next()) {
					manual++;
				}
			}
			int method = rec.fetchRecordsByQuestion("a").size();
			Assertions.assertEquals(method, manual);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
