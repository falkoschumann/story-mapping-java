/*
 * Story Mapping - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.backend.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.muspellheim.storymapping.contract.data.ActivityCard;
import de.muspellheim.storymapping.contract.data.Board;
import de.muspellheim.storymapping.contract.data.GoalCard;
import de.muspellheim.storymapping.contract.data.UserStoryCard;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.Test;

public class AsciiDocStoryMapRepositoryTest {
  public static final Path EXAMPLE_PATH = Paths.get("src/test/resources/example.adoc");

  @Test
  void load() {
    var file = EXAMPLE_PATH;
    var repository = new AsciiDocStoryMapRepository(file);

    var board = repository.loadBoard();

    assertEquals(createExampleBoard(), board);
  }

  @Test
  void store() throws IOException {
    var file = Paths.get("build/test/example.adoc");
    var repository = new AsciiDocStoryMapRepository(file);

    var board = createExampleBoard();
    repository.storeBoard(board);

    var expected = String.join("\n", Files.readAllLines(EXAMPLE_PATH));
    var actual = String.join("\n", Files.readAllLines(file));
    assertEquals(expected, actual);
  }

  private static Board createExampleBoard() {
    return new Board(
        "Foobar",
        List.of(
            new GoalCard(
                "G2",
                "Foo",
                List.of(
                    new ActivityCard(
                        "A3",
                        "Foo 1",
                        List.of(
                            new UserStoryCard("U6", "Lorem ipsum 1"),
                            new UserStoryCard("U5", "Lorem ipsum 2"))),
                    new ActivityCard(
                        "A2", "Foo 2", List.of(new UserStoryCard("U4", "Lorem ipsum 3"))))),
            new GoalCard(
                "G1",
                "Bar",
                List.of(
                    new ActivityCard(
                        "A1",
                        "Bar 1",
                        List.of(
                            new UserStoryCard("U3", "Lorem ipsum 4"),
                            new UserStoryCard("U2", "Lorem ipsum 5"),
                            new UserStoryCard("U1", "Lorem ipsum 6")))))));
  }
}
