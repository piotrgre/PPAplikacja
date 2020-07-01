package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import database.NonTestDatabaseLogic;
import model.AnswerRecord;

public class NonTestAnswerRecordHSQLDAO implements AnswerRecordDAO {
	
	@Override
	public List<AnswerRecord> fetchRecordsByQuestionAndAnswer(String question, String answer) {
		NonTestDatabaseLogic dl = new NonTestDatabaseLogic();
        List<AnswerRecord> retVal = new ArrayList<>();
        dl.runWithConnection(c -> {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM SURVEYDATA WHERE Question=? AND Answer=?")) {
                ps.setString(1, question);
                ps.setString(2, answer);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        AnswerRecord record = getAnswerRecordFromCursor(rs);
                        retVal.add(record);
                    }
                }
            }
        });
        return retVal;
	}
	
	private AnswerRecord getAnswerRecordFromCursor(ResultSet rs) throws SQLException {
		AnswerRecord record = new AnswerRecord(rs.getString("Question"),rs.getString("Answer"));
        return record;
    }



	@Override
	public List<AnswerRecord> fetchRecordsByQuestion(String question) {
		NonTestDatabaseLogic dl = new NonTestDatabaseLogic();
        List<AnswerRecord> retVal = new ArrayList<>();
        dl.runWithConnection(c -> {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM SURVEYDATA WHERE QUESTION=?")) {
                ps.setString(1, question);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        AnswerRecord record = getAnswerRecordFromCursor(rs);
                        retVal.add(record);
                    }
                }
            }
        });
        return retVal;
	}
	
	public List<AnswerRecord> fetchAllRecords() {
		NonTestDatabaseLogic dl = new NonTestDatabaseLogic();
        List<AnswerRecord> retVal = new ArrayList<>();
        dl.runWithConnection(c -> {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM SURVEYDATA")) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        AnswerRecord record = getAnswerRecordFromCursor(rs);
                        retVal.add(record);
                    }
                }
            }
        });
        return retVal;
	}
	
		public void insertRecord(AnswerRecord record) {
		
		String question = record.getQuestion();
		String answer = record.getAnswer();
		NonTestDatabaseLogic dl = new NonTestDatabaseLogic();
		dl.runWithConnection(c->{
			try(PreparedStatement ps =c.prepareStatement("INSERT INTO SURVEYDATA (QUESTION, ANSWER) "+"VALUES (?,?)")){
				ps.setString(1, question);
				ps.setString(2, answer);
				ps.execute();
			}catch(SQLException e ) {
				throw new RuntimeException(e);
			}
		});
		
	}
		
	//nizej metody pomocnicze do tworzenia tabel, sczytywania liczby i kasowania rekordow
	public void printAllIds() {
		
		NonTestDatabaseLogic dl = new NonTestDatabaseLogic();
        dl.runWithConnection(c -> {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM SURVEYDATA")) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        System.out.println(rs.getInt(1));
                    }
                }
            }
        });
		
	}
	
	public void deleteAll() {
		
		NonTestDatabaseLogic dl = new NonTestDatabaseLogic();
        dl.runWithConnection(c -> {
            try (PreparedStatement ps = c.prepareStatement("DELETE FROM SURVEYDATA")) {
            	ps.execute();
            	PreparedStatement pss = c.prepareStatement("ALTER TABLE SURVEYDATA ALTER COLUMN ID RESTART WITH 1 ");
            	pss.execute();
            }
        });
		
	}
		
//		public void createTable() {
//			
//			NonTestDatabaseLogic dl = new NonTestDatabaseLogic();
//			dl.runWithConnection(c->{
//				try(PreparedStatement ps =c.prepareStatement("CREATE TABLE SURVEYDATA (ID INT PRIMARY KEY IDENTITY, QUESTION VARCHAR(255), ANSWER VARCHAR(255))")){
//					ps.execute();
//				}catch(SQLException e ) {
//					throw new RuntimeException(e);
//				}
//			});
//			
//		}



}
