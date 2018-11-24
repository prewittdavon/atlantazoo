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

public class AnimalDetail extends VBox {

  public AnimalDetail(String name, String species,
    int age, String exhibit, String type) {
      Label title = new Label("Animal Detail");
      String info = String.format("Name: %s    Species:%s     Age:%d months     Exhibit:%s      Type:%s",
        name, species, age, exhibit, type);
      Label infoLabel = new Label(info);

      this.getChildren().addAll(title, infoLabel);

  }

}
