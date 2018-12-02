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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.Statement;
import java.sql.ResultSet;



public class AtlantaZoo extends Application {
  public static Stage primaryStage;
  public static Scene scene;
  public static Connection con;


  @Override
  public void start(Stage ps) throws Exception {
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

  public static Connection conn() throws Exception {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      con = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_group83?useSSL=false", "cs4400_group83", "");
      if(!con.isClosed()) System.out.println("Successfully connected to " + "MySQL server using TCP/IP...");
      return con;
  }

}
