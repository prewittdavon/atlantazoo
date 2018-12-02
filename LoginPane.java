package common;

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
import admin.*;
import staff.*;
import visitor.*;



public class LoginPane extends VBox {

  public LoginPane() {
    setSpacing(5);
    Label title = new Label("Atlanta Zoo");
    Label usernameLabel = new Label("Username");
    usernameLabel.setPadding(new Insets(0, 15, 0, 0));
    TextField usernameText = new TextField ();
    HBox username = new HBox();
    username.getChildren().addAll(usernameLabel, usernameText);

    Label passwordLabel = new Label("Password");
    passwordLabel.setPadding(new Insets(0, 15, 0, 0));
    TextField passwordText = new TextField ();
    HBox password = new HBox();
    password.getChildren().addAll(passwordLabel, passwordText);

    Button login = new Button();
    login.setText("Login");
    login.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (false) {
          AtlantaZoo.setScene("Atlanta Zoo", new admin.Actions());
        } else if (false) {
          AtlantaZoo.setScene("Atlanta Zoo", new staff.Actions());
        } else{
          AtlantaZoo.setScene("Atlanta Zoo", new visitor.Actions());
        }
      }
    });

    Button register = new Button();
    register.setText("Register");
    register.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        AtlantaZoo.setScene("Register", new RegisterPane());
      }
    });


    VBox page = new VBox();
    page.getChildren().addAll(title, username, password, login, register);
    this.getChildren().addAll(page);

  }

}
