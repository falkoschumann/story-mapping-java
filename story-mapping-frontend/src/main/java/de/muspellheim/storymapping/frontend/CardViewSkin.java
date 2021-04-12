/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;

class CardViewSkin extends SkinBase<CardView> {
  private final AnchorPane anchorPane;
  private final Label title;
  private final Region state;

  protected CardViewSkin(CardView control) {
    super(control);

    anchorPane = new AnchorPane();
    getChildren().add(anchorPane);

    title = new Label();
    title.getStyleClass().add("title");
    AnchorPane.setTopAnchor(title, 8.0);
    AnchorPane.setRightAnchor(title, 8.0);
    AnchorPane.setBottomAnchor(title, 8.0);
    AnchorPane.setLeftAnchor(title, 8.0);
    anchorPane.getChildren().add(title);

    state = new Region();
    state.getStyleClass().add("state");
    AnchorPane.setBottomAnchor(state, 4.0);
    AnchorPane.setLeftAnchor(state, 4.0);
    anchorPane.getChildren().add(state);

    registerChangeListener(control.titleProperty(), o -> updateChildren());
    registerChangeListener(control.colorProperty(), o -> updateChildren());
    registerChangeListener(control.stateProperty(), o -> updateChildren());
    updateChildren();
  }

  private void updateChildren() {
    var cardView = getSkinnable();

    title.setText(cardView.getTitle());
    anchorPane.setBackground(new Background(new BackgroundFill(cardView.getColor(), null, null)));

    if (cardView.getState() != null) {
      AnchorPane.setBottomAnchor(title, 16.0);
      switch (cardView.getState()) {
        case OPEN:
        case CLOSED:
          break;
        case READY:
          state.getStyleClass().add("ready");
          break;
        case IN_PROGRESS:
          state.getStyleClass().add("in-progress");
          break;
        case DONE:
          state.getStyleClass().add("done");
          break;
      }
    } else {
      AnchorPane.setBottomAnchor(title, 8.0);
    }
  }
}
