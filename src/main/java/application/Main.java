package application;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

	private static Stage stage;

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		Main.stage = stage;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Data data = new Data();
			data.load("data.txt");
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/HomeScreen.fxml"));
			Scene scene = new Scene(root, 700, 400);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Survey");
			primaryStage.show();
			setStage(primaryStage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
