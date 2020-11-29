package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;


public class Main extends Application {

    String comboBoxValue = "";
    int index = 0;
    final ComboBox<String> comboBoxResult = new ComboBox();
    private Boolean python = false;
    private Boolean javaWikiUrl = false;
    private Boolean javaHTML = false;


    public void launchResult() {
        if (python) {
            File exctractFromPython = new File("output/python");
            File[] filesFromPython = exctractFromPython.listFiles();

            for (int i = 0; i < filesFromPython.length; i++) {
                String fileName = filesFromPython[i].toString();
                comboBoxResult.getItems().add(filesFromPython[i].toString());
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        final ComboBox<String> comboBox = new ComboBox();
        comboBox.getItems().setAll("Exctracteur 1", "Extracteur 2", "Extracteur 3");
        Button btn = new Button();
        TextField textField = new TextField("Chose an url");


        comboBox.valueProperty().addListener(observable -> {

            comboBoxValue = comboBox.getSelectionModel().getSelectedItem().toString();
            index = comboBox.getSelectionModel().getSelectedIndex();


            btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (index == 0) {
                        try {
                            python = false;
                            javaHTML = true;
                            javaWikiUrl = false;

                            ExtractorsImpl.cloneExtractor("https://github.com/Jlebours/PDL_1920_groupe-7");
                            ExtractorsImpl.runJavaExtractor();
                            launchResult();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else if (index == 1) {
                        try {
                            python = false;
                            javaHTML = false;
                            javaWikiUrl = true;

                            ExtractorsImpl.cloneExtractor("https://github.com/Jlebours/PDL_1920_groupe-7");
                            ExtractorsImpl.runJavaExtractor();
                            launchResult();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        try {
                            python = true;
                            javaHTML = false;
                            javaWikiUrl = false;

                            ExtractorsImpl.cloneExtractor("https://github.com/Jlebours/WikipediaExtractor_Python.git");
                            ExtractorsImpl.runPythonExtractor();
                            launchResult();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
        });
        comboBox.setTranslateX(0);
        comboBox.setTranslateY(-30);
        comboBoxResult.setTranslateX(-50);
        comboBoxResult.setTranslateY(60);
        btn.setTranslateX(0);
        btn.setTranslateY(30);
        textField.setTranslateX(0);
        textField.setTranslateY(-100);
        primaryStage.setTitle("Extractor");


        btn.setText("Lancez votre extracteur");
        StackPane root = new StackPane();

        textField.setMinWidth(180);

        root.setPadding(new Insets(10));

        primaryStage.setTitle("JavaFX TextField (o7planning.org)");

        root.getChildren().add(comboBox);
        root.getChildren().add(btn);
        root.getChildren().addAll(textField);
        root.getChildren().addAll(comboBoxResult);
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}



