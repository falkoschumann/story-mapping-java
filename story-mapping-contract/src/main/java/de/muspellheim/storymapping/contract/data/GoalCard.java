/*
 * Story Mapping - Contract
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.contract.data;

import java.util.List;

public record GoalCard(String id, String title, List<? extends Card> activities) implements Card {
  public GoalCard(String id, String title) {
    this(id, title, List.of());
  }
}
