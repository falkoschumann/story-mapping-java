/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import de.muspellheim.storymapping.contract.MessageHandling;
import de.muspellheim.storymapping.contract.data.Board;
import de.muspellheim.storymapping.contract.messages.queries.GetBoardQuery;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class BoardViewModel {
  private final ReadOnlyObjectWrapper<Board> boardProperty = new ReadOnlyObjectWrapper<>();

  private final MessageHandling messageHandling;

  public BoardViewModel(MessageHandling messageHandling) {
    this.messageHandling = messageHandling;
  }

  public ReadOnlyObjectProperty<Board> boardPropertyProperty() {
    return boardProperty.getReadOnlyProperty();
  }

  public Board getBoard() {
    return boardProperty.get();
  }

  public void run() {
    var result = messageHandling.handle(new GetBoardQuery());
    boardProperty.set(result.board());
  }
}
