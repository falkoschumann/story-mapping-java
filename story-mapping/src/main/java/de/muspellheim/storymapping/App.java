/*
 * Story Mapping - App
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class App extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    var root = new Label("Hello World!");
    var scene = new Scene(root, 480, 320);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
