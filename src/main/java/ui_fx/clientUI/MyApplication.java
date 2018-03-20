package ui_fx.clientUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Fresher on 20/03/2018.
 */
public class MyApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/scene.fxml"));
        primaryStage.setTitle("My App");
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }


    public static void main(String... args) {
        launch(args);
    }
}
