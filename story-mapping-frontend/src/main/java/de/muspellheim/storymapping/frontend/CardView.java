/*
 * Story Mapping - Frontend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.frontend;

import de.muspellheim.storymapping.contract.data.Card;
import javafx.scene.control.Label;

public class CardView extends Label {

  public CardView(Card card) {
    setText(card.title());
  }
}
