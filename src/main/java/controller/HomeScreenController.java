package controller;

import application.Data;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.QuestionPane;

public class HomeScreenController {

	@FXML
	protected Button startSurvey;

	protected static QuestionPane qp;

	@FXML
	private void startSurvey() {

		qp = new QuestionPane(0,5);
		qp.setQuestionList(Data.getLoadedQuestions());
		Pagination pagination;
		double numberOfPages = (double)qp.getQuestionList().size() / (double)qp.getItemsPerPage();

		if(numberOfPages!=(int)numberOfPages) {

			pagination = new Pagination((int)(numberOfPages+1), 0);
			pagination.setMaxPageIndicatorCount((int)(numberOfPages)+1);


		}
		else{

			pagination = new Pagination((int)numberOfPages, 0);
			pagination.setMaxPageIndicatorCount((int)numberOfPages);

		}

		pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
		pagination.setPageFactory(new Callback<Integer, Node>() {

			@Override
			public Node call(Integer pageIndex) {
				return qp.createPage(pageIndex);
			}
		});

		AnchorPane anchor = new AnchorPane();
		AnchorPane.setTopAnchor(pagination, 10.0);
		AnchorPane.setRightAnchor(pagination, 10.0);
		AnchorPane.setBottomAnchor(pagination, 10.0);
		AnchorPane.setLeftAnchor(pagination, 10.0);
		anchor.getChildren().addAll(pagination);
		Stage stage = Main.getStage();
		double windowHeight = 200 *qp.getItemsPerPage();
		Scene scene = new Scene(anchor,400, windowHeight);
		stage.setScene(scene);
		stage.show();
		scene.getStylesheets().add("/application.css");
	}

	public static QuestionPane getQp() {
		return qp;
	}
}