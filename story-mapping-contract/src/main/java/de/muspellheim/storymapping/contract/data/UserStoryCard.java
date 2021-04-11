/*
 * Story Mapping - Contract
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.contract.data;

public record UserStoryCard(String id, String title, State state) implements Card {
  public UserStoryCard(String id, String title) {
    this(id, title, State.OPEN);
  }
}
