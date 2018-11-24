package visitor;

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

    Button searchExhibits = new Button();
    searchExhibits.setText("Search Exhibits");
    searchExhibits.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        AtlantaZoo.setScene("Search Exhibits", new SearchExhibit());
      }
    });

    Button viewExhibitHistory = new Button();
    viewExhibitHistory.setText("View Exhibit History");
    viewExhibitHistory.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
          AtlantaZoo.setScene("View Exhibit History", new ViewExhibitHistory());
      }
    });

    Button viewShowHistory = new Button();
    viewShowHistory.setText("View Show History");
    viewShowHistory.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        AtlantaZoo.setScene("View Show History", new ViewShowHistory());
      }
    });

    Button searchShows = new Button();
    searchShows.setText("Search Shows");
    searchShows.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        AtlantaZoo.setScene("Search Show", new SearchShow());
      }
    });

    Button searchForAnimals = new Button();
    searchForAnimals.setText("Search For Animals");
    searchForAnimals.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        AtlantaZoo.setScene("Search Animals", new SearchAnimal());
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



    HBox hb1 = new HBox();
    HBox hb2 = new HBox();
    HBox hb3 = new HBox();
    hb1.getChildren().addAll(searchExhibits, viewExhibitHistory);
    hb2.getChildren().addAll(searchShows, viewShowHistory);
    hb3.getChildren().addAll(searchForAnimals, logOut);


    setAlignment(Pos.CENTER);
    setSpacing(3);
    setPadding(new Insets(5, 12, 15, 5));

    VBox page = new VBox();
    page.getChildren().addAll(hb1, hb2, hb3);
    this.getChildren().addAll(page);
  }
}
