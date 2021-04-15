/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import de.muspellheim.storymapping.contract.MessageHandling;
import de.muspellheim.storymapping.contract.data.ActivityCard;
import de.muspellheim.storymapping.contract.data.Card;
import de.muspellheim.storymapping.contract.data.GoalCard;
import de.muspellheim.storymapping.contract.data.UserStoryCard;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class BoardView extends GridPane {
  // TODO ScrollPane herumlegen
  private final BoardViewModel viewModel;

  public BoardView(MessageHandling messageHandling) {
    setHgap(10);
    setVgap(10);
    setPadding(new Insets(10));
    setBackground(new Background(new BackgroundFill(Color.GHOSTWHITE, null, null)));

    viewModel = new BoardViewModel(messageHandling);
    viewModel.boardPropertyProperty().addListener(o -> updateBoard());
  }

  private void updateBoard() {
    var board = viewModel.getBoard();
    createAndPlaceCard(board.cards(), 0, 0);
  }

  private void createAndPlaceCard(List<Card> cards, int columnIndex, int rowIndex) {
    for (Card card : cards) {
      var cardView = new CardView();
      cardView.setTitle(card.title());
      GridPane.setConstraints(cardView, columnIndex, rowIndex);
      getChildren().add(cardView);
      if (card instanceof GoalCard goalCard) {
        cardView.setColor(Color.LIGHTSKYBLUE);
        createAndPlaceCard(goalCard.activities(), columnIndex, rowIndex + 1);
        columnIndex += goalCard.activities().size();
      }
      if (card instanceof ActivityCard activityCard) {
        cardView.setColor(Color.LIGHTGREEN);
        createAndPlaceCard(activityCard.userStories(), columnIndex, rowIndex + 1);
        columnIndex++;
      }
      if (card instanceof UserStoryCard userStoryCard) {
        cardView.setColor(Color.LIGHTGOLDENRODYELLOW);
        cardView.setState(userStoryCard.state());
        rowIndex++;
      }
    }
  }

  public void run() {
    viewModel.run();
  }
}
