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

import common.AtlantaZoo;
import common.LoginPane;

public class Actions extends VBox {
  public Actions() {
    Label title = new Label("Atlanta Zoo");

    Button searchAnimals = new Button();
    searchAnimals.setText("Search Animals");
    searchAnimals.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        AtlantaZoo.setScene("Search Animal", new SearchAnimal());
      }
    });

    Button viewShows = new Button();
    viewShows.setText("View Assigned Shows");
    viewShows.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        AtlantaZoo.setScene("View Assigned Shows", new ViewAssignedShows());
      }
    });


    Button logOut = new Button();
    logOut.setText("Log Out");
    logOut.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        AtlantaZoo.setScene("Login", new LoginPane());
      }
    });


    HBox hb = new HBox();

    hb.getChildren().addAll(searchAnimals, viewShows, logOut);


    setAlignment(Pos.CENTER);
    setSpacing(3);
    setPadding(new Insets(5, 12, 15, 5));

    VBox page = new VBox();
    page.getChildren().addAll(title, hb);
    this.getChildren().addAll(page);
  }
}
