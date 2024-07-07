package se233.chapter2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import se233.chapter2.controller.FetchData;
import se233.chapter2.controller.Initialize;
import se233.chapter2.model.Currency;
import se233.chapter2.view.CurrencyPane;
import se233.chapter2.view.TopPane;

public class Launcher extends Application {
    private static Stage primaryStage;
    private static FlowPane mainPane;
    private static TopPane topPane;
    private static CurrencyPane currencyPane;
    private static Currency currency;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage=stage;
        primaryStage.setTitle("Currency Watcher");
        primaryStage.setResizable(false);
//        System.out.println(FetchData.fetchRange("USD", 6));
        currency = Initialize.initializeApp();
        initMainPane();
        Scene mainScene=new Scene(mainPane);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public void initMainPane(){
        mainPane=new FlowPane();
        topPane=new TopPane();
        currencyPane = new CurrencyPane(currency);
        mainPane.getChildren().add(topPane);
        mainPane.getChildren().add(currencyPane);
    }
}
