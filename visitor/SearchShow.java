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
import java.sql.Statement;
import java.sql.ResultSet;
import common.AtlantaZoo;
import java.sql.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Date;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;


public class SearchShow extends VBox {
  ObservableList<Show> data =
      FXCollections.observableArrayList();
  public SearchShow() {
      Label title = new Label("Shows");

      Label nameLabel = new Label("Name");
      TextField nameText = new TextField ();
      HBox name = new HBox();
      name.getChildren().addAll(nameLabel, nameText);

      DatePicker date = new DatePicker();

      VBox exhibit = dropDown("Exhibit");
      //exhibitDropDown.getItems().addAll("Yes", "No");
      //exhibit.getChildren().addAll(exhibitLabel, exhibitDropDown);

      Button search = new Button();
      search.setText("Search");
      search.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          data.clear();
          String nameField = nameText.getText();

          VBox numAnBox1 = exhibit;
          ComboBox dropEx = (ComboBox) numAnBox1.getChildren().get(1);
          String ex = (String) dropEx.getValue();

          String dt = date.getValue().toString();

          System.out.println(ex);

          String query = "select " +
                         "Name, dt, Exhibit " +
                         "from Shows WHERE " +
                         "Name=\"" + nameField + "\"" +
                         " AND Exhibit=\"" + ex + "\"";
          Connection con = null;
          try {
              con = AtlantaZoo.conn();
              Statement stmt = con.createStatement();
              ResultSet rs = stmt.executeQuery(query);

              while (rs.next()) {
                  String name = rs.getString("Name");
                  String exhibit = rs.getString("Exhibit");
                  Date date = rs.getDate("dt");
                  data.add(new Show(name, date.toString(), exhibit));
                  //System.out.println(name + " " + exhibit );
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
      });


      HBox row1 = new HBox();
      row1.getChildren().addAll(name, date);

      HBox row2 = new HBox();
      row2.getChildren().addAll(exhibit, search);


      TableView table = new TableView();
      table.setEditable(true);
      TableColumn nameCol = new TableColumn("Name");
      nameCol.setCellValueFactory(
              new PropertyValueFactory<>("name"));
      TableColumn exhibitsCol = new TableColumn("Exhibits");
      exhibitsCol.setCellValueFactory(
              new PropertyValueFactory<>("exhibit"));
      TableColumn dateCol = new TableColumn("Date");
      dateCol.setCellValueFactory(
              new PropertyValueFactory<>("date"));

      getData();
      table.setItems(data);
      table.getColumns().addAll(nameCol, exhibitsCol, dateCol);

      // Button logVisit = new Button();
      // logVisit.setText("Search");
      // logVisit.setOnAction(new EventHandler<ActionEvent>() {
      //   @Override
      //   public void handle(ActionEvent event) {
      //
      //   }
      // });

      this.getChildren().addAll(title, row1, row2, table);

  }

  public VBox dropDown(String name) {
        Label label = new Label(name);
        ComboBox<String> dropDown = new ComboBox<String>();
        for (String s : AtlantaZoo.getExhibit()) dropDown.getItems().add(s);
        //for (int i = 0; i <= max; i++) dropDown.getItems().add("" + i);
        dropDown.setValue(null);
        VBox box = new VBox();
        box.getChildren().addAll(label, dropDown);
        return box;
  }

  public static class Show {
      private final SimpleStringProperty name;
      private final SimpleStringProperty date;
      private final SimpleStringProperty exhibit;

      public Show(String name, String date, String exhibit) {
          this.name = new SimpleStringProperty(name);
          this.date = new SimpleStringProperty(date);
          this.exhibit = new SimpleStringProperty(exhibit);
      }

      public String getName() {
          return name.get();
      }
      public void setName(String uName) {
          name.set(uName);
      }

      public String getDate() {
          return date.get();
      }
      public void setDate(String d) {
          date.set(d);
      }

      public String getExhibit() {
          return exhibit.get();
      }
      public void setExhibit(String e) {
          exhibit.set(e);
      }
  }

  public void getData() {
    String query = "select " +
                   "Name, dt, Exhibit " +
                   "from Shows";
    Connection con = null;
    try {
        con = AtlantaZoo.conn();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String name = rs.getString("Name");
            String exhibit = rs.getString("Exhibit");
            Date date = rs.getDate("dt");
            data.add(new Show(name, date.toString(), exhibit));
            //System.out.println(name + " " + exhibit );
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
