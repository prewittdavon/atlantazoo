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

public class SearchAnimal extends VBox {

  public SearchAnimal() {
      Label title = new Label("Animals");


      HBox exhibit = new HBox();
      Label exhibitLabel = new Label("Exhibit");
      ComboBox<String> exhibitDropDown = new ComboBox<String>();
      exhibitDropDown.getItems().addAll("Yes", "No");
      exhibit.getChildren().addAll(exhibitLabel, exhibitDropDown);

      Label nameLabel = new Label("Name");
      TextField nameText = new TextField ();
      HBox name = new HBox();
      name.getChildren().addAll(nameLabel, nameText);

      Label speciesLabel = new Label("Species");
      TextField speciesText = new TextField ();
      HBox species = new HBox();
      species.getChildren().addAll(speciesLabel, speciesText);

      HBox age = new HBox();
      age.getChildren().addAll(new Label("Age"), dropDown("Min", 0, 10), dropDown("Max", 0, 10));


      HBox type = new HBox();
      Label typeLabel = new Label("Type");
      ComboBox<String> typeDropDown = new ComboBox<String>();
      typeDropDown.getItems().addAll("Yes", "No");
      type.getChildren().addAll(typeLabel, typeDropDown);

      Button search = new Button();
      search.setText("Search");
      search.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            //Implement
        }
      });


      HBox row1 = new HBox();
      row1.getChildren().addAll(title, exhibit);

      HBox row2 = new HBox();
      row2.getChildren().addAll(name, age);
      HBox row3 = new HBox();
      row3.getChildren().addAll(species, type);

      TableView table = new TableView();
      table.setEditable(true);
      TableColumn nameCol = new TableColumn("Name");
      TableColumn speciesCol = new TableColumn("Species");
      TableColumn numAnimalsCol = new TableColumn("Exhibit");
      TableColumn waterCol = new TableColumn("Age");
      TableColumn typeCol = new TableColumn("Type");
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


      table.getColumns().addAll(nameCol, speciesCol, numAnimalsCol, waterCol, typeCol);
      this.getChildren().addAll(row1, row2, row3, search, table);

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
