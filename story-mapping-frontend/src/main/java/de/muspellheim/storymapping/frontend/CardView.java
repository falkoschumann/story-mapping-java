/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;

public class CardView extends Control {

  /***************************************************************************
   *                                                                         *
   * Constructors                                                            *
   *                                                                         *
   **************************************************************************/

  public CardView() {
    initialize();
  }

  private void initialize() {
    getStyleClass().setAll("card-view");
  }

  /***************************************************************************
   *                                                                         *
   * Properties                                                              *
   *                                                                         *
   **************************************************************************/

  private final StringProperty title = new SimpleStringProperty(this, "title");

  public final StringProperty titleProperty() {
    return title;
  }

  public final String getTitle() {
    return title.get();
  }

  public final void setTitle(String title) {
    this.title.set(title);
  }

  private final ObjectProperty<Color> color = new SimpleObjectProperty<>(this, "color");

  public final Color getColor() {
    return color.get();
  }

  public final ObjectProperty<Color> colorProperty() {
    return color;
  }

  public final void setColor(Color color) {
    this.color.set(color);
  }

  /***************************************************************************
   *                                                                         *
   * Methods                                                                 *
   *                                                                         *
   **************************************************************************/

  @Override
  protected Skin<?> createDefaultSkin() {
    return new CardViewSkin(this);
  }

  @Override
  public String getUserAgentStylesheet() {
    return Objects.requireNonNull(CardView.class.getResource("cardview.css")).toExternalForm();
  }
}
