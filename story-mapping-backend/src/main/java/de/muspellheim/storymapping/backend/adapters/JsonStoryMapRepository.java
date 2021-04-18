/*
 * Story Mapping - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.backend.adapters;

import de.muspellheim.storymapping.backend.StoryMapRepository;
import de.muspellheim.storymapping.contract.data.Project;
import java.nio.file.Path;
import lombok.SneakyThrows;

public class JsonStoryMapRepository implements StoryMapRepository {
  private final Path file;

  public JsonStoryMapRepository(Path file) {
    this.file = file;
  }

  @Override
  @SneakyThrows
  public Project loadBoard() {
    return new JsonStoryMapReader(file).read();
  }

  @Override
  @SneakyThrows
  public void storeBoard(Project project) {
    new JsonStoryMapWriter(file).write(project);
  }
}
