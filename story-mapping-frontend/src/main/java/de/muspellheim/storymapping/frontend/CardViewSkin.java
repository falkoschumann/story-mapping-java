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

class CardViewSkin extends SkinBase<CardView> {
  private final AnchorPane anchorPane;
  private final Label title;

  protected CardViewSkin(CardView control) {
    super(control);

    anchorPane = new AnchorPane();
    anchorPane.setMinSize(180, 120);
    anchorPane.setPrefSize(180, 120);
    anchorPane.setMaxSize(180, 120);
    getChildren().add(anchorPane);

    title = new Label();
    title.getStyleClass().add("title");
    AnchorPane.setTopAnchor(title, 12.0);
    AnchorPane.setRightAnchor(title, 12.0);
    AnchorPane.setBottomAnchor(title, 12.0);
    AnchorPane.setLeftAnchor(title, 12.0);
    anchorPane.getChildren().add(title);

    registerChangeListener(control.titleProperty(), o -> updateChildren());
    registerChangeListener(control.colorProperty(), o -> updateChildren());
    updateChildren();
  }

  private void updateChildren() {
    var cardView = getSkinnable();

    title.setText(cardView.getTitle());
    anchorPane.setBackground(new Background(new BackgroundFill(cardView.getColor(), null, null)));
  }
}
