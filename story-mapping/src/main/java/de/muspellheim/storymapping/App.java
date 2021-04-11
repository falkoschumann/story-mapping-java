/*
 * Story Mapping - App
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping;

import de.muspellheim.storymapping.backend.MessageHandler;
import de.muspellheim.storymapping.frontend.BoardView;
import de.muspellheim.storymapping.frontend.ViewModelFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    // var frontend = new Label("Hello World!");

    var backend = new MessageHandler();
    ViewModelFactory.initMessageHandling(backend);

    var frontend = new BoardView();

    var scene = new Scene(frontend, 480, 320);
    primaryStage.setTitle("Story Mapping");
    primaryStage.setScene(scene);
    primaryStage.show();

    frontend.run();
  }
}
