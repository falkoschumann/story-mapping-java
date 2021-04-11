/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import de.muspellheim.storymapping.contract.MessageHandling;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BoardStageController {
  private final BoardView view;

  public BoardStageController(MessageHandling messageHandling, Stage stage) {
    view = new BoardView(messageHandling);
    var scene = new Scene(view, 1024, 640);
    stage.setScene(scene);
    stage.setTitle("Story Mapping");
    stage.show();
  }

  public void run() {
    view.run();
  }
}
