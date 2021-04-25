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
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonStoryMapReader {
  private final Path file;

  public JsonStoryMapReader(Path file) {
    this.file = file;
  }

  public Project read() throws IOException {
    try (var reader = Json.createReader(Files.newBufferedReader(file))) {
      return readProject(reader.readObject());
    }
  }

  private Project readProject(JsonObject o) {
    var type = o.getString("type");
    if (!"Project".equals(type)) {
      throw new IllegalStateException("Object is not a project: " + o);
    }

    var name = o.getString("name");
    var stories = readStories(o.getJsonArray("stories"));
    return new Project(name, stories);
  }

  private Goal readGoal(JsonObject o) {
    var id = o.getString("id");
    var title = o.getString("title");
    var activities = readStories(o.getJsonArray("activities"));
    return new Goal(id, title, activities);
  }

  private Activity readActivity(JsonObject o) {
    var id = o.getString("id");
    var title = o.getString("title");
    var userStories = readStories(o.getJsonArray("userStories"));
    return new Activity(id, title, userStories);
  }

  private List<Story> readStories(JsonArray a) {
    var stories = new ArrayList<Story>();
    for (var v : a) {
      var o = v.asJsonObject();
      var story =
          switch (o.getString("type")) {
            case "Goal" -> readGoal(o);
            case "Activity" -> readActivity(o);
            case "UserStory" -> readUserStory(o);
            case "Pain" -> readPain(o);
            default -> null;
          };
      if (story != null) {
        stories.add(story);
      }
    }
    return stories;
  }

  private UserStory readUserStory(JsonObject o) {
    var id = o.getString("id");
    var title = o.getString("title");
    var jsonState = o.getJsonString("state");
    var state = jsonState != null ? State.valueOf(jsonState.getString()) : State.EMPTY;
    return new UserStory(id, title, state);
  }

  private Pain readPain(JsonObject o) {
    var id = o.getString("id");
    var title = o.getString("title");
    var jsonState = o.getJsonString("state");
    var state = jsonState != null ? State.valueOf(jsonState.getString()) : State.EMPTY;
    return new Pain(id, title, state);
  }
}
