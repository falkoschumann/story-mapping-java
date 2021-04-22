/*
 * Story Mapping - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.backend.adapters;

import de.muspellheim.storymapping.contract.data.Activity;
import de.muspellheim.storymapping.contract.data.Goal;
import de.muspellheim.storymapping.contract.data.Pain;
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
import java.util.regex.Pattern;

public class AsciiDocStoryMapReader {
  private final Path file;

  private String boardTitle;
  private final Deque<Goal> goals = new LinkedList<>();

  private int goalIndex = 1;
  private final Deque<Activity> activities = new LinkedList<>();

  private int activityIndex = 1;
  private final Deque<Story> userStories = new LinkedList<>();

  private int userStoryIndex = 1;
  private int painIndex = 1;

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
        State state = null;
        if (title.startsWith("[ ] ")) {
          title = title.substring(4);
          state = State.TODO;
        } else if (title.startsWith("[x] ")) {
          title = title.substring(4);
          state = State.DONE;
        } else if (title.startsWith("[Constraint] ")) {
          title = title.substring(13);
          state = State.CONSTRAINT;
        }

        var isPain = false;
        if (title.startsWith("[Pain] ")) {
          isPain = true;
          title = title.substring(7);
        }

        String teamMember = null;
        var matcher = Pattern.compile("^\\((.+)\\) .*").matcher(title);
        if (matcher.matches()) {
          teamMember = matcher.group(1);
          title = title.substring(teamMember.length() + 3);
          state = state == State.DONE ? state : State.IN_PROGRESS;
        }

        if (isPain) {
          userStories.addFirst(new Pain("P" + painIndex++, title, state, teamMember));
        } else {
          userStories.addFirst(new UserStory("U" + userStoryIndex++, title, state, teamMember));
        }
      }
    }
    return new Project(boardTitle, List.copyOf(goals));
  }
}
