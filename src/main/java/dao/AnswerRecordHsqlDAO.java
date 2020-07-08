package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseLogic;
import database.NonTestDatabaseLogic;
import model.AnswerRecord;



public class AnswerRecordHsqlDAO implements AnswerRecordDAO{
	
	protected DatabaseLogic db;
	protected String dbName;
	
	public AnswerRecordHsqlDAO(DatabaseLogic db) {
		this.db = db;
		this.dbName=db.getDbName();
	}

	@Override
	public List<AnswerRecord> fetchRecordsByQuestionAndAnswer(String question, String answer) {
        List<AnswerRecord> retVal = new ArrayList<>();
        db.runWithConnection(c -> {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM " +this.dbName+ " WHERE Question=? AND Answer=?")) {
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
		AnswerRecord record = new AnswerRecord(rs.getString("Question"),rs.getNString("Answer"));
        return record;
    }




	@Override
	public List<AnswerRecord> fetchRecordsByQuestion(String question) {
        List<AnswerRecord> retVal = new ArrayList<>();
        db.runWithConnection(c -> {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM " +this.dbName+ " WHERE QUESTION=?")) {
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
	
	public void insertRecord(AnswerRecord record) {
		
	String question = record.getQuestion();
	String answer = record.getAnswer();
	db.runWithConnection(c->{
		try(PreparedStatement ps =c.prepareStatement("INSERT INTO " +this.dbName+ " (QUESTION, ANSWER) "+"VALUES (?,?)")){
			ps.setString(1, question);
			ps.setString(2, answer);
			ps.execute();
		}catch(SQLException e ) {
			throw new RuntimeException(e);
		}
	});
	
	}
	
	public List<AnswerRecord> fetchAllRecords() {
        List<AnswerRecord> retVal = new ArrayList<>();
        db.runWithConnection(c -> {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM " +this.dbName)) {
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
	
	//nizej metody pomocnicze do tworzenia tabel, zczytywania liczby i kasowania rekordow
	public void printAllIds() {
        db.runWithConnection(c -> {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM " +this.dbName)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        System.out.println(rs.getInt(1));
                    }
                }
            }
        });
		
	}
	
	public void deleteAll() {
        db.runWithConnection(c -> {
            try (PreparedStatement ps = c.prepareStatement("DELETE FROM " +this.dbName)) {
            	ps.execute();
            	PreparedStatement pss = c.prepareStatement("ALTER TABLE " +this.dbName+ " ALTER COLUMN ID RESTART WITH 1 ");
            	pss.execute();
            }
        });
		
	}
		
//		public void createTable() {
//			db.runWithConnection(c->{
//				try(PreparedStatement ps =c.prepareStatement("CREATE TABLE SURVEYDATA (ID INT PRIMARY KEY IDENTITY, QUESTION VARCHAR(255), ANSWER VARCHAR(255))")){
//					ps.execute();
//				}catch(SQLException e ) {
//					throw new RuntimeException(e);
//				}
//			});
//			
//		}


}
