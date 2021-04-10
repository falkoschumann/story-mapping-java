/*
 * Story Mapping - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.backend;

import de.muspellheim.storymapping.contract.MessageHandling;
import de.muspellheim.storymapping.contract.data.Board;
import de.muspellheim.storymapping.contract.data.Card;
import de.muspellheim.storymapping.contract.messages.queries.GetBoardQuery;
import de.muspellheim.storymapping.contract.messages.queries.GetBoardQueryResult;

public class MessageHandler implements MessageHandling {
  @Override
  public GetBoardQueryResult handle(GetBoardQuery query) {
    return new GetBoardQueryResult(new Board(new Card("Lorem ipsum")));
  }
}
