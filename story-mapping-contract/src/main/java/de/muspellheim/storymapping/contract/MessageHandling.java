/*
 * Story Mapping - Contract
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.contract;

import de.muspellheim.storymapping.contract.messages.commands.OpenFileCommand;
import de.muspellheim.storymapping.contract.messages.queries.GetBoardQuery;
import de.muspellheim.storymapping.contract.messages.queries.GetBoardQueryResult;

public interface MessageHandling {
  void handle(OpenFileCommand command);

  GetBoardQueryResult handle(GetBoardQuery query);
}
