package admin;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import java.sql.Statement;
import java.sql.ResultSet;
import common.AtlantaZoo;
import java.sql.Connection;

public class ViewStaff extends VBox {

  ObservableList<Staff> data =
      FXCollections.observableArrayList();
  public ViewStaff() {
      Label title = new Label("View Staff");

      TableView table = new TableView();
      table.setEditable(true);
      TableColumn usernameCol = new TableColumn("Username");
      usernameCol.setCellValueFactory(
              new PropertyValueFactory<>("userName"));
      TableColumn emailCol = new TableColumn("Email");
      emailCol.setCellValueFactory(
        new PropertyValueFactory<>("email"));

      Button delete = new Button();
      delete.setText("Delete Staff Member");
      delete.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            //Implement
        }
      });
      getData();
      table.setItems(data);
      table.getColumns().addAll(usernameCol, emailCol);
      this.getChildren().addAll(title, table, delete);

  }

  public static class Staff {
      private final SimpleStringProperty userName;
      private final SimpleStringProperty email;

      public Staff(String userName, String email) {
          this.userName = new SimpleStringProperty(userName);
          this.email = new SimpleStringProperty(email);
      }

      public String getUserName() {
          return userName.get();
      }
      public void setUserName(String uName) {
          userName.set(uName);
      }

      public String getEmail() {
          return email.get();
      }
      public void setEmail(String e) {
          email.set(e);
      }
  }

  public void getData() {
    String query = "select " +
                   "USERNAME, EMAIL " +
                   "from User where USERTYPE=\"staff\"";
    Connection con = null;
    try {
        con = AtlantaZoo.conn();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String userName = rs.getString("USERNAME");
            String email= rs.getString("EMAIL");
            data.add(new Staff(userName, email));
        }
    } catch (Exception e) {
        //JDBCTutorialUtilities.printSQLException(e);
        System.out.println(e.getMessage());
    } finally {
      try {
        if(con != null) con.close();
      } catch (Exception f) {

      }
    }
  }


}
