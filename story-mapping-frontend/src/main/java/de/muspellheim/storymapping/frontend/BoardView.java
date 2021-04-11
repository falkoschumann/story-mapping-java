/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import javafx.scene.layout.GridPane;

public class BoardView extends GridPane {
  private BoardViewModel viewModel = ViewModelFactory.getBoardViewModel();

  public BoardView() {
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
