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

    @Override
    public void start(Stage primaryStage) throws Exception{
        final ComboBox<String> comboBox = new ComboBox();
        comboBox.getItems().setAll("Exctracteur 1","Extracteur 2", "Extracteur 3");
        comboBox.valueProperty().addListener(observable -> System.out.printf("Valeur sélectionnée: %s", comboBox.getValue()).println());
        comboBox.getSelectionModel().select(1);
        primaryStage.setTitle("Exctrator");
        Button btn = new Button();
        btn.setText("Lancez votre extracteur");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("L'extracteur n°");
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(comboBox);
        //root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}