/*
 * Story Mapping - Contract
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.contract.data;

import java.util.List;

public record ActivityCard(String id, String title, List<? extends Card> userStories)
    implements Card {
  public ActivityCard(String id, String title) {
    this(id, title, List.of());
  }
}
