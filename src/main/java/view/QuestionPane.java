package view;

import java.util.ArrayList;

import controller.SurveyScreenController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class QuestionPane extends VBox {

	private ArrayList<Question> questionList = new ArrayList<>();
	private int itemsPerPage;
	private Button finish = new Button();
	


	public QuestionPane(int spacing, int itemsPerPage) {
		super(spacing);
		this.itemsPerPage = itemsPerPage;
		
	}
	

	public QuestionPane createPage(int pageIndex) {
		this.getChildren().clear();
		int page = pageIndex * this.itemsPerPage;
		for (int i = page; i < page + this.itemsPerPage; i++) {
			Question question = this.questionList.get(i);
			this.getChildren().add(question);
			if (i==questionList.size()-1) {
				
				HBox buttonPane = new HBox();
				finish.setText("Finish");
				buttonPane.setAlignment(Pos.BOTTOM_CENTER);
				buttonPane.getChildren().add(finish);
				this.getChildren().add(buttonPane);
				finish.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						SurveyScreenController.finishSurvey();
					}
				});
				
				
			}
			this.setMargin(question, new Insets (15,20,15,20));
		}
		return this;

	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public int getItemsPerPage() {
		return this.itemsPerPage;
	}

	public ArrayList<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(ArrayList<Question> question_list) {
		this.questionList = question_list;
	}
	






}
