package view;

import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Question extends VBox {
    private Text name = new Text();
    private RadioButton answer1 = new RadioButton();
    private RadioButton answer2 = new RadioButton();
    private RadioButton answer3 = new RadioButton();
    private RadioButton answer4 = new RadioButton();
    private boolean amIfirst = true;
    private ToggleGroup toggleGroup = new ToggleGroup();


    public Question(String name, String answer1, String answer2, String answer3, String answer4) {
        super(5);
        try {

            this.name.setText(name);
            this.answer1.setText(answer1);
            this.answer2.setText(answer2);
            this.answer3.setText(answer3);
            this.answer4.setText(answer4);

            this.answer1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    doYourThing();
                }
            });

            this.answer2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    doYourThing();
                }
            });

            this.answer3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    doYourThing();
                }
            });

            this.answer4.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    doYourThing();
                }
            });


            this.getChildren().addAll(this.name, this.answer1, this.answer2, this.answer3, this.answer4);

        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("siema");

        }
    }

    public void doYourThing() {

        if (amIfirst == true) {

            this.answer1.setToggleGroup(this.toggleGroup);
            this.answer2.setToggleGroup(this.toggleGroup);
            this.answer3.setToggleGroup(this.toggleGroup);
            this.answer4.setToggleGroup(this.toggleGroup);

        }

    }

    public Text getName() {
        return name;
    }

    public void setName(Text name) {
        this.name = name;
    }

    public RadioButton getAnswer1() {
        return answer1;
    }

    public void setAnswer1(RadioButton answer1) {
        this.answer1 = answer1;
    }

    public RadioButton getAnswer2() {
        return answer2;
    }

    public void setAnswer2(RadioButton answer2) {
        this.answer2 = answer2;
    }

    public RadioButton getAnswer3() {
        return answer3;
    }

    public void setAnswer3(RadioButton answer3) {
        this.answer3 = answer3;
    }

    public RadioButton getAnswer4() {
        return answer4;
    }

    public void setAnswer4(RadioButton answer4) {
        this.answer4 = answer4;
    }

    public boolean isAmIfirst() {
        return amIfirst;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

}