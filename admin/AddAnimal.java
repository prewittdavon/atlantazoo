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

public class AddAnimal extends VBox {

  public AddAnimal() {
      Label title = new Label("Add Animal");


      Label nameLabel = new Label("Name");
      TextField nameText = new TextField ();
      HBox name = new HBox();
      name.getChildren().addAll(nameLabel, nameText);

      HBox exhibit = new HBox();
      Label exhibitLabel = new Label("Exhibit");
      ComboBox<String> exhibitDropDown = new ComboBox<String>();
      exhibitDropDown.getItems().addAll("Yes", "No");
      exhibit.getChildren().addAll(exhibitLabel, exhibitDropDown);

      HBox type = new HBox();
      Label typeLabel = new Label("Type");
      ComboBox<String> typeDropDown = new ComboBox<String>();
      typeDropDown.getItems().addAll("Yes", "No");
      type.getChildren().addAll(typeLabel, typeDropDown);

      Label speciesLabel = new Label("Species");
      TextField speciesText = new TextField ();
      HBox species = new HBox();
      species.getChildren().addAll(speciesLabel, speciesText);

      HBox age = new HBox();
      age.getChildren().addAll(new Label("Age"), dropDown("Min", 0, 10), dropDown("Max", 0, 10));



      Button addAnimal = new Button();
      addAnimal.setText("Add Animal");
      addAnimal.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            //Implement
        }
      });

      this.getChildren().addAll(title, name, exhibit, type, species, age, addAnimal);


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
