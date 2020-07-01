package controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import application.Main;
import dao.NonTestAnswerRecordHSQLDAO;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.AnswerRecord;
import model.Question;

public class SurveyScreenController {

	public static void finishSurvey() {
		
		Stage stage = Main.getStage();
		NonTestAnswerRecordHSQLDAO answerDAO = new NonTestAnswerRecordHSQLDAO();

		try {
			Main.getStage();
			ArrayList<Question> questions = HomeScreenController.getQp().getQuestionList();
			
			for (int i = 0; i < questions.size(); i++) {
				String answer = questions.get(i).getToggleGroup().getSelectedToggle().toString();

			}
			
			for (int i = 0; i < questions.size(); i++) {
				String answer = questions.get(i).getToggleGroup().getSelectedToggle().toString();
				String question = questions.get(i).getName().getText();
				AnswerRecord an = new AnswerRecord(question,Character.toString(answer.charAt(answer.length()-2)));
				answerDAO.insertRecord(an);
	
			}

			try {
				Object s = SurveyScreenController.class.getClassLoader();
				BorderPane root = (BorderPane) FXMLLoader.load(((ClassLoader) s).getResource("SummaryScreen.fxml"));
				Scene scene = new Scene(root, 700, 400);
				stage.setScene(scene);

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (NullPointerException e) {
			Stage stage1 = new Stage();
			VBox vBox = new VBox();
			Label label = new Label("Please answer all questions");
			vBox.setAlignment(Pos.CENTER);
			vBox.getChildren().add(label);
			Scene scene = new Scene(vBox);
			stage1.setScene(scene);
			stage1.show();

			TimerTask timerTask = new TimerTask() {
				@Override
				public void run() {
					Platform.runLater(()->{
						stage1.close();

					});
				}
			};

			Timer timer = new Timer();
			timer.schedule(timerTask,2000);

		}
		
		for( int i=0; i<answerDAO.fetchAllRecords().size();i++) {
			System.out.println("Question: "+answerDAO.fetchAllRecords().get(i).getQuestion()+", Answer:"+answerDAO.fetchAllRecords().get(i).getAnswer()+"  ");
		}
		answerDAO.printAllIds();


	}
}
