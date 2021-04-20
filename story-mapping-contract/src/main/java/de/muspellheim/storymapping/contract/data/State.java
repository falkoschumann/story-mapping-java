/*
 * Story Mapping - Contract
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.contract.data;

public enum State {
  OPEN("Open"),
  IN_PROGRESS("In progress"),
  DONE("Done"),
  NEXT_ITERATION("Next iteration");

  private final String title;

  State(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return title;
  }
}
