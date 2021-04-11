/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import de.muspellheim.storymapping.contract.MessageHandling;
import javafx.scene.layout.GridPane;

public class BoardView extends GridPane {
  private final BoardViewModel viewModel;

  public BoardView(MessageHandling messageHandling) {
    viewModel = new BoardViewModel(messageHandling);
    viewModel.boardPropertyProperty().addListener(o -> updateBoard());
  }

  private void updateBoard() {
    var board = viewModel.getBoard();
    var cardView = new CardView(board.card());
    getChildren().setAll(cardView);
  }

  public void run() {
    viewModel.run();
  }
}
