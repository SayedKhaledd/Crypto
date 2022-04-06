package lab1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    static Stage myStage;
    static Parent root;
    @Override
    public void start(Stage primaryStage) throws Exception {
        myStage = primaryStage;
        root = FXMLLoader.load(getClass().getResource("main.fxml"));

        myStage.setTitle("crypto");
        myStage.setMaximized(true);
        myStage.setScene(new Scene(root, myStage.getMaxHeight(), myStage.getMaxWidth()));
        myStage.show();

    }
}
