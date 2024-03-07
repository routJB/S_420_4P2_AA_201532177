package org.calma.ui.laboratoire5;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SalmonController implements Initializable{

    private SoundPlayer soundPlayer;
    private SalmonModel salmonModel;
    @FXML
    private Button startBtn;
    @FXML
    private Rectangle greenBtn;
    @FXML
    private Rectangle yellowBtn;
    @FXML
    private Rectangle redBtn;
    @FXML
    private Rectangle blueBtn;

    public SalmonController() {
        this.salmonModel = new SalmonModel();
        this.soundPlayer = new SoundPlayer();
    }
    @FXML
    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == startBtn){
           this.salmonModel.start();
            displayColors(salmonModel.getSequence());
        }
    }
    @FXML
    private void userInput(MouseEvent e){
        if (!salmonModel.isGameOn()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Salmon");
            alert.setHeaderText("Appuyer sur Start!");
            alert.show();
            shake();
        }

        else{
            Rectangle clickedButton = (Rectangle) e.getSource();

            if (clickedButton == greenBtn) {
                if(this.salmonModel.checkSequence("g")){
                    generateGreenTransition().play();
                }
                else{
                    alertGameOver();
                }
            }

            else if (clickedButton == redBtn) {
                if(this.salmonModel.checkSequence("r")){
                    generateRedTransition().play();
                }
                else{
                    alertGameOver();
                }
            }

            else if (clickedButton == yellowBtn) {
                if(this.salmonModel.checkSequence("y")){
                    generateYellowTransition().play();
                }
                else{
                    alertGameOver();
                }
            }

            else if (clickedButton == blueBtn) {
                if(this.salmonModel.checkSequence("b")){
                    generateBlueTransition().play();
                }
                else{
                    alertGameOver();
                }
            }
        }
    }
    private void alertGameOver(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salmon");
        alert.setHeaderText("Game Over");
        alert.show();
    }
    public void displayColors(ArrayList<String> sequence){
        SequentialTransition displaySequence = new SequentialTransition();

        for (String color: sequence){
            SequentialTransition transition = new SequentialTransition();

            switch (color){
                case "g":
                    transition = generateGreenTransition();
                    break;

                case "r":
                    transition = generateRedTransition();
                    break;

                case "y":
                    transition = generateYellowTransition();
                    break;

                case "b":
                    transition = generateBlueTransition();

                    break;
            }

            displaySequence.getChildren().add(transition);
        }

        displaySequence.play();
    }

    private SequentialTransition generateGreenTransition(){
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.3), greenBtn);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.3), greenBtn);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);

        FadeTransition refade = new FadeTransition(Duration.seconds(0.3), greenBtn);
        refade.setToValue(1.0);

        ScaleTransition descale = new ScaleTransition(Duration.seconds(0.3), greenBtn);
        descale.setToX(1);
        descale.setToY(1);

        ParallelTransition transition = new ParallelTransition(fadeTransition, scaleTransition);
        ParallelTransition retransition = new ParallelTransition(refade, descale);

        this.soundPlayer.note_on(60);
        //this.soundPlayer.note_off(60);

        return new SequentialTransition(transition, retransition);
    }
    private SequentialTransition generateRedTransition(){
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.3), redBtn);
        fadeTransition.setAutoReverse(true);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.3), redBtn);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);

        FadeTransition refade = new FadeTransition(Duration.seconds(0.3), redBtn);
        refade.setToValue(1.0);

        ScaleTransition descale = new ScaleTransition(Duration.seconds(0.3), redBtn);
        descale.setToX(1);
        descale.setToY(1);;

        ParallelTransition transition = new ParallelTransition(fadeTransition, scaleTransition);
        ParallelTransition retransition = new ParallelTransition(refade, descale);

        this.soundPlayer.note_on(61);
        //this.soundPlayer.note_off(60);

        return new SequentialTransition(transition, retransition);
    }
    private SequentialTransition generateYellowTransition(){
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.3), yellowBtn);
        fadeTransition.setAutoReverse(true);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.3), yellowBtn);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);

        FadeTransition refade = new FadeTransition(Duration.seconds(0.3), yellowBtn);
        refade.setToValue(1.0);

        ScaleTransition descale = new ScaleTransition(Duration.seconds(0.3), yellowBtn);
        descale.setToX(1);
        descale.setToY(1);;

        ParallelTransition transition = new ParallelTransition(fadeTransition, scaleTransition);
        ParallelTransition retransition = new ParallelTransition(refade, descale);

        this.soundPlayer.note_on(62);
        //this.soundPlayer.note_off(60);

        return new SequentialTransition(transition, retransition);
    }
    private SequentialTransition generateBlueTransition(){
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.3), blueBtn);
        fadeTransition.setAutoReverse(true);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.3), blueBtn);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);

        FadeTransition refade = new FadeTransition(Duration.seconds(0.3), blueBtn);
        refade.setToValue(1.0);

        ScaleTransition descale = new ScaleTransition(Duration.seconds(0.3), blueBtn);
        descale.setToX(1);
        descale.setToY(1);;

        ParallelTransition transition = new ParallelTransition(fadeTransition, scaleTransition);
        ParallelTransition retransition = new ParallelTransition(refade, descale);

        this.soundPlayer.note_on(63);
        //this.soundPlayer.note_off(60);

        return new SequentialTransition(transition, retransition);
    }
    private void shake(){
        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(1));
        transition1.setFromX(blueBtn.getX());
        transition1.setFromY(blueBtn.getY());

        transition1.setToX(blueBtn.getX() - 20);
        transition1.setFromY(blueBtn.getY() + 20);

        Path path = new Path();

        transition1.setNode(blueBtn);
        transition1.setAutoReverse(true);
        transition1.play();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

