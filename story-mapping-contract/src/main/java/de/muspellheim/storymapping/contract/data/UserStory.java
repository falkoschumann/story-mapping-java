/*
 * Story Mapping - Contract
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.contract.data;

public record UserStory(String id, String title, State state, String teamMember) implements Story {
  public UserStory(String id, String title) {
    this(id, title, null, null);
  }

  public UserStory(String id, String title, State state) {
    this(id, title, state, null);
  }
}
