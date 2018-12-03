package visitor;

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

import javax.swing.text.View;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;


public class ViewExhibitHistory extends VBox {


    ObservableList<ViewExhibitHistory.Exhibit> data =
            FXCollections.observableArrayList();

    TextField nameText;
    ComboBox<String> dropMin;
    ComboBox<String> dropMax;
    DatePicker time;

  public ViewExhibitHistory() {
      Label title = new Label("Exhibit History");



      Label nameLabel = new Label("Name");
      nameText = new TextField ();
      HBox name = new HBox();
      name.getChildren().addAll(nameLabel, nameText);

      VBox vbox1 = dropDown("Min", 0, 10);
      VBox vbox2 = dropDown("Max", 0, 10);



      dropMin = (ComboBox<String>)vbox1.getChildren().get(1);
      dropMax = (ComboBox<String>)vbox2.getChildren().get(1);

      HBox numAnimals = new HBox();
      numAnimals.getChildren().addAll(new Label("Num Animals"), vbox1, vbox2);

      time = new DatePicker();

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
      row1.getChildren().addAll(name, numAnimals);
      HBox row2 = new HBox();
      row2.getChildren().addAll(time, search);

      TableView table = new TableView();
      table.setEditable(true);
//      TableColumn nameCol = new TableColumn("Name");
//      TableColumn sizeCol = new TableColumn("Size");
//      TableColumn numAnimalsCol = new TableColumn("Num Visits");
//      TableColumn waterCol = new TableColumn("Water");

      TableColumn nameCol = new TableColumn("Name");
      nameCol.setCellValueFactory(
              new PropertyValueFactory<>("name"));

      TableColumn timeCol = new TableColumn("Time");
      timeCol.setCellValueFactory(
              new PropertyValueFactory<>("date"));

      TableColumn visitsCol = new TableColumn("Number of Visits");
      visitsCol.setCellValueFactory(
              new PropertyValueFactory<>("visits"));

      getData();
      table.setItems(data);

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


      table.getColumns().addAll(nameCol, timeCol, visitsCol);
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

    public static class Exhibit {
        private final SimpleStringProperty name;
        private final SimpleStringProperty date;
        private final SimpleStringProperty visits;

        public Exhibit(String name, String date, String visits) {
            this.name = new SimpleStringProperty(name);
            this.date = new SimpleStringProperty(date);
            this.visits = new SimpleStringProperty(visits);
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

        public String getVisits() {
            return visits.get();
        }
        public void setVisits(String e) {
            visits.set(e);
        }
    }

    public void getData() {
        String query = "select " +
                "a.Exhibit, dt, Visits " +
                "from (Visit_Exhibit a RIGHT JOIN (SELECT b.Exhibit, COUNT(b.Exhibit) AS Visits FROM Visit_Exhibit b GROUP BY b.Exhibit) AS b ON a.Exhibit = b.Exhibit) " +
                "where a.Visitor = 'martha_johnson'";
        Connection con = null;
        try {
            con = AtlantaZoo.conn();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("Exhibit");
                Date date = rs.getDate("dt");
                String visits = rs.getString("Visits");
                System.out.println(visits);
                data.add(new ViewExhibitHistory.Exhibit(name, date.toString(), visits));
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

    public void getFilteredData() {
        String query = "select " +
                "a.Exhibit, dt, Visits " +
                "from (Visit_Exhibit a RIGHT JOIN (SELECT b.Exhibit, COUNT(b.Exhibit) AS Visits FROM Visit_Exhibit b GROUP BY b.Exhibit) AS b ON a.Exhibit = b.Exhibit) " +
                "where a.Visitor = 'martha_johnson' AND a.Exhibit = '" + nameText.getText() + "' AND dt = '" + time.getValue() + "' AND Visits >= " + dropMin.getValue() + " AND Visits <= " + dropMax.getValue();
        Connection con = null;
        try {
            con = AtlantaZoo.conn();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            data.clear();
            while (rs.next()) {
                String name = rs.getString("Exhibit");
                Date date = rs.getDate("dt");
                String exhibit = rs.getString("Visits");
                data.add(new ViewExhibitHistory.Exhibit(name, date.toString(), exhibit));
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
