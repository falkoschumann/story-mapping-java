/*
 * Story Mapping - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.backend.adapters;

import de.muspellheim.storymapping.contract.data.Activity;
import de.muspellheim.storymapping.contract.data.Goal;
import de.muspellheim.storymapping.contract.data.Project;
import de.muspellheim.storymapping.contract.data.Story;
import de.muspellheim.storymapping.contract.data.UserStory;
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

  public void write(Project project) throws IOException {
    Files.createDirectories(file.getParent());
    lines.clear();

    lines.add("= " + project.title());
    lines.add(":toc:");
    project.stories().forEach(this::write);
    Files.write(file, lines, StandardCharsets.UTF_8);
  }

  private void write(Story card) {
    if (card instanceof Goal goalCard) {
      lines.add("");
      lines.add("== " + goalCard.title());
      goalCard.activities().forEach(this::write);
    } else if (card instanceof Activity activityCard) {
      lines.add("");
      lines.add("=== " + activityCard.title());
      lines.add("");
      activityCard.userStories().forEach(this::write);
    } else if (card instanceof UserStory userStoryCard) {
      lines.add("* " + userStoryCard.title());
    }
  }
}
