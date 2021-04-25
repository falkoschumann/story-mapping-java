/*
 * Story Mapping - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.backend.adapters;

import de.muspellheim.storymapping.contract.data.Activity;
import de.muspellheim.storymapping.contract.data.Goal;
import de.muspellheim.storymapping.contract.data.Project;
import de.muspellheim.storymapping.contract.data.State;
import de.muspellheim.storymapping.contract.data.Story;
import de.muspellheim.storymapping.contract.data.UserStory;
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
  private final Deque<Goal> goals = new LinkedList<>();

  private int goalIndex = 1;
  private final Deque<Activity> activities = new LinkedList<>();

  private int activityIndex = 1;
  private final Deque<Story> userStories = new LinkedList<>();

  private int userStoryIndex = 1;

  public AsciiDocStoryMapReader(Path file) {
    this.file = file;
  }

  public Project read() throws IOException {
    var lines = Files.readAllLines(file, StandardCharsets.UTF_8);
    goals.clear();
    for (int i = lines.size() - 1; i >= 0; i--) {
      var line = lines.get(i);
      if (line.startsWith("= ")) {
        boardTitle = line.substring(2);
      } else if (line.startsWith("== ")) {
        var title = line.substring(3);
        goals.addFirst(new Goal("G" + goalIndex++, title, List.copyOf(activities)));
        activities.clear();
      } else if (line.startsWith("=== ")) {
        var title = line.substring(4);
        activities.addFirst(new Activity("A" + activityIndex++, title, List.copyOf(userStories)));
        userStories.clear();
      } else if (line.startsWith("* ")) {
        var title = line.substring(2);
        State state = State.UNKNOWN;
        if (title.startsWith("[ ] ")) {
          title = title.substring(4);
          state = State.TODO;
        } else if (title.startsWith("[x] ")) {
          title = title.substring(4);
          state = State.DONE;
        }
        userStories.addFirst(new UserStory("U" + userStoryIndex++, title, state));
      }
    }
    return new Project(boardTitle, List.copyOf(goals));
  }
}
