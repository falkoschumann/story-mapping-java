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

class CardSkin extends SkinBase<Card> {
  private final AnchorPane anchorPane;
  private final Label title;
  private final Region state;
  private final Label constraint;
  private final Label teamMember;

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

    constraint = new Label();
    constraint.getStyleClass().add("constraint");
    AnchorPane.setBottomAnchor(constraint, 4.0);
    AnchorPane.setLeftAnchor(constraint, 8.0);
    anchorPane.getChildren().add(constraint);

    teamMember = new Label();
    teamMember.getStyleClass().add("team-member");
    AnchorPane.setBottomAnchor(teamMember, 4.0);
    AnchorPane.setLeftAnchor(teamMember, 19.0);
    anchorPane.getChildren().add(teamMember);

    registerChangeListener(control.titleProperty(), o -> updateChildren());
    registerChangeListener(control.colorProperty(), o -> updateChildren());
    registerChangeListener(control.stateProperty(), o -> updateChildren());
    updateChildren();
  }

  private void updateChildren() {
    var card = getSkinnable();

    title.setText(card.getTitle());
    anchorPane.setBackground(new Background(new BackgroundFill(card.getColor(), null, null)));

    if (card.getState() != null || card.getTeamMember() != null) {
      AnchorPane.setBottomAnchor(title, 16.0);
    } else {
      AnchorPane.setBottomAnchor(title, 8.0);
    }

    if (card.getState() != null) {
      switch (card.getState()) {
        case TODO -> state.getStyleClass().add("todo");
        case IN_PROGRESS -> state.getStyleClass().add("in-progress");
        case DONE -> state.getStyleClass().add("done");
        case NEXT_ITERATION -> state.getStyleClass().add("next-iteration");
        case CONSTRAINT -> constraint.setText("Constraint");
      }
    }

    teamMember.setText(card.getTeamMember());
  }
}
