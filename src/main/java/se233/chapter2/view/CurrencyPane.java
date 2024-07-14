package se233.chapter2.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import se233.chapter2.controller.AllEventHandlers;
import se233.chapter2.controller.draw.DrawGraphtask;
import se233.chapter2.model.Currency;

import java.util.concurrent.*;

public class CurrencyPane extends BorderPane {
    private Currency currency;
    private Button watch;
    private Button delete;
    public CurrencyPane(Currency currency) {
        this.watch = new Button("Watch");
        this.delete= new Button("Delete");

        this.watch.setOnAction(e -> {AllEventHandlers.onWatch(currency.getShortCode());});
        this.delete.setOnAction(e -> {AllEventHandlers.onDelete(currency.getShortCode());});

        this.setPadding(new Insets(0));
        this.setPrefSize(640, 300);
        this.setStyle("-fx-border-color: black");
        try {
            this.refreshPane(currency);
        }catch (ExecutionException e){
            System.out.println("Encountered an execution exception.");
        }catch (InterruptedException e){
            System.out.println("Encountered an interrupted exception.");
        }
    }

    public void refreshPane(Currency currency) throws ExecutionException, InterruptedException {
        this.currency = currency;
        Pane currencyInfo= genInfoPane();
        FutureTask futureTask=new FutureTask<VBox>(new DrawGraphtask(currency));
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {
            executor.execute(futureTask);
            VBox currencyGraph = (VBox) futureTask.get();
            Pane topArea = genTopArea();
            this.setTop(topArea);
            this.setLeft(currencyInfo);
            this.setCenter(currencyGraph);
//            executor.shutdown();
        }
    }

    private Pane genInfoPane() {
        VBox currencyInfoPane=new VBox(10);
        currencyInfoPane.setPadding(new Insets(5, 25, 5, 25));
        currencyInfoPane.setAlignment(Pos.CENTER);
        Label exchangeString=new Label("");
        Label watchString=new Label("");
        exchangeString.setStyle("-fx-font-size: 20");
        watchString.setStyle("-fx-font-size: 14");
        if (this.currency!=null){
            exchangeString.setText(String.format("%s: %.4f", this.currency.getShortCode(), this.currency.getCurrent().getRate()));
            if(this.currency.getWatch()==true){
                watchString.setText(String.format("(Watch @%.4f)", this.currency.getWatchRate()));
            }
        }
        currencyInfoPane.getChildren().addAll(exchangeString,watchString);
        return currencyInfoPane;
    }

    private HBox genTopArea(){
        HBox topArea=new HBox(10);
        topArea.setPadding(new Insets(5));
        topArea.getChildren().addAll(watch, delete);
        ((HBox)topArea).setAlignment(Pos.CENTER_RIGHT);
        return topArea;
    }

}
