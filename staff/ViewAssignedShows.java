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

  }


}
