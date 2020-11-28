package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    String comboBoxValue = "";
    int index = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {

        final ComboBox<String> comboBox = new ComboBox();
        comboBox.getItems().setAll("Exctracteur 1", "Extracteur 2", "Extracteur 3");
        Button btn = new Button();
        comboBox.valueProperty().addListener(observable -> {

            comboBoxValue = comboBox.getSelectionModel().getSelectedItem().toString();
            index = comboBox.getSelectionModel().getSelectedIndex();


            btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (index == 0) {
                        System.out.println("//lancer l'ancien Java l'extracteur Java witext ");
                    } else if (index == 1) {
                        System.out.println("//Lancer le Java sur le CSV");
                    } else {
                        System.out.println("//Lancer le python");
                    }
                    System.out.println("L'extracteur selectionné est l'" + comboBoxValue);
                }
            });
        });
        btn.setTranslateX(75);
        btn.setTranslateY(100);
        primaryStage.setTitle("Exctrator");
        btn.setText("Lancez votre extracteur");
        StackPane root = new StackPane();
        root.getChildren().add(comboBox);
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


