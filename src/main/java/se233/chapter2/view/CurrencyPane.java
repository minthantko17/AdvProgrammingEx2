package se233.chapter2.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import se233.chapter2.model.Currency;

public class CurrencyPane extends BorderPane {
    private Currency currency;
    private Button watch;
    public CurrencyPane(Currency currency) {
        this.watch = new Button("Watch");
        this.setPadding(new Insets(0));
        this.setPrefSize(640, 300);
        this.setStyle("-fx-border-color: black");
        this.refreshPane(currency);
    }

    public void refreshPane(Currency currency) {
        this.currency = currency;
        Pane currencyInfo= genInfoPane();
        Pane topArea=genTopArea();
        this.setTop(topArea);
        this.setLeft(currencyInfo);
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
        topArea.getChildren().addAll(watch);
        ((HBox)topArea).setAlignment(Pos.CENTER);
        return topArea;
    }

}
