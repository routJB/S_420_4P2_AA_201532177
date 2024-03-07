package org.calma.ui.laboratoire5.Partie2;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

public class SalmonController implements Initializable{
    private SoundPlayer soundPlayer;
    private SalmonModel salmonModel;
    @FXML
    private Button startBtn;
    @FXML
    private Path pathGreenBtn;
    @FXML
    private Path pathRedBtn;
    @FXML
    private Path pathYellowBtn;
    @FXML
    private Path pathBlueBtn;
    @FXML
    private Rectangle greenBtn;
    @FXML
    private Rectangle yellowBtn;
    @FXML
    private Rectangle redBtn;
    @FXML
    private Rectangle blueBtn;
    private double BTN1_LAYOUT_X;
    private double BTN1_LAYOUT_Y;
    private double BTN2_LAYOUT_X;
    private double BTN2_LAYOUT_Y;
    private double BTN3_LAYOUT_X;
    private double BTN3_LAYOUT_Y;
    private double BTN4_LAYOUT_X;
    private double BTN4_LAYOUT_Y;
    private boolean positionsChangees = true;
    private Rectangle[] buttons;
    public SalmonController() {
        soundPlayer = new SoundPlayer();
        salmonModel = new SalmonModel();
    }
    @FXML
    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == startBtn){
            salmonModel.start();
            displayColors(salmonModel.getSequence());

            if(salmonModel.getNbCoups() % 5 == 0){
                positionsChangees = true;
            }

            if(positionsChangees){
                BTN1_LAYOUT_X = greenBtn.getLayoutX();
                BTN1_LAYOUT_Y = greenBtn.getLayoutY();
                BTN2_LAYOUT_X = redBtn.getLayoutX();
                BTN2_LAYOUT_Y = redBtn.getLayoutY();
                BTN3_LAYOUT_X = yellowBtn.getLayoutX();
                BTN3_LAYOUT_Y = yellowBtn.getLayoutY();
                BTN4_LAYOUT_X = blueBtn.getLayoutX();
                BTN4_LAYOUT_Y = blueBtn.getLayoutY();


                for (int i = buttons.length - 1; i > 0; i--) {
                    int index = (int) (Math.random() * (i + 1));
                    Rectangle temp = buttons[index];
                    buttons[index] = buttons[i];
                    buttons[i] = temp;
                }

                for (int i = 0; i < buttons.length; i++) {
                    Rectangle btn = buttons[i];
                    switch (i) {
                        case 0:
                            AnchorPane.setLeftAnchor(btn, BTN1_LAYOUT_X);
                            AnchorPane.setTopAnchor(btn, BTN1_LAYOUT_Y);
                            break;
                        case 1:
                            AnchorPane.setLeftAnchor(btn, BTN2_LAYOUT_X);
                            AnchorPane.setTopAnchor(btn, BTN2_LAYOUT_Y);
                            break;
                        case 2:
                            AnchorPane.setLeftAnchor(btn, BTN3_LAYOUT_X);
                            AnchorPane.setTopAnchor(btn, BTN3_LAYOUT_Y);
                            break;
                        case 3:
                            AnchorPane.setLeftAnchor(btn, BTN4_LAYOUT_X);
                            AnchorPane.setTopAnchor(btn, BTN4_LAYOUT_Y);
                            break;
                        default:
                            break;
                    }
                }
                positionsChangees = false;
            }
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

        AnchorPane.setLeftAnchor(greenBtn, BTN1_LAYOUT_X);
        AnchorPane.setTopAnchor(greenBtn, BTN1_LAYOUT_Y);

        AnchorPane.setLeftAnchor(redBtn, BTN2_LAYOUT_X);
        AnchorPane.setTopAnchor(redBtn, BTN2_LAYOUT_Y);

        AnchorPane.setLeftAnchor(yellowBtn, BTN3_LAYOUT_X);
        AnchorPane.setTopAnchor(yellowBtn, BTN3_LAYOUT_Y);

        AnchorPane.setLeftAnchor(blueBtn, BTN4_LAYOUT_X);
        AnchorPane.setTopAnchor(blueBtn, BTN4_LAYOUT_Y);
    }
    public void displayColors(ArrayList<String> sequence){
        SequentialTransition displaySequence = new SequentialTransition();

        for (String color: sequence){
            SequentialTransition transition = new SequentialTransition();

            switch (color){
                case "g":
                    transition = generateGreenTransition();
                    soundPlayer.note_on(60);
                    break;

                case "r":
                    transition = generateRedTransition();
                    soundPlayer.note_on(61);
                    break;

                case "y":
                    transition = generateYellowTransition();
                    soundPlayer.note_on(62);
                    break;

                case "b":
                    transition = generateBlueTransition();
                    soundPlayer.note_on(63);
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

        return new SequentialTransition(transition, retransition);
    }
    private void shake(){
        PathTransition pathTransGreen = new PathTransition();
        pathTransGreen.setDuration(Duration.seconds(1));
        pathTransGreen.setPath(pathGreenBtn);
        pathTransGreen.setNode(greenBtn);
        pathTransGreen.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransGreen.setCycleCount(3);
        pathTransGreen.setAutoReverse(true);

        SequentialTransition sequenceGreen = new SequentialTransition();
        sequenceGreen.getChildren().add(pathTransGreen);

        // Rectangle rouge
        TranslateTransition transitionRed = new TranslateTransition(Duration.seconds(1));
        transitionRed.setToX(redBtn.getX() + 20);
        transitionRed.setToY(redBtn.getY() + 20); // Utilisez setToY pour définir la translation finale
        transitionRed.setNode(redBtn);
        transitionRed.setAutoReverse(true);

        PathTransition pathTransRed = new PathTransition();
        pathTransRed.setDuration(Duration.seconds(1));
        pathTransRed.setPath(pathRedBtn);
        pathTransRed.setNode(redBtn);
        pathTransRed.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransRed.setCycleCount(3);
        pathTransRed.setAutoReverse(true);

        SequentialTransition sequenceRed = new SequentialTransition();
        sequenceRed.getChildren().addAll(transitionRed, pathTransRed);

        // Rectangle jaune
        TranslateTransition transitionYellow = new TranslateTransition(Duration.seconds(1));
        transitionYellow.setToX(yellowBtn.getX() - 20);
        transitionYellow.setToY(yellowBtn.getY() - 40); // Utilisez setToY pour définir la translation finale
        transitionYellow.setNode(yellowBtn);
        transitionYellow.setAutoReverse(true);

        PathTransition pathTransYellow = new PathTransition();
        pathTransYellow.setDuration(Duration.seconds(1));
        pathTransYellow.setPath(pathYellowBtn);
        pathTransYellow.setNode(yellowBtn);
        pathTransYellow.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransYellow.setCycleCount(3);
        pathTransYellow.setAutoReverse(true);

        SequentialTransition sequenceYellow = new SequentialTransition();
        sequenceYellow.getChildren().addAll(transitionYellow, pathTransYellow);

        // Rectangle bleu
        TranslateTransition transitionBlue = new TranslateTransition(Duration.seconds(1));
        transitionBlue.setToX(blueBtn.getX() + 20);
        transitionBlue.setToY(blueBtn.getY() - 40); // Utilisez setToY pour définir la translation finale
        transitionBlue.setNode(blueBtn);

        PathTransition pathTransBlue = new PathTransition();
        pathTransBlue.setDuration(Duration.seconds(1));
        pathTransBlue.setPath(pathBlueBtn);
        pathTransBlue.setNode(blueBtn);
        pathTransBlue.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransBlue.setCycleCount(3);

        SequentialTransition sequenceBlue = new SequentialTransition();
        sequenceBlue.getChildren().addAll(transitionBlue, pathTransBlue);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(sequenceYellow, sequenceBlue, sequenceGreen, sequenceRed);
        parallelTransition.play();

        TranslateTransition resetGreen = new TranslateTransition(Duration.seconds(1), greenBtn);
        resetGreen.setToX(125);
        resetGreen.setToY(50);

        // Rectangle rouge
        TranslateTransition resetRed = new TranslateTransition(Duration.seconds(1), redBtn);
        resetRed.setToX(0);
        resetRed.setToY(0);

        // Rectangle jaune
        TranslateTransition resetYellow = new TranslateTransition(Duration.seconds(1), yellowBtn);
        resetYellow.setToX(0);
        resetYellow.setToY(0);

        // Rectangle bleu
        TranslateTransition resetBlue = new TranslateTransition(Duration.seconds(1), blueBtn);
        resetBlue.setToX(0);
        resetBlue.setToY(0);

        // Jouer toutes les transitions
        resetGreen.play();
        resetRed.play();
        resetYellow.play();
        resetBlue.play();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Appelée");

        buttons = new Rectangle[]{greenBtn, redBtn, yellowBtn, blueBtn};

        pathGreenBtn = new Path();
        pathGreenBtn.getElements().add(new MoveTo(greenBtn.getX(), greenBtn.getY())); // Ajoutez un point de départ au chemin
        pathGreenBtn.getElements().addAll(
                new HLineTo(greenBtn.getX() + greenBtn.getWidth()),
                new VLineTo(greenBtn.getY() + greenBtn.getHeight()),
                new HLineTo(greenBtn.getX()),
                new VLineTo(greenBtn.getY())
        );
        pathGreenBtn.setStroke(Color.RED);


    }
}

