/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import javafx.collections.ListChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class BoardSkin extends SkinBase<Board> {
  private final GridPane gridPane;
  private final Label title;

  protected BoardSkin(Board control) {
    super(control);

    var vBox = new VBox();
    getChildren().add(vBox);

    title = new Label();
    title.getStyleClass().add("title");
    vBox.getChildren().add(title);

    // TODO ScrollPane herumlegen
    gridPane = new GridPane();
    VBox.setVgrow(gridPane, Priority.ALWAYS);
    vBox.getChildren().add(gridPane);

    registerChangeListener(control.titleProperty(), o -> updateChildren());
    control.getCards().addListener((ListChangeListener<? super Card>) o -> updateChildren());
    updateChildren();
  }

  private void updateChildren() {
    var board = getSkinnable();

    title.setText(board.getTitle());

    var cards = board.getCards();
    for (var card : cards) {
      System.out.println(card);
      GridPane.setConstraints(card, card.getColumn(), card.getRow());
    }
    gridPane.getChildren().setAll(cards);
  }
}
