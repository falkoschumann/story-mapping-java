/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import de.muspellheim.storymapping.contract.MessageHandling;

public class ViewModelFactory {
  private static MessageHandling messageHandling;

  public static void initMessageHandling(MessageHandling messageHandling) {
    ViewModelFactory.messageHandling = messageHandling;
  }

  public static BoardViewModel getBoardViewModel() {
    return new BoardViewModel(messageHandling);
  }
}
