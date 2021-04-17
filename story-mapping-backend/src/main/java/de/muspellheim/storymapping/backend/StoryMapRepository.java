/*
 * Story Mapping - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.backend;

import de.muspellheim.storymapping.contract.data.Board;

public interface StoryMapRepository {
  Board loadBoard();

  void storeBoard(Board board);
}
