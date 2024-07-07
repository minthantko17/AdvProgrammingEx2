package se233.chapter2.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.time.LocalDateTime;

public class TopPane extends FlowPane {
    private Button refresh;
    private Button change;
    private Label update;
    public TopPane(){
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setPrefSize(640,20);
        change= new Button("Change");
        refresh= new Button("Refresh");
        update=new Label();
        refreshPane();
        this.getChildren().addAll(refresh, change, update);
    }
    public void refreshPane(){
        update.setText(String.format("Last update: %s", LocalDateTime.now().toString()));
    }
}
