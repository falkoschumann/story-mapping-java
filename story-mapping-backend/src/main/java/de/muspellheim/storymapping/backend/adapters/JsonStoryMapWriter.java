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
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.stream.JsonGenerator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class JsonStoryMapWriter {
  private final Path file;

  public JsonStoryMapWriter(Path file) {
    this.file = file;
  }

  public void write(Project project) throws IOException {
    Files.createDirectories(file.getParent());

    var factory = Json.createWriterFactory(Map.of(JsonGenerator.PRETTY_PRINTING, true));
    try (var writer = factory.createWriter(Files.newBufferedWriter(file))) {
      var o = Json.createObjectBuilder();
      o.add("type", "Project");
      o.add("name", project.name());
      o.add("stories", writeStories(project.stories()));
      writer.writeObject(o.build());
    }
  }

  private JsonArrayBuilder writeStories(List<? extends Story> stories) {
    var a = Json.createArrayBuilder();
    for (var story : stories) {
      if (story instanceof Goal goal) {
        var o = Json.createObjectBuilder();
        o.add("type", "Goal");
        o.add("id", goal.id());
        o.add("title", goal.title());
        o.add("activities", writeStories(goal.activities()));
        a.add(o);
      } else if (story instanceof Activity activity) {
        var o = Json.createObjectBuilder();
        o.add("type", "Activity");
        o.add("id", activity.id());
        o.add("title", activity.title());
        o.add("userStories", writeStories(activity.userStories()));
        a.add(o);
      } else if (story instanceof UserStory userStory) {
        var o = Json.createObjectBuilder();
        o.add("type", "UserStory");
        o.add("id", userStory.id());
        o.add("title", userStory.title());
        if (userStory.state() != State.EMPTY) {
          o.add("state", userStory.state().name());
        }
        a.add(o);
      } else if (story instanceof Pain pain) {
        var o = Json.createObjectBuilder();
        o.add("type", "Pain");
        o.add("id", pain.id());
        o.add("title", pain.title());
        if (pain.state() != State.EMPTY) {
          o.add("state", pain.state().name());
        }
        a.add(o);
      }
    }
    return a;
  }
}
