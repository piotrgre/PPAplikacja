package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import view.Question;

public class Data {

	private static ArrayList<Question> loadedQuestions = new ArrayList<>();

	public void load(String fileName) throws FileNotFoundException {
		try {
			URL url = getClass().getResource("/"+fileName);
			File file = new File(url.getPath());

			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] datasplit = data.split(",");
				Question question = new Question(datasplit[0], datasplit[1], datasplit[2], datasplit[3], datasplit[4]);
				loadedQuestions.add(question);
			}
			myReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static ArrayList<Question> getLoadedQuestions() {
		return loadedQuestions;
	}

	public void setLoadedQuestions(ArrayList<Question> loadedQuestions) {
		Data.loadedQuestions = loadedQuestions;
	}
	
}
