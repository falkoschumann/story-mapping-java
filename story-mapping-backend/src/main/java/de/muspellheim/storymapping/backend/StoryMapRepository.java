/*
 * Story Mapping - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.backend;

import de.muspellheim.storymapping.contract.data.Project;

public interface StoryMapRepository {
  Project loadBoard();

  void storeBoard(Project board);
}
