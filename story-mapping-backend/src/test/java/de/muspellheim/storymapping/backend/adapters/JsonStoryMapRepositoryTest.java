/*
 * Story Mapping - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.backend.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.muspellheim.storymapping.contract.data.Activity;
import de.muspellheim.storymapping.contract.data.Goal;
import de.muspellheim.storymapping.contract.data.Project;
import de.muspellheim.storymapping.contract.data.UserStory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.Test;

public class JsonStoryMapRepositoryTest {
  public static final Path EXAMPLE_PATH = Paths.get("src/test/resources/example.json");

  @Test
  void load() {
    var repository = new JsonStoryMapRepository(EXAMPLE_PATH);

    var board = repository.loadBoard();

    assertEquals(createExampleBoard(), board);
  }

  @Test
  void store() throws IOException {
    var file = Paths.get("build/test/example.json");
    var repository = new JsonStoryMapRepository(file);

    var board = createExampleBoard();
    repository.storeBoard(board);

    var expected = String.join("\n", Files.readAllLines(EXAMPLE_PATH));
    var actual = String.join("\n", Files.readAllLines(file));
    assertEquals(expected, actual);
  }

  private static Project createExampleBoard() {
    return new Project(
        "Foobar",
        List.of(
            new Goal(
                "G1",
                "Foo",
                List.of(
                    new Activity(
                        "A1",
                        "Foo 1",
                        List.of(
                            new UserStory("U1", "Lorem ipsum 1"),
                            new UserStory("U2", "Lorem ipsum 2"))),
                    new Activity("A2", "Foo 2", List.of(new UserStory("U3", "Lorem ipsum 3"))))),
            new Goal(
                "G2",
                "Bar",
                List.of(
                    new Activity(
                        "A3",
                        "Bar 1",
                        List.of(
                            new UserStory("U4", "Lorem ipsum 4"),
                            new UserStory("U5", "Lorem ipsum 5"),
                            new UserStory("U6", "Lorem ipsum 6")))))));
  }
}
