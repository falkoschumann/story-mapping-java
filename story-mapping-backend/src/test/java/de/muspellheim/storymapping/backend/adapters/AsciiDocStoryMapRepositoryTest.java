/*
 * Story Mapping - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.backend.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.muspellheim.storymapping.contract.data.Activity;
import de.muspellheim.storymapping.contract.data.Goal;
import de.muspellheim.storymapping.contract.data.Pain;
import de.muspellheim.storymapping.contract.data.Project;
import de.muspellheim.storymapping.contract.data.State;
import de.muspellheim.storymapping.contract.data.UserStory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

public class AsciiDocStoryMapRepositoryTest {
  public static final Path EXAMPLE_PATH = Paths.get("src/test/resources/example.adoc");

  @Test
  void teamMember() {
    var matcher = Pattern.compile("^\\((.+)\\) .*").matcher("(FS) Lorem ipsum");
    assertTrue(matcher.matches());
  }

  @Test
  void load() {
    var repository = new AsciiDocStoryMapRepository(EXAMPLE_PATH);

    var board = repository.loadProject();

    assertEquals(createExampleBoard(), board);
  }

  @Test
  void store() throws IOException {
    var file = Paths.get("build/test/example.adoc");
    var repository = new AsciiDocStoryMapRepository(file);

    var board = createExampleBoard();
    repository.storeProject(board);

    var expected = String.join("\n", Files.readAllLines(EXAMPLE_PATH));
    var actual = String.join("\n", Files.readAllLines(file));
    assertEquals(expected, actual);
  }

  private static Project createExampleBoard() {
    return new Project(
        "Foobar",
        List.of(
            new Goal(
                "G2",
                "Foo",
                List.of(
                    new Activity(
                        "A3",
                        "Foo 1",
                        List.of(
                            new UserStory("U7", "Lorem ipsum 1", State.TODO),
                            new UserStory("U6", "Lorem ipsum 2", State.DONE),
                            new UserStory("U5", "Lorem ipsum 3", State.IN_PROGRESS, "FS"),
                            new UserStory("U4", "Lorem ipsum 4", State.DONE, "FS"),
                            new Pain("P5", "Lorem ipsum 5", State.TODO),
                            new Pain("P4", "Lorem ipsum 6", State.DONE),
                            new Pain("P3", "Lorem ipsum 7", State.IN_PROGRESS, "FS"),
                            new Pain("P2", "Lorem ipsum 8", State.DONE, "FS"))),
                    new Activity("A2", "Foo 2", List.of(new UserStory("U3", "Lorem ipsum"))))),
            new Goal(
                "G1",
                "Bar",
                List.of(
                    new Activity(
                        "A1",
                        "Bar 1",
                        List.of(
                            new UserStory("U2", "Lorem ipsum A"),
                            new UserStory("U1", "Lorem ipsum B", State.CONSTRAINT),
                            new Pain("P1", "Lorem ipsum C")))))));
  }
}
