
package admin;

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

    Button viewVisitors = new Button();
    viewVisitors.setText("View Visitors");
    viewVisitors.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
          AtlantaZoo.setScene("View Visitors", new ViewVisitors());
      }
    });

    Button viewStaff = new Button();
    viewStaff.setText("View Staff");
    viewStaff.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        AtlantaZoo.setScene("View Staff", new ViewStaff());
      }
    });

    Button viewShows = new Button();
    viewShows.setText("View Shows");
    viewShows.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        AtlantaZoo.setScene("View Shows", new ViewShow());
      }
    });

    Button viewAnimals = new Button();
    viewAnimals.setText("View Animals");
    viewAnimals.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        AtlantaZoo.setScene("View Animal", new ViewAnimal());
      }
    });

    Button addShow = new Button();
    addShow.setText("Add Show");
    addShow.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
          //Implement
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
    hb1.getChildren().addAll(viewVisitors, viewStaff);
    hb2.getChildren().addAll(viewShows, viewAnimals);
    hb3.getChildren().addAll(addShow, logOut);


    setAlignment(Pos.CENTER);
    setSpacing(3);
    setPadding(new Insets(5, 12, 15, 5));

    VBox page = new VBox();
    page.getChildren().addAll(hb1, hb2, hb3);
    this.getChildren().addAll(page);
  }
}
