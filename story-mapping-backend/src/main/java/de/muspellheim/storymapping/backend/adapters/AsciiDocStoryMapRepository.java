/*
 * Story Mapping - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.backend.adapters;

import de.muspellheim.storymapping.backend.StoryMapRepository;
import de.muspellheim.storymapping.contract.data.Project;
import java.nio.file.Path;
import lombok.SneakyThrows;

public class AsciiDocStoryMapRepository implements StoryMapRepository {
  private final Path file;

  public AsciiDocStoryMapRepository(Path file) {
    this.file = file;
  }

  @Override
  @SneakyThrows
  public Project loadProject() {
    return new AsciiDocStoryMapReader(file).read();
  }

  @Override
  @SneakyThrows
  public void storeProject(Project project) {
    new AsciiDocStoryMapWriter(file).write(project);
  }
}
