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

public class AddShow extends VBox {

  public AddShow() {
      Label title = new Label("Add Show");

      Label nameLabel = new Label("Name");
      TextField nameText = new TextField ();
      HBox name = new HBox();
      name.getChildren().addAll(nameLabel, nameText);

      HBox exhibit = new HBox();
      Label exhibitLabel = new Label("Exhbit");
      ComboBox<String> exhibitDropDown = new ComboBox<String>();
      exhibitDropDown.getItems().addAll("Yes", "No");
      exhibit.getChildren().addAll(exhibitLabel, exhibitDropDown);

      HBox staff = new HBox();
      Label staffLabel = new Label("Staff");
      ComboBox<String> staffDropDown = new ComboBox<String>();
      staffDropDown.getItems().addAll("Yes", "No");
      staff.getChildren().addAll(staffLabel, staffDropDown);

      DatePicker date = new DatePicker();

      Label timeLabel = new Label("Time");
      TextField timeText = new TextField ();
      HBox time = new HBox();
      time.getChildren().addAll(timeLabel, timeText);



      Button addShow = new Button();
      addShow.setText("Add Show");
      addShow.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            //Implement
        }
      });


      this.getChildren().addAll(title, name, exhibit, staff, date, time, addShow);

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
