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


public class SearchExhibit extends VBox {

  ObservableList<Exhibit> data =
      FXCollections.observableArrayList();

  public SearchExhibit() {
      Label title = new Label("Exhibits");
      Label nameLabel = new Label("Name");
      TextField nameText = new TextField ();
      HBox name = new HBox();
      name.getChildren().addAll(nameLabel, nameText);

      HBox numAnimals = new HBox();
      numAnimals.getChildren().addAll(new Label("Num Animals"), dropDown("Min", 0, 10), dropDown("Max", 0, 10));

      HBox size = new HBox();
      size.getChildren().addAll(new Label("Size"), dropDown("Min", 0, 900), dropDown("Max", 0, 900));
      //DatePicker time = new DatePicker();

      HBox water = new HBox();
      water.getChildren().addAll(new Label("Water"), waterDropDown());

      Button search = new Button();
      search.setText("Search");
      search.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            String nameField = nameLabel.getText();

            VBox numAnBox1 = (VBox) numAnimals.getChildren().get(1);
            ComboBox dropMin = (ComboBox) numAnBox1.getChildren().get(1);
            String numAnimalsMin = (String) dropMin.getValue();

            VBox numAnBox2 = (VBox) numAnimals.getChildren().get(2);
            ComboBox dropMax = (ComboBox) numAnBox2.getChildren().get(1);
            String numAnimalsMax = (String) dropMax.getValue();

            VBox sizeBox1 = (VBox) size.getChildren().get(1);
            ComboBox sdropMin = (ComboBox) sizeBox1.getChildren().get(1);
            String sizeMin = (String) sdropMin.getValue();

            VBox sizeBox2 = (VBox) size.getChildren().get(2);
            ComboBox sdropMax = (ComboBox) sizeBox2.getChildren().get(1);
            String sizeMax = (String) sdropMax.getValue();

            VBox waterBox = (VBox) water.getChildren().get(1);
            ComboBox waterF = (ComboBox) waterBox.getChildren().get(1);
            String waterFeat = (String) waterF.getValue();

            String query = " SELECT * FROM Exhibit_Info" +
              " WHERE Name=" + nameField +
              " AND Animals>=" + numAnimalsMin +
              " AND Animals<=" + numAnimalsMax +
              " AND Size>=" + sizeMin +
              " AND Size<=" + sizeMax +
              " AND Water=" + (waterFeat == "Yes");

            data.clear();

            Connection con = null;
            try {
                con = AtlantaZoo.conn();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String name = rs.getString("Name");
                    String size = rs.getString("Size");
                    String animals = rs.getString("Animals");
                    String water = rs.getString("Water").equals("1") ? "Yes":"No";
                    data.add(new Exhibit(name, size, animals, water));
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
      row1.getChildren().addAll(name, numAnimals);

      HBox row2 = new HBox();
      row2.getChildren().addAll(size, water, search);


      TableView table = new TableView();
      table.setEditable(true);

      TableColumn nameCol = new TableColumn("Name");
      nameCol.setCellValueFactory(
        new PropertyValueFactory<>("name"));

      TableColumn sizeCol = new TableColumn("Size");
      sizeCol.setCellValueFactory(
        new PropertyValueFactory<>("size"));

      TableColumn numAnimalsCol = new TableColumn("Num Animals");
      numAnimalsCol.setCellValueFactory(
        new PropertyValueFactory<>("numAnimals"));

      TableColumn waterCol = new TableColumn("Water");
      waterCol.setCellValueFactory(
        new PropertyValueFactory<>("water"));

      getData();
      table.setItems(data);
      table.getColumns().addAll(nameCol, sizeCol, numAnimalsCol, waterCol);
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

  public VBox waterDropDown() {
        Label label = new Label("water");
        ComboBox<String> dropDown = new ComboBox<String>();
        dropDown.getItems().add("Yes");
        dropDown.getItems().add("No");
        dropDown.setValue(null);
        VBox box = new VBox();
        box.getChildren().addAll(label, dropDown);
        return box;
  }

  public void getData() {
    String query = "select " +
                   "* " +
                   "from Exhibit_Info";
    Connection con = null;
    try {
        con = AtlantaZoo.conn();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String name = rs.getString("Name");
            String size = rs.getString("Size");
            String animals = rs.getString("Animals");
            String water = rs.getString("Water").equals("1") ? "Yes":"No";
            data.add(new Exhibit(name, size, animals, water));
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

  public static class Exhibit {
      private final SimpleStringProperty name;
      private final SimpleStringProperty size;
      private final SimpleStringProperty numAnimals;
      private final SimpleStringProperty water;

      public Exhibit(String name, String size, String numAnimals, String water) {
          this.name = new SimpleStringProperty(name);
          this.size = new SimpleStringProperty(size);
          this.numAnimals = new SimpleStringProperty(numAnimals);
          this.water = new SimpleStringProperty(water);
      }

      public String getName() {
          return name.get();
      }
      public void setName(String n) {
          name.set(n);
      }
      public String getSize() {
          return size.get();
      }
      public void setSize(String s) {
          size.set(s);
      }
      public String getNumAnimals() {
          return numAnimals.get();
      }
      public void setNumAnimals(String a) {
          numAnimals.set(a);
      }
      public String getWater() {
          return water.get();
      }
      public void setWater(String w) {
          water.set(w);
      }
  }
}
