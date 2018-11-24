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
import visitor.SearchAnimal;


public class ViewAnimal extends SearchAnimal {
  public ViewAnimal() {
    Button removeAnimal = new Button();
    removeAnimal.setText("Remove Animal");
    removeAnimal.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
          //Implement
      }
    });
    this.getChildren().add(removeAnimal);
  }

}
