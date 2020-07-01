package dao;

import java.util.List;

import model.AnswerRecord;

public interface AnswerRecordDAO {	
    public List<AnswerRecord> fetchRecordsByQuestionAndAnswer(String question, String answer);
    public List<AnswerRecord> fetchRecordsByQuestion(String question);
}
