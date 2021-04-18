/*
 * Story Mapping - Contract
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.contract.data;

import java.util.List;

public record Activity(String id, String title, List<? extends Story> userStories)
    implements Story {
  public Activity(String id, String title) {
    this(id, title, List.of());
  }
}
