/*
 * Story Mapping - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.backend.adapters;

import de.muspellheim.storymapping.contract.data.ActivityCard;
import de.muspellheim.storymapping.contract.data.Board;
import de.muspellheim.storymapping.contract.data.GoalCard;
import de.muspellheim.storymapping.contract.data.UserStoryCard;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class AsciiDocStoryMapReader {
  private final Path file;

  private String boardTitle;
  private final Deque<GoalCard> goals = new LinkedList<>();

  private int goalIndex = 1;
  private final Deque<ActivityCard> activities = new LinkedList<>();

  private int activityIndex = 1;
  private final Deque<UserStoryCard> userStories = new LinkedList<>();

  private int userStoryIndex = 1;

  public AsciiDocStoryMapReader(Path file) {
    this.file = file;
  }

  public Board read() throws IOException {
    var lines = Files.readAllLines(file, StandardCharsets.UTF_8);
    goals.clear();
    for (int i = lines.size() - 1; i >= 0; i--) {
      var line = lines.get(i);
      if (line.startsWith("= ")) {
        boardTitle = line.substring(2);
        System.out.println("Found board: " + boardTitle);
      } else if (line.startsWith("== ")) {
        var title = line.substring(3);
        System.out.println("Found goal: " + title);
        goals.addFirst(new GoalCard("G" + goalIndex++, title, List.copyOf(activities)));
        activities.clear();
      } else if (line.startsWith("=== ")) {
        var title = line.substring(4);
        System.out.println("Found activity: " + title);
        activities.addFirst(
            new ActivityCard("A" + activityIndex++, title, List.copyOf(userStories)));
        userStories.clear();
      } else if (line.startsWith("* ")) {
        var title = line.substring(2);
        System.out.println("Found user story: " + title);
        userStories.addFirst(new UserStoryCard("U" + userStoryIndex++, title));
      }
    }
    return new Board(boardTitle, List.copyOf(goals));
  }
}
