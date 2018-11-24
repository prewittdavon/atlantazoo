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


public class RegisterPane extends VBox {
  public RegisterPane() {
    Label title = new Label("Atlanta Zoo");

    Label emailLabel = new Label("Email");
    TextField emailText = new TextField ();
    HBox email = new HBox();
    email.getChildren().addAll(emailLabel, emailText);

    Label usernameLabel = new Label("Username");
    TextField usernameText = new TextField ();
    HBox username = new HBox();
    username.getChildren().addAll(usernameLabel, usernameText);

    Label passwordLabel = new Label("Password");
    TextField passwordText = new TextField ();
    HBox password = new HBox();
    password.getChildren().addAll(passwordLabel, passwordText);

    Label confirmPasswordLabel = new Label("Password");
    TextField confirmPasswordText = new TextField ();
    HBox confirmPassword = new HBox();
    confirmPassword.getChildren().addAll(confirmPasswordLabel, confirmPasswordText);

    Button registerVisitor = new Button();
    registerVisitor.setText("Register Visitor");
    registerVisitor.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
          //Implement
      }
    });
    Button registerStaff = new Button();
    registerStaff.setText("Register Staff");
    registerStaff.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
          //Implement
      }
    });
    HBox registerButtons = new HBox();
    registerButtons.getChildren().addAll(registerVisitor, registerStaff);

    setAlignment(Pos.CENTER);
    setSpacing(3);
    setPadding(new Insets(5, 12, 15, 5));

    VBox page = new VBox();
    page.getChildren().addAll(title, email, username, password, confirmPassword, registerButtons);
    this.getChildren().addAll(page);

  }
}
