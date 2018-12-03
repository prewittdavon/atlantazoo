package visitor;

import admin.ViewShow;
import common.AtlantaZoo;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ViewShowHistory extends VBox {

    ObservableList<ViewShowHistory.Show> data =
            FXCollections.observableArrayList();

    TextField nameText;
    ComboBox<String> exhibitDropDown;
    DatePicker time;
    TableView table;

  public ViewShowHistory() {
      Label title = new Label("Show History");



      Label nameLabel = new Label("ShowName");
      nameText = new TextField ();
      HBox name = new HBox();
      name.getChildren().addAll(nameLabel, nameText);


      time = new DatePicker();

      ObservableList<String> exhibits = getExhibitsData();

      HBox exhibit = new HBox();
      Label exhibitLabel = new Label("Exhibit");
      exhibitDropDown = new ComboBox<String>();
      exhibitDropDown.getItems().addAll(exhibits);
      exhibit.getChildren().addAll(exhibitLabel, exhibitDropDown);

      Button search = new Button();
      search.setText("Search");
      search.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              //Implement
              getFilteredData();
          }
      });

      HBox row1 = new HBox();
      row1.getChildren().addAll(name, exhibit);
      HBox row2 = new HBox();
      row2.getChildren().addAll(time, search);

      table = new TableView();
      table.setEditable(true);
//      TableColumn nameCol = new TableColumn("Name");
//      TableColumn timeCol = new TableColumn("Time");
//      TableColumn exhibitsCol = new TableColumn("Exhibits");

      TableColumn nameCol = new TableColumn("Name");
      nameCol.setCellValueFactory(
              new PropertyValueFactory<>("name"));

      TableColumn timeCol = new TableColumn("Time");
      timeCol.setCellValueFactory(
              new PropertyValueFactory<>("date"));

      TableColumn exhibitsCol = new TableColumn("Exhibit");
      exhibitsCol.setCellValueFactory(
              new PropertyValueFactory<>("exhibit"));

      getData();
      table.setItems(data);
      table.getColumns().addAll(nameCol, timeCol, exhibitsCol);


      // TableColumn nameCol = new TableColumn("Name");
      // TableColumn sizeCol = new TableColumn("Size");
      // TableColumn numAnimalsCol = new TableColumn("Num Animals");
      // TableColumn waterFeatureCol = new TableColumn("Water Feature");

      // ObservableList<Person> data =
      //   FXCollections.observableArrayList(
      //       new Person("Jacob", "Smith", "jacob.smith@example.com"),
      //       new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
      //       new Person("Ethan", "Williams", "ethan.williams@example.com"),
      //       new Person("Emma", "Jones", "emma.jones@example.com"),
      //       new Person("Michael", "Brown", "michael.brown@example.com")
      //   );
      //
      // table.getColumns().addAll(nameCol, sizeCol, numAnimalsCol, waterFeatureCol);


      this.getChildren().addAll(title, row1, row2, table);

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
                "a.ShowName, a.dt, b.Exhibit " +
                "from (Visit_Show a RIGHT JOIN (SELECT Name, dt, Exhibit FROM Shows) AS b ON a.ShowName = b.Name AND a.dt = b.dt) " +
                "where a.Visitor = 'Yizra'";
        Connection con = null;
        try {
            con = AtlantaZoo.conn();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("ShowName");
                Date date = rs.getDate("dt");
                String exhibit = rs.getString("Exhibit");
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

    public ObservableList<String> getExhibitsData() {
        String query = "select Name " +
                "from Exhibit ";
        Connection con = null;
        ObservableList<String> exhibits = FXCollections.observableArrayList();
        try {
            con = AtlantaZoo.conn();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("Name");
                exhibits.add(name);
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
        return exhibits;
    }

    public void getFilteredData() {
        String query = "select " +
                "a.ShowName, a.dt, b.Exhibit " +
                "from (Visit_Show a RIGHT JOIN (SELECT Name, dt, Exhibit FROM Shows) AS b ON a.ShowName = b.Name AND a.dt = b.dt) " +
                "where a.Visitor = 'Yizra' AND a.ShowName = '" + nameText.getText() + "' AND a.dt = '" + time.getValue() + "' AND b.Exhibit = '" + exhibitDropDown.getValue() + "'";
        Connection con = null;
        data.clear();
        try {
            con = AtlantaZoo.conn();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("ShowName");
                Date date = rs.getDate("dt");
                String exhibit = rs.getString("Exhibit");
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
        System.out.println(query);
    }

}
