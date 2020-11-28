package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    String comboBoxValue = "";
    int index = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {

        final ComboBox<String> comboBox = new ComboBox();
        comboBox.getItems().setAll("Exctracteur 1", "Extracteur 2", "Extracteur 3");
        final Button btn = new Button();
        final ComboBox<String> comboBoxCSV = new ComboBox();

        //Pour celle ci while on est dans le fichier iterator.hasNext on rajoute une valeur et ensuite on ajoute dans la liste.


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
                        try {
                            script.cloneExtractor("src/main/extractors/WikipediaExtractor_Python");
                            script.runPythonExtractor();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("L'extracteur selectionné est l'" + comboBoxValue);
                }
            });
        });

        StackPane root = new StackPane();

        comboBox.setTranslateX(0);
        comboBox.setTranslateY(-30);
        btn.setTranslateX(0);
        btn.setTranslateY(30);
        btn.setText("Lancez votre extracteur");



        primaryStage.setTitle("Exctrator");

        root.getChildren().add(comboBox);
        root.getChildren().add(btn);

        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}


