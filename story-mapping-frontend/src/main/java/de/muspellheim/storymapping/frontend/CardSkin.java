/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;

class CardSkin extends SkinBase<Card> {
  private final AnchorPane anchorPane;
  private final Label title;
  private final Region state;
  private final Tooltip tooltip;

  protected CardSkin(Card control) {
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

    tooltip = new Tooltip();
    control.setTooltip(tooltip);

    registerChangeListener(control.titleProperty(), o -> updateChildren());
    registerChangeListener(control.colorProperty(), o -> updateChildren());
    registerChangeListener(control.stateProperty(), o -> updateChildren());
    updateChildren();
  }

  private void updateChildren() {
    var card = getSkinnable();

    title.setText(card.getTitle());
    anchorPane.setBackground(new Background(new BackgroundFill(card.getColor(), null, null)));

    if (card.getState() != null) {
      AnchorPane.setBottomAnchor(title, 16.0);
      switch (card.getState()) {
        case OPEN:
          break;
        case IN_PROGRESS:
          state.getStyleClass().add("in-progress");
          break;
        case DONE:
          state.getStyleClass().add("done");
          break;
        case NEXT_ITERATION:
          state.getStyleClass().add("next-iteration");
          break;
      }
    } else {
      AnchorPane.setBottomAnchor(title, 8.0);
    }

    var tooltipPrefix = card.getState() != null ? "[" + card.getState() + "] " : "";
    tooltip.setText(tooltipPrefix + card.getTitle());
  }
}
