/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import de.muspellheim.storymapping.contract.MessageHandling;
import de.muspellheim.storymapping.contract.data.ActivityCard;
import de.muspellheim.storymapping.contract.data.PainCard;
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
          if (card2 instanceof PainCard) {
            cardView2.setColor(Color.LIGHTCORAL);
          } else {
            cardView2.setColor(Color.LIGHTGOLDENRODYELLOW);
          }
        }
      }
    }
  }

  public void run() {
    viewModel.run();
  }
}
