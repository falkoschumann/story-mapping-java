/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import de.muspellheim.storymapping.contract.MessageHandling;
import de.muspellheim.storymapping.contract.data.Activity;
import de.muspellheim.storymapping.contract.data.Goal;
import de.muspellheim.storymapping.contract.data.Story;
import de.muspellheim.storymapping.contract.data.UserStory;
import java.util.List;
import javafx.scene.paint.Color;

public class StoryMapView extends Board {
  private final StoryMapViewModel viewModel;

  public StoryMapView(MessageHandling messageHandling) {
    viewModel = new StoryMapViewModel(messageHandling);
    viewModel.boardPropertyProperty().addListener(o -> updateBoard());
  }

  private void updateBoard() {
    var board = viewModel.getBoard();
    setTitle(board.title());
    createAndPlaceCard(board.stories(), 0, 0);
  }

  private void createAndPlaceCard(List<? extends Story> stories, int column, int row) {
    for (Story story : stories) {
      var card = new Card();
      card.setTitle(story.title());
      card.setColumn(column);
      card.setRow(row);
      getCards().add(card);
      if (story instanceof Goal goalCard) {
        card.setColor(Color.LIGHTSKYBLUE);
        createAndPlaceCard(goalCard.activities(), column, row + 1);
        column += goalCard.activities().size();
      }
      if (story instanceof Activity activityCard) {
        card.setColor(Color.LIGHTGREEN);
        createAndPlaceCard(activityCard.userStories(), column, row + 1);
        column++;
      }
      if (story instanceof UserStory userStoryCard) {
        card.setColor(Color.LIGHTGOLDENRODYELLOW);
        card.setState(userStoryCard.state());
        row++;
      }
    }
  }

  public void run() {
    viewModel.run();
  }
}
