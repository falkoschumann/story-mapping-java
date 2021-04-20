/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import de.muspellheim.storymapping.contract.MessageHandling;
import de.muspellheim.storymapping.contract.data.Activity;
import de.muspellheim.storymapping.contract.data.Goal;
import de.muspellheim.storymapping.contract.data.Story;
import de.muspellheim.storymapping.contract.data.UserStory;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;

public class StoryMapView extends VBox {
  private final Label title;
  private final Button exportImageButton;
  private final Board board;

  private final StoryMapViewModel viewModel;

  public StoryMapView(MessageHandling messageHandling) {
    title = new Label("Story Mapping");
    title.setFont(new Font("Verdana", 24));

    var iconUrl =
        Objects.requireNonNull(StoryMapView.class.getResource("icons/folder-open.png"))
            .toExternalForm();
    var openButton = new Button("", new ImageView(iconUrl));

    openButton.setOnAction(e -> handleOpen());

    iconUrl =
        Objects.requireNonNull(StoryMapView.class.getResource("icons/image.png")).toExternalForm();
    exportImageButton = new Button("", new ImageView(iconUrl));
    exportImageButton.setDisable(true);
    exportImageButton.setOnAction(e -> handleExportImage());

    var toolBar = new ToolBar(title, Spacer.newHSpacer(), openButton, exportImageButton);
    getChildren().add(toolBar);

    board = new Board();
    var scrolledBoard = new ScrollPane(board);
    VBox.setVgrow(scrolledBoard, Priority.ALWAYS);
    getChildren().add(scrolledBoard);

    viewModel = new StoryMapViewModel(messageHandling);
    viewModel.projectProperty().addListener(o -> updateBoard());
  }

  private void handleOpen() {
    var chooser = new FileChooser();
    chooser.setTitle("Open Story Map");
    chooser
        .getExtensionFilters()
        .add(new ExtensionFilter("Project (*.adoc, *.json)", "*.adoc", "*.json"));
    var file = chooser.showOpenDialog(getScene().getWindow());
    if (file == null) {
      return;
    }

    viewModel.openFile(file.toPath());
  }

  private void handleExportImage() {
    var chooser = new FileChooser();
    chooser.setTitle("Export Story Map as Image");
    chooser.getExtensionFilters().add(new ExtensionFilter("Image (*.png)", "*.png"));
    var file = chooser.showSaveDialog(getScene().getWindow());
    if (file == null) {
      return;
    }

    WritableImage image = board.snapshot(new SnapshotParameters(), null);
    try {
      ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void updateBoard() {
    exportImageButton.setDisable(false);
    var project = viewModel.getBoard();
    title.setText(project.title());
    createAndPlaceCard(project.stories(), 0, 0);
  }

  private void createAndPlaceCard(List<? extends Story> stories, int column, int row) {
    for (Story story : stories) {
      var card = new Card();
      card.setTitle(story.title());
      card.setColumn(column);
      card.setRow(row);
      board.getCards().add(card);
      if (story instanceof Goal goalCard) {
        card.setColor(Color.LIGHTSKYBLUE);
        createAndPlaceCard(goalCard.activities(), column, row + 1);
        column += goalCard.activities().size();
      }
      if (story instanceof Activity activityCard) {
        card.setColor(Color.LIGHTGREEN);
        createAndPlaceCard(activityCard.userStories(), column, row + 1);
        column++;
      }
      if (story instanceof UserStory userStoryCard) {
        card.setColor(Color.LIGHTGOLDENRODYELLOW);
        card.setState(userStoryCard.state());
        row++;
      }
    }
  }

  public void run() {
    viewModel.run();
  }
}