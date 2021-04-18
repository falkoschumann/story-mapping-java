/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import de.muspellheim.storymapping.contract.MessageHandling;
import de.muspellheim.storymapping.contract.data.Project;
import de.muspellheim.storymapping.contract.messages.queries.GetBoardQuery;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class StoryMapViewModel {
  private final ReadOnlyObjectWrapper<Project> boardProperty = new ReadOnlyObjectWrapper<>();

  private final MessageHandling messageHandling;

  public StoryMapViewModel(MessageHandling messageHandling) {
    this.messageHandling = messageHandling;
  }

  public ReadOnlyObjectProperty<Project> boardPropertyProperty() {
    return boardProperty.getReadOnlyProperty();
  }

  public Project getBoard() {
    return boardProperty.get();
  }

  public void run() {
    var result = messageHandling.handle(new GetBoardQuery());
    boardProperty.set(result.board());
  }
}
