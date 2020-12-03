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
import org.luaj.vm2.ast.Str;

import java.io.File;
import java.io.IOException;


public class Main extends Application {

    String comboBoxValue = "";
    String comboBoxResultValue="";
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
        } else if (javaWikiUrl) {
            File exctractFromPython = new File("output/wikitext");
            File[] filesFromPython = exctractFromPython.listFiles();

            for (int i = 0; i < filesFromPython.length; i++) {
                String fileName = filesFromPython[i].toString();
                comboBoxResult.getItems().add(filesFromPython[i].toString());
            }
        } else if (javaHTML) {
            File exctractFromPython = new File("output/html");
            File[] filesFromPython = exctractFromPython.listFiles();

            for (int i = 0; i < filesFromPython.length; i++) {
                String fileName = filesFromPython[i].toString();
                comboBoxResult.getItems().add(filesFromPython[i].toString());
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        RunExtractors.verifyInputExists();
        RunExtractors.verifyOutputExists();

        final ComboBox<String> comboBox = new ComboBox();
        comboBox.getItems().setAll("Exctracteur Java HTML", "Extracteur Java WikiText", "Extracteur Python");
        Button btn = new Button();
        Button tabBtn = new Button();
        TextField textField = new TextField("Chose an url");


        comboBox.valueProperty().addListener(observable -> {

            comboBoxValue = comboBox.getSelectionModel().getSelectedItem().toString();
            index = comboBox.getSelectionModel().getSelectedIndex();


            btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    String urlTextField = textField.getText();
                    System.out.println(urlTextField);
                    if (index == 0) {
                        try {
                            //comboBoxResult.
                            python = false;
                            javaHTML = true;
                            javaWikiUrl = false;
                            RunExtractors.modifyWikiurls(urlTextField);
                            RunExtractors.runJavaExtractor();
                            launchResult();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else if (index == 1) {
                        try {
                            python = false;
                            javaHTML = false;
                            javaWikiUrl = true;
                            RunExtractors.runJavaExtractor();
                            launchResult();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        try {
                            python = true;
                            javaHTML = false;
                            javaWikiUrl = false;
                            RunExtractors.runPythonExtractor();
                            launchResult();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
        });
        comboBoxResult.valueProperty().addListener(observable -> {
            comboBoxResultValue = comboBoxResult.getSelectionModel().getSelectedItem().toString();
            System.out.println(comboBoxResultValue);
            tabBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Je clique sur le bouton afficher les fichiers");

                }

            });
        });

        comboBox.setTranslateX(0);
        comboBox.setTranslateY(-30);
        comboBoxResult.setTranslateX(-50);
        comboBoxResult.setTranslateY(60);
        btn.setTranslateX(0);
        btn.setTranslateY(30);
        tabBtn.setTranslateX(0);
        tabBtn.setTranslateY(100);
        textField.setTranslateX(0);
        textField.setTranslateY(-100);
        primaryStage.setTitle("Extractor");


        btn.setText("Lancer votre extracteur");
        tabBtn.setText("Afficher le contenu du fichier");
        StackPane root = new StackPane();

        textField.setMinWidth(180);

        root.setPadding(new Insets(10));

        primaryStage.setTitle("JavaFX TextField (o7planning.org)");

        root.getChildren().add(comboBox);
        root.getChildren().add(btn);
        root.getChildren().add(tabBtn);
        root.getChildren().addAll(textField);
        root.getChildren().addAll(comboBoxResult);
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}



