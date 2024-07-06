package se233.chapter2;

import javafx.application.Application;
import javafx.stage.Stage;
import se233.chapter2.controller.FetchData;

public class Launcher extends Application {
    public static Stage primaryStage;
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage=stage;
        primaryStage.setTitle("Currency Watcher");
        primaryStage.setResizable(false);
        System.out.println(FetchData.fetchRange("USD", 6));
        primaryStage.show();
    }
}
