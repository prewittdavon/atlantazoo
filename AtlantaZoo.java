package common;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import admin.*;
import staff.*;
import visitor.*;




public class AtlantaZoo extends Application {
  public static Stage primaryStage;
  public static Scene scene;
  @Override
  public void start(Stage ps) {
    primaryStage = ps;

    StackPane root = new StackPane(new LoginPane());

    scene = new Scene(root, 500, 450);
    //
    primaryStage.setTitle("Login");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void setScene(String name, VBox vbox) {
    primaryStage.setTitle(name);
    Scene scene = new Scene(new StackPane(vbox), 500, 450);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static Scene login() {
    return scene;
  }

}
