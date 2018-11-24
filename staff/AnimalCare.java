package staff;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import visitor.AnimalDetail;

public class AnimalCare extends AnimalDetail {
  public AnimalCare(String name, String species,
    int age, String exhibit, String type) {
    super(name, species, age, exhibit, type);


    TextField logText = new TextField ();
    Button logButton = new Button();
    logButton.setText("Log Notes");
    logButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
          //Implement
      }
    });
    HBox log = new HBox();
    log.getChildren().addAll(logText, logButton);

    TableView table = new TableView();
    table.setEditable(true);
    TableColumn staffMemberCol = new TableColumn("Staff Member");
    TableColumn noteCol = new TableColumn("Note");
    TableColumn timeCol = new TableColumn("Time");

    table.getColumns().addAll(staffMemberCol, noteCol, timeCol);
    this.getChildren().addAll(log, table);

  }
}
