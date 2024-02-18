package org.calma.ui.laboratoire3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;

public class FichePartie1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StackPane root = new StackPane();

        final VBox vBox = new VBox();

        final MenuBar menuBar = new MenuBar();
        menuBar.setMaxWidth(Double.MAX_VALUE);

        Menu menuFile = new Menu("File");
        Menu menuEdit = new Menu("Edit");
        Menu menuHelp = new Menu("Help");

        menuBar.getMenus().addAll(menuFile, menuEdit, menuHelp);

        //AJout images haut
        final HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 10, 10, 50));

        Image imgLogo = new Image("/org/calma/ui/images/ocp.png");
        ImageView imgViewLogo = new ImageView(imgLogo);

        imgViewLogo.setFitHeight(50);
        imgViewLogo.setFitWidth(50);

        final Label lblTitre = new Label("Fiche descriptive - OCP Corporation");
        lblTitre.setPadding(new Insets(10, 10, 10,50));
        lblTitre.setFont(new Font(18));
        lblTitre.setStyle("-fx-font-weight: bold");
        hbox.getChildren().addAll(imgViewLogo, lblTitre);

        final GridPane grid1 = new GridPane();
        ColumnConstraints col1G1 = new ColumnConstraints();
        col1G1.setPercentWidth(20);
        ColumnConstraints col2G1 = new ColumnConstraints();
        grid1.getColumnConstraints().addAll(col1G1, col2G1);
        col2G1.setPercentWidth(85);
        grid1.setPadding(new Insets(10));
        grid1.setHgap(50);
        grid1.setVgap(10);

        Label lblId = new Label("Identification");
        TextField txtId = new TextField();
        txtId.setDisable(true);

        Label lblPrenom = new Label("Prénom");
        TextField txtPrenom = new TextField();

        Label lblNom = new Label("Nom");
        TextField txtNom = new TextField();

        Label lblDateNaissance = new Label("Date de naissance");
        DatePicker date = new DatePicker();

        Label lblCourriel = new Label("Courriel");
        TextField txtCourriel = new TextField();
        txtCourriel.setPrefWidth(425);
        Image imgCourriel = new Image("/org/calma/ui/images/email_invalid.png");
        ImageView imgViewCourriel = new ImageView(imgCourriel);
        imgViewCourriel.setFitHeight(24);
        imgViewCourriel.setFitWidth(24);

        final HBox hboxGrid = new HBox();
        hboxGrid.setSpacing(10);
        hboxGrid.getChildren().addAll(txtCourriel, imgViewCourriel);

        Label lblDepartement = new Label("Département(s)");
        lblDepartement.setPadding(new Insets(0,0,50,0));
        Label lblInclu = new Label("Inclu(s)");
        Label lblExclu = new Label("Exclu(s)");

        Button btnInclude = new Button("<");
        Button btnExclude = new Button(">");

        TextArea txtInclus = new TextArea();
        txtInclus.setPrefWidth(150);
        txtInclus.setPrefHeight(155);
        TextArea txtExclus = new TextArea();
        txtExclus.setPrefWidth(150);
        txtExclus.setPrefHeight(155);

        final HBox hBoxFinalColumn = new HBox();
        final VBox vBox1 = new VBox();
        final VBox vBox2 = new VBox();
        final VBox vBox3 = new VBox();

        vBox1.getChildren().addAll(lblInclu, txtInclus);
        vBox2.getChildren().addAll(btnInclude, btnExclude);
        vBox3.getChildren().addAll(lblExclu, txtExclus);
        vBox2.setAlignment(Pos.CENTER);
        vBox2.setSpacing(3);

        hBoxFinalColumn.getChildren().addAll(vBox1, vBox2, vBox3);
        hBoxFinalColumn.setSpacing(30);

        StackPane stackLastBtn = new StackPane();
        stackLastBtn.setAlignment(Pos.BASELINE_RIGHT);
        stackLastBtn.setPadding(new Insets(0,5,5,0));
        Button btnValid = new Button("Valider");
        stackLastBtn.getChildren().add(btnValid);

        grid1.add(lblId, 0, 0);
        grid1.add(txtId, 1,0);
        grid1.add(lblPrenom, 0, 1);
        grid1.add(txtPrenom, 1,1);
        grid1.add(lblNom, 0, 2);
        grid1.add(txtNom, 1,2);
        grid1.add(lblDateNaissance, 0, 3);
        grid1.add(date, 1,3);
        grid1.add(lblCourriel, 0, 4);
        grid1.add(hboxGrid, 1,4);
        grid1.add(lblDepartement, 0, 5);
        grid1.add(hBoxFinalColumn, 1,5);

        hBoxFinalColumn.setPadding(new Insets(-5,20,5,25));

        vBox.getChildren().addAll(menuBar, hbox, grid1, stackLastBtn);

        root.getChildren().add(vBox);

        Scene scene = new Scene(root,640, 480);

        stage.setTitle("FichePartie1");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
