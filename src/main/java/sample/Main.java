package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.commons.io.FileUtils;
import org.luaj.vm2.ast.Str;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main extends Application {

    String comboBoxValue = "";
    String comboBoxResultValue = "";
    int index = 0;
    final ComboBox<String> comboBoxResult = new ComboBox();
    private Boolean python = false;
    private Boolean javaWikiUrl = false;
    private Boolean javaHTML = false;

    private final TableView tableView = new TableView<>();
    private final ObservableList<Map<String, String>> dataList
            = FXCollections.observableArrayList();

    private int countMaxCol = 0;


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

        TableView<String> tableView = new TableView<>();

        RunExtractors.verifyInputExists();
        RunExtractors.verifyOutputExists();

        final ComboBox<String> comboBox = new ComboBox();
        comboBox.getItems().setAll("Exctracteur Java HTML", "Extracteur Java WikiText", "Extracteur Python");
        Button btn = new Button();
        Button tabBtn = new Button();
        Button compareButton = new Button();
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
                    tst(primaryStage);

                }

            });
        });

        compareButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                List<String> pythonList = compareCSV(comboBoxResultValue);
                String regex = "output\\/\\w+|\\/(.*)";
                String[] tokensVal = comboBoxResultValue.split(regex);

                System.out.println("MA REGEX"  + tokensVal[0]);
                List<String> javaHTMLList = compareCSV("output/wikitext/"+tokensVal[0]);
                List<String> javaWikiTextList = compareCSV("output/html/"+tokensVal[0]);

                if (python) {
                    try {
                        RunExtractors.runJavaExtractor();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (javaHTML || javaWikiUrl) {
                    try {
                        RunExtractors.runPythonExtractor();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
//                if(!(pythonList.size()==javaHTMLList.size()) || (pythonList.size()==javaWikiTextList.size())){
//                    System.out.println("Mes tailles sont différentes");
//                }
//                else {
//                    for(int i=0;i<pythonList.size();i++){
//                        if(pythonList.get(i).equals(javaHTMLList.get(i))&&pythonList.get(i).equals(javaWikiTextList.get(i))){
//                            System.out.println("Mes premières lignes sont égales");
//                        }
//                        else {
//                            System.out.println("Nous avons une différence a Python = "+ pythonList.get(i) + "et JavaHTML" + javaHTMLList.get(i) + "Ou a Python : " + pythonList.get(i) + "et WikiText : " + javaWikiTextList.get(i));
//                        }
//
//                    }

    //            }


            }
        });


        comboBox.setTranslateX(0);
        comboBox.setTranslateY(-30);
        comboBoxResult.setTranslateX(-50);
        comboBoxResult.setTranslateY(60);
        btn.setTranslateX(0);
        btn.setTranslateY(30);
        tabBtn.setTranslateX(0);
        tabBtn.setTranslateY(100);
        compareButton.setTranslateX(0);
        compareButton.setTranslateY(130);
        textField.setTranslateX(0);
        textField.setTranslateY(-100);
        primaryStage.setTitle("Extractor");


        btn.setText("Lancer votre extracteur");
        tabBtn.setText("Afficher le contenu du fichier");
        compareButton.setText("Afficher les différences");
        StackPane root = new StackPane();

        textField.setMinWidth(180);

        root.setPadding(new Insets(10));

        primaryStage.setTitle("JavaFX TextField (o7planning.org)");

        root.getChildren().add(comboBox);
        root.getChildren().add(btn);
        root.getChildren().add(tabBtn);
        root.getChildren().add(compareButton);
        root.getChildren().addAll(textField);
        root.getChildren().addAll(comboBoxResult);
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();

    }
    private List<String> compareCSV(String url) {
        List<String> lineList = new ArrayList<>();
        String FieldDelimiter = ",";
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(url));
            String line;
            while ((line = br.readLine()) != null) {

                String[] fields = line.split(FieldDelimiter, -1);
                for (int i = 0; i < fields.length; i++) {
                    lineList.add(fields[i]);

                }
            }
            System.out.println(lineList);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return lineList;
    }

    private void readCSV() {
        String CsvFile = comboBoxResultValue;
        String FieldDelimiter = ",";
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(CsvFile));
            String line;
            while ((line = br.readLine()) != null) {
                Map<String, String> CSVLine = new HashMap<>();
                String[] fields = line.split(FieldDelimiter, -1);
                for (int i = 0; i < fields.length; i++) {
                    CSVLine.put("column"+i, fields[i]);

                }
                dataList.add(CSVLine);
                if(countMaxCol < fields.length) {
                    countMaxCol = fields.length;
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }


    public void tst( Stage primaryStage) {
        readCSV();
        primaryStage.setTitle("CSV");
        Group root = new Group();

        for (int i = 1; i <= countMaxCol; i++) {
            TableColumn<Map, String> columnF = new TableColumn("Ma colonne " + i);
            columnF.setCellValueFactory(new MapValueFactory<>("column"+i));
            tableView.getColumns().addAll(columnF);

            tableView.setEditable(true);
            columnF.setCellFactory(TextFieldTableCell.forTableColumn());
        }

        tableView.setItems(dataList);
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().add(tableView);

        root.getChildren().add(vBox);

        primaryStage.setScene(new Scene(root, 700, 250));
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}



