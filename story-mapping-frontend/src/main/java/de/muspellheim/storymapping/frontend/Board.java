/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class Board extends Control {
  private final ObservableList<Card> cards = FXCollections.observableArrayList();

  public Board() {
    initialize();
  }

  private void initialize() {
    getStyleClass().setAll("board");
  }

  public final ObservableList<Card> getCards() {
    return cards;
  }

  @Override
  protected Skin<?> createDefaultSkin() {
    return new BoardSkin(this);
  }

  @Override
  public String getUserAgentStylesheet() {
    return Objects.requireNonNull(Card.class.getResource("board.css")).toExternalForm();
  }
}
