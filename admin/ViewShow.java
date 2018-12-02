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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import java.sql.Statement;
import java.sql.ResultSet;
import common.AtlantaZoo;
import java.sql.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Date;

public class ViewShow extends VBox {

  ObservableList<Show> data =
      FXCollections.observableArrayList();
  public ViewShow() {
      Label title = new Label("Show");



      Label nameLabel = new Label("Name");
      TextField nameText = new TextField ();
      HBox name = new HBox();
      name.getChildren().addAll(nameLabel, nameText);


      DatePicker time = new DatePicker();

      HBox exhibit = new HBox();
      Label exhibitLabel = new Label("Exhibit");
      ComboBox<String> exhibitDropDown = new ComboBox<String>();
      exhibitDropDown.getItems().addAll("Yes", "No");
      exhibit.getChildren().addAll(exhibitLabel, exhibitDropDown);

      Button search = new Button();
      search.setText("Search");
      search.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            //Implement
        }
      });

      HBox row1 = new HBox();
      row1.getChildren().addAll(name, exhibit);
      HBox row2 = new HBox();
      row2.getChildren().addAll(time, search);

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

      Button removeShow = new Button();
      removeShow.setText("Remove Show");
      removeShow.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            //Implement
        }
      });

      this.getChildren().addAll(title, row1, row2, table, removeShow);

  }

  public VBox dropDown(String name, int min, int max) {
        Label label = new Label(name);
        ComboBox<String> dropDown = new ComboBox<String>();
        for (int i = min; i <= max; i++) dropDown.getItems().add("" + i);
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
