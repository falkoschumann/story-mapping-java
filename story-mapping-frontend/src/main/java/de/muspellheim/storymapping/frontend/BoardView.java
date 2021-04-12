/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import de.muspellheim.storymapping.contract.MessageHandling;
import de.muspellheim.storymapping.contract.data.ActivityCard;
import de.muspellheim.storymapping.contract.data.PainCard;
import de.muspellheim.storymapping.contract.data.UserStoryCard;
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
    // TODO Extrahiere Methode createAndPlaceCard()
    var board = viewModel.getBoard();
    for (int i = 0; i < board.cards().size(); i++) {
      var card1 = board.cards().get(i);
      var cardView1 = new CardView();
      cardView1.setTitle(card1.title());
      GridPane.setConstraints(cardView1, i, 0);
      getChildren().add(cardView1);
      if (card1 instanceof ActivityCard activityCard) {
        cardView1.setColor(Color.LIGHTGREEN);

        for (int j = 0; j < activityCard.userStories().size(); j++) {
          var card2 = activityCard.userStories().get(j);
          var cardView2 = new CardView();
          cardView2.setTitle(card2.title());
          GridPane.setConstraints(cardView2, i, j + 1);
          getChildren().add(cardView2);
          if (card2 instanceof UserStoryCard userStoryCard) {
            cardView2.setColor(Color.LIGHTGOLDENRODYELLOW);
            cardView2.setState(userStoryCard.state());
          } else if (card2 instanceof PainCard painCard) {
            cardView2.setColor(Color.LIGHTCORAL);
            cardView2.setState(painCard.state());
          }
        }
      }
    }
  }

  public void run() {
    viewModel.run();
  }
}
