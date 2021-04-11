/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import de.muspellheim.storymapping.contract.MessageHandling;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class BoardView extends GridPane {
  private final BoardViewModel viewModel;

  public BoardView(MessageHandling messageHandling) {
    setHgap(12);
    setVgap(12);
    setPadding(new Insets(12));
    setBackground(new Background(new BackgroundFill(Color.GHOSTWHITE, null, null)));

    viewModel = new BoardViewModel(messageHandling);
    viewModel.boardPropertyProperty().addListener(o -> updateBoard());
  }

  private void updateBoard() {
    var board = viewModel.getBoard();
    var card = board.cards().get(0);
    var cardView = new CardView();
    cardView.setTitle(card.title());
    cardView.setColor(Color.LIGHTGREEN);
    getChildren().setAll(cardView);
  }

  public void run() {
    viewModel.run();
  }
}
