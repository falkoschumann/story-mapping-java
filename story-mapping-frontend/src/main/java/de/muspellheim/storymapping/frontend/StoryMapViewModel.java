/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import de.muspellheim.storymapping.contract.MessageHandling;
import de.muspellheim.storymapping.contract.data.Project;
import de.muspellheim.storymapping.contract.messages.commands.OpenFileCommand;
import de.muspellheim.storymapping.contract.messages.queries.GetBoardQuery;
import java.nio.file.Path;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class StoryMapViewModel {
  private final ReadOnlyObjectWrapper<Project> project = new ReadOnlyObjectWrapper<>();

  private final MessageHandling messageHandling;

  private Path projectFile;

  public StoryMapViewModel(MessageHandling messageHandling) {
    this.messageHandling = messageHandling;
  }

  public ReadOnlyObjectProperty<Project> projectProperty() {
    return project.getReadOnlyProperty();
  }

  public Project getBoard() {
    return project.get();
  }

  public void run() {
    var result = messageHandling.handle(new GetBoardQuery());
    project.set(result.board());
  }

  public void openFile(Path file) {
    projectFile = file;
    refresh();
  }

  public void refresh() {
    messageHandling.handle(new OpenFileCommand(projectFile));
    var result = messageHandling.handle(new GetBoardQuery());
    project.set(result.board());
  }
}
