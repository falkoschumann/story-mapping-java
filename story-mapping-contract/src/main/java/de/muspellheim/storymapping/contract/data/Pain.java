/*
 * Story Mapping - Contract
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.contract.data;

public record Pain(String id, String title, State state, String teamMember) implements Story {
  public Pain(String id, String title) {
    this(id, title, null, null);
  }

  public Pain(String id, String title, State state) {
    this(id, title, state, null);
  }
}
