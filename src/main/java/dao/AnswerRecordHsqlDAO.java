package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseLogic;
import model.AnswerRecord;



public class AnswerRecordHsqlDAO implements AnswerRecordDAO{

	@Override
	public List<AnswerRecord> fetchRecordsByQuestionAndAnswer(String question, String answer) {
		DatabaseLogic dl = new DatabaseLogic();
        List<AnswerRecord> retVal = new ArrayList<>();
        dl.runWithConnection(c -> {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM SurveyAnswers WHERE Question=? AND Answer=?")) {
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
		AnswerRecord record = new AnswerRecord("Question","Answer");

        return record;
    }




	@Override
	public List<AnswerRecord> fetchRecordsByQuestion(String question) {
		DatabaseLogic dl = new DatabaseLogic();
        List<AnswerRecord> retVal = new ArrayList<>();
        dl.runWithConnection(c -> {
            try (PreparedStatement ps = c.prepareStatement("SELECT * FROM SURVEYANSWERS WHERE QUESTION=?")) {
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


}
