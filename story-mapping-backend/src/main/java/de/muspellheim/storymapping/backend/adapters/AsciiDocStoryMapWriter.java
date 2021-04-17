/*
 * Story Mapping - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.backend.adapters;

import de.muspellheim.storymapping.contract.data.ActivityCard;
import de.muspellheim.storymapping.contract.data.Board;
import de.muspellheim.storymapping.contract.data.Card;
import de.muspellheim.storymapping.contract.data.GoalCard;
import de.muspellheim.storymapping.contract.data.UserStoryCard;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AsciiDocStoryMapWriter {
  private final Path file;
  private final List<String> lines = new ArrayList<>();

  public AsciiDocStoryMapWriter(Path file) {
    this.file = file;
  }

  public void write(Board board) throws IOException {
    Files.createDirectories(file.getParent());
    lines.clear();

    lines.add("= " + board.title());
    lines.add(":toc:");
    board.cards().forEach(this::write);
    Files.write(file, lines, StandardCharsets.UTF_8);
  }

  private void write(Card card) {
    if (card instanceof GoalCard goalCard) {
      lines.add("");
      lines.add("== " + goalCard.title());
      goalCard.activities().forEach(this::write);
    } else if (card instanceof ActivityCard activityCard) {
      lines.add("");
      lines.add("=== " + activityCard.title());
      lines.add("");
      activityCard.userStories().forEach(this::write);
    } else if (card instanceof UserStoryCard userStoryCard) {
      lines.add("* " + userStoryCard.title());
    }
  }
}
