/*
 * Story Mapping - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.backend;

import de.muspellheim.storymapping.contract.MessageHandling;
import de.muspellheim.storymapping.contract.data.Board;
import de.muspellheim.storymapping.contract.data.UserStoryCard;
import de.muspellheim.storymapping.contract.messages.queries.GetBoardQuery;
import de.muspellheim.storymapping.contract.messages.queries.GetBoardQueryResult;
import java.util.List;

public class MessageHandler implements MessageHandling {
  @Override
  public GetBoardQueryResult handle(GetBoardQuery query) {
    return new GetBoardQueryResult(new Board(List.of(new UserStoryCard("1", "Lorem ipsum"))));
  }
}
