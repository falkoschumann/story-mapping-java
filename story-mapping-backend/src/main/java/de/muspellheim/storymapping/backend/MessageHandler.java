/*
 * Story Mapping - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.backend;

import de.muspellheim.storymapping.contract.MessageHandling;
import de.muspellheim.storymapping.contract.data.ActivityCard;
import de.muspellheim.storymapping.contract.data.Board;
import de.muspellheim.storymapping.contract.data.State;
import de.muspellheim.storymapping.contract.data.UserStoryCard;
import de.muspellheim.storymapping.contract.messages.queries.GetBoardQuery;
import de.muspellheim.storymapping.contract.messages.queries.GetBoardQueryResult;
import java.util.List;

public class MessageHandler implements MessageHandling {
  @Override
  public GetBoardQueryResult handle(GetBoardQuery query) {
    return new GetBoardQueryResult(
        new Board(
            List.of(
                new ActivityCard(
                    "1",
                    "Umreise die Projektziele",
                    List.of(
                        new UserStoryCard("1.1", "Schreibe Projektvision auf eine Karte"),
                        new UserStoryCard("1.2", "Positioniere die Karte oben links"))),
                new ActivityCard(
                    "2",
                    "Beschreibe das Gesamtbild",
                    List.of(
                        new UserStoryCard(
                            "2.1", "Schreibe jede Aktivität auf eine Karte", State.READY),
                        new UserStoryCard(
                            "2.2",
                            "Ordne die Karten oben in einer Zeile (Backbone)",
                            State.IN_PROGRESS),
                        new UserStoryCard("2.3", "Gruppiere Karten optional nach Ziel oder Nutzer"),
                        new UserStoryCard(
                            "2.4",
                            "Exportiere Bild mit Ziel/Nutzer als Zeile und Aktivitäten als Spalten"))),
                new ActivityCard(
                    "3",
                    "Entwickle Lösungen",
                    List.of(
                        new UserStoryCard(
                            "3.1", "Schreibe jede User Stories auf eine Karte", State.READY),
                        new UserStoryCard(
                            "3.2",
                            "Ordne die Karten in einer Spalte unter der jeweiligen Aktivität an (Body)",
                            State.IN_PROGRESS),
                        new UserStoryCard(
                            "3.3",
                            "Exportiere Bild mit Aktivitäten als Zeile und User Stories als Spalten"))),
                new ActivityCard(
                    "4",
                    "Schneide tragfähige Releases",
                    List.of(
                        new UserStoryCard(
                            "4.1", "Ordne die User Stories jeder Spalte nach ihrer Priorität"),
                        new UserStoryCard(
                            "4.2", "Lege für jedes Release eine Swimlane mit User Stories an"),
                        new UserStoryCard(
                            "4.3", "Schreibe den Outcome jedes Releases auf eine Karte"),
                        new UserStoryCard(
                            "4.4",
                            "Positioniere die Karte mit dem Outcome zu Beginn der jeweiligen Swimlane"),
                        new UserStoryCard(
                            "4.5",
                            "Exportiere Bild mit Aktivitäten als Zeile und User Stories als Spalten inklusive Swimlanes"))),
                new ActivityCard(
                    "5",
                    "Entwickle eine Strategie für Implementierung",
                    List.of(
                        new UserStoryCard(
                            "5.1", "Schreibe optional eine Liste von Tasks für eine User Story"),
                        new UserStoryCard(
                            "5.2",
                            "Kennzeichne den Zustand einer User Story (Ready, in Progress, Done)"),
                        new UserStoryCard(
                            "5.3",
                            "Exportiere Kanban-Board mit Zustand als Spalten mit Tasks und User Stories als Swimlanes"))))));
  }
}
