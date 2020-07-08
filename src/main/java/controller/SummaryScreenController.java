package controller;

import java.util.ArrayList;
import java.util.List;

import dao.AnswerRecordHsqlDAO;
import database.NonTestDatabaseLogic;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import model.AnswerRecord;
public class SummaryScreenController {
	@FXML
	private TableView<AnswerRecord> summaryTable;
	@FXML
	private Button exitApp;
	
	
	public void initialize() {
        TableColumn<AnswerRecord, String> questionColumn = (TableColumn<AnswerRecord, String>) summaryTable.getColumns().get(0);
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        questionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        questionColumn.setOnEditCommit(edit -> {
//            Car editedCar = carTableView.getEditingCell().getTableView().getItems().get(carTableView.getEditingCell().getRow());
//            editedCar.setMake(edit.getNewValue());
//        });
        
        
        TableColumn<AnswerRecord, String> answerColumn = (TableColumn<AnswerRecord, String>) summaryTable.getColumns().get(1);
        answerColumn.setCellValueFactory(new PropertyValueFactory<>("answer"));
        answerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        answerColumn.setOnEditCommit(edit -> {
//            Car editedCar = carTableView.getEditingCell().getTableView().getItems().get(carTableView.getEditingCell().getRow());
//            editedCar.setMake(edit.getNewValue());
//        });
        
        List<AnswerRecord> answerRecords = new ArrayList<AnswerRecord>();
        AnswerRecordHsqlDAO dao = new AnswerRecordHsqlDAO(new NonTestDatabaseLogic());
        answerRecords = dao.fetchAllRecords();
        
        for(int i=answerRecords.size()-15;i<answerRecords.size();i++) {
        	summaryTable.getItems().add(answerRecords.get(i));
        }
        
	}
	
	public void close() {
	    Stage stage = (Stage) exitApp.getScene().getWindow();
	    stage.close();


	}
	

}
