package staff;

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

public class SearchExhibit extends VBox {

  public SearchExhibit() {
      Label title = new Label("Exhibits");


      Label nameLabel = new Label("Name");
      TextField nameText = new TextField ();
      HBox name = new HBox();
      name.getChildren().addAll(nameLabel, nameText);

      HBox numVisits = new HBox();
      numVisits.getChildren().addAll(new Label("Num Visits"), dropDown("Min", 0, 10), dropDown("Max", 0, 10));

      DatePicker time = new DatePicker();

      Button search = new Button();
      search.setText("Search");
      search.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            //Implement
        }
      });

      HBox row1 = new HBox();
      row1.getChildren().addAll(name, numVisits);

      HBox row2 = new HBox();
      row2.getChildren().addAll(time, search);


      TableView table = new TableView();
      table.setEditable(true);
      TableColumn nameCol = new TableColumn("Name");
      TableColumn timeCol = new TableColumn("Time");
      TableColumn numVisitsCol = new TableColumn("Num Visits");


      table.getColumns().addAll(nameCol, timeCol, numVisitsCol);
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

  // public void getData() {
  //   String query = "select " +
  //                  "* " +
  //                  "from Exhibit_Info";
  //   Connection con = null;
  //   try {
  //       con = AtlantaZoo.conn();
  //       Statement stmt = con.createStatement();
  //       ResultSet rs = stmt.executeQuery(query);
  //
  //       while (rs.next()) {
  //           String name = rs.getString("Name");
  //           String size = rs.getString("Size");
  //           String animals = rs.getString("Animals");
  //           String water = rs.getString("Water");
  //           data.add(new Exhibit(name, size, animals, water));
  //       }
  //   } catch (Exception e) {
  //       //JDBCTutorialUtilities.printSQLException(e);
  //       System.out.println(e.getMessage());
  //   } finally {
  //     try {
  //       if(con != null) con.close();
  //     } catch (Exception f) {
  //
  //     }
  //   }
  // }

}
