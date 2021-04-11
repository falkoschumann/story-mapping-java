/*
 * Story Mapping - App
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping;

import de.muspellheim.storymapping.backend.MessageHandler;
import de.muspellheim.storymapping.frontend.BoardStageController;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
  @Override
  public void start(Stage primaryStage) {
    var backend = new MessageHandler();
    var frontend = new BoardStageController(backend, primaryStage);
    frontend.run();
  }
}
