/*
 * Story Mapping - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.backend;

import de.muspellheim.storymapping.backend.adapters.AsciiDocStoryMapRepository;
import de.muspellheim.storymapping.backend.adapters.JsonStoryMapRepository;
import de.muspellheim.storymapping.contract.MessageHandling;
import de.muspellheim.storymapping.contract.data.Project;
import de.muspellheim.storymapping.contract.messages.commands.OpenFileCommand;
import de.muspellheim.storymapping.contract.messages.queries.GetBoardQuery;
import de.muspellheim.storymapping.contract.messages.queries.GetBoardQueryResult;

public class MessageHandler implements MessageHandling {
  private Project project;

  @Override
  public void handle(OpenFileCommand command) {
    var file = command.file();
    StoryMapRepository repository;
    if (file.toString().endsWith(".adoc")) {
      repository = new AsciiDocStoryMapRepository(file);
    } else if (file.toString().endsWith(".json")) {
      repository = new JsonStoryMapRepository(file);
    } else {
      throw new IllegalStateException("Unknown file extension: " + file);
    }
    project = repository.loadProject();
  }

  @Override
  public GetBoardQueryResult handle(GetBoardQuery query) {
    return new GetBoardQueryResult(project);
  }
}
