package model;


public class AnswerRecord {
	
	protected String question;
	protected String answer;
	
	public AnswerRecord(String q, String a) {
		this.question=q;
		this.answer=a;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	
}


