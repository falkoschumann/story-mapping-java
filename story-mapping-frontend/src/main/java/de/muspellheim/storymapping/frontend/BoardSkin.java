/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import javafx.collections.ListChangeListener;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class BoardSkin extends SkinBase<Board> {
  private final GridPane gridPane;

  protected BoardSkin(Board control) {
    super(control);

    // TODO ScrollPane herumlegen
    gridPane = new GridPane();
    VBox.setVgrow(gridPane, Priority.ALWAYS);
    getChildren().add(gridPane);

    control.getCards().addListener((ListChangeListener<? super Card>) o -> updateChildren());
    updateChildren();
  }

  private void updateChildren() {
    var board = getSkinnable();

    var cards = board.getCards();
    for (var card : cards) {
      GridPane.setConstraints(card, card.getColumn(), card.getRow());
    }
    gridPane.getChildren().setAll(cards);
  }
}
