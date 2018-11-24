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

public class SearchShow extends VBox {

  public SearchShow() {
      Label title = new Label("Shows");

      Label nameLabel = new Label("Name");
      TextField nameText = new TextField ();
      HBox name = new HBox();
      name.getChildren().addAll(nameLabel, nameText);

      DatePicker date = new DatePicker();

      HBox exhibit = new HBox();
      Label exhibitLabel = new Label("Exhbit");
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
      row1.getChildren().addAll(name, date);

      HBox row2 = new HBox();
      row2.getChildren().addAll(exhibit, search);


      TableView table = new TableView();
      table.setEditable(true);
      TableColumn nameCol = new TableColumn("Name");
      TableColumn exhibitsCol = new TableColumn("Exhibit");
      TableColumn dateCol = new TableColumn("Date");
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


      table.getColumns().addAll(nameCol, exhibitsCol, dateCol);

      Button logVisit = new Button();
      logVisit.setText("Search");
      logVisit.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            //Implement
        }
      });

      this.getChildren().addAll(title, row1, row2, table, logVisit);

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

}
