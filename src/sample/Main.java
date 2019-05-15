package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;

    private static Scene scene;
    public static String id;

    public static String Login;
    private Parent root;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.primaryStage=primaryStage;
        root = FXMLLoader.load(getClass().getResource("RootWind.fxml"));
        scene =new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static String getLogin() {
        return Login;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
