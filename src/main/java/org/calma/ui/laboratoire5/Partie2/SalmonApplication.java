package org.calma.ui.laboratoire5.Partie2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SalmonApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/org/calma/ui/laboratoire5/Partie2/Salmon-View.fxml")));

        stage.setTitle("Salmon");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}

