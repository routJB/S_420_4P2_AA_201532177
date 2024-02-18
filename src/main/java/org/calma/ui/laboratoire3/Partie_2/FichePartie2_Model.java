package org.calma.ui.laboratoire3.Partie_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FichePartie2_Model extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(FichePartie2_Model.class.getResource("FichePartie2_View.fxml"));

        Scene scene = new Scene(fxmlLoader.load(),640, 480);

        stage.setTitle("FichePartie2");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
