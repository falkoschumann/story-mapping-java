/*
 * Story Mapping - Contract
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.contract.data;

public enum State {
  TODO("Todo"),
  DOING("Doing"),
  DONE("Done"),
  EMPTY("");

  private final String title;

  State(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return title;
  }
}
