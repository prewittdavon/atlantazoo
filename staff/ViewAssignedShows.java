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

public class ViewAssignedShows extends VBox {

  public ViewAssignedShows() {
      Label title = new Label("Staff - Show History");


      TableView table = new TableView();
      table.setEditable(true);
      TableColumn nameCol = new TableColumn("Name");
      TableColumn timeCol = new TableColumn("Size");
      TableColumn exhibitsCol = new TableColumn("Exhibits");

      VBox nameBox = new VBox();
      VBox dtBox = new VBox();
      VBox exhbBox = new VBox();
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


      table.getColumns().addAll(nameCol, timeCol, exhibitsCol);
      this.getChildren().addAll(title, table);

      ArrayList<String> name = new ArrayList<>();
      ArrayList<String> dt = new ArrayList<>();
      ArrayList<String> exhb = new ArrayList<>();

      public void getData() {
        String query = "select " +
                       "* " +
                       "from Shows" +
                       "where "; //TODO get the instance of staff
        Connection con = null;
        try {
            con = AtlantaZoo.conn();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                name.add(rs.getString("Name"));
                dt.add(rs.getString("dt"));
                exhb.add(rs.getString("Exhibit"));
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

      nameBox.getChildren.addAll(name);
      dtBox.getChildren.addAll(dt);
      exhbBox.getChildren.addAll(exhb);


  }


}
