/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;

public class Card extends Control {
  private final StringProperty title = new SimpleStringProperty("");
  private final IntegerProperty column = new SimpleIntegerProperty();
  private final IntegerProperty row = new SimpleIntegerProperty();
  private final ObjectProperty<Color> Color = new SimpleObjectProperty<>();
  private final StringProperty stateName = new SimpleStringProperty("");
  private final ObjectProperty<Color> stateColor = new SimpleObjectProperty<>();

  public Card() {
    initialize();
  }

  private void initialize() {
    getStyleClass().setAll("card");
  }

  public final StringProperty titleProperty() {
    return title;
  }

  public final String getTitle() {
    return title.get();
  }

  public final void setTitle(String value) {
    this.title.set(value);
  }

  public IntegerProperty columnProperty() {
    return column;
  }

  public int getColumn() {
    return column.get();
  }

  public void setColumn(int value) {
    this.column.set(value);
  }

  public IntegerProperty rowProperty() {
    return row;
  }

  public int getRow() {
    return row.get();
  }

  public void setRow(int value) {
    this.row.set(value);
  }

  public final Color getColor() {
    return Color.get();
  }

  public final ObjectProperty<Color> colorProperty() {
    return Color;
  }

  public final void setColor(Color value) {
    this.Color.set(value);
  }

  public StringProperty stateNameProperty() {
    return stateName;
  }

  public String getStateName() {
    return stateName.get();
  }

  public void setStateName(String value) {
    this.stateName.set(value);
  }

  public final ObjectProperty<Color> stateColorProperty() {
    return stateColor;
  }

  public final Color getStateColor() {
    return stateColor.get();
  }

  public final void setStateColor(Color value) {
    this.stateColor.set(value);
  }

  @Override
  protected Skin<?> createDefaultSkin() {
    return new CardSkin(this);
  }

  @Override
  public String getUserAgentStylesheet() {
    return Objects.requireNonNull(Card.class.getResource("card.css")).toExternalForm();
  }
}
