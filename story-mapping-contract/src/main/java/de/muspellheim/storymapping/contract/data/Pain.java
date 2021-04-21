/*
 * Story Mapping - Contract
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.contract.data;

public record Pain(String id, String title, State state) implements Story {
  public Pain(String id, String title) {
    this(id, title, State.TODO);
  }
}
