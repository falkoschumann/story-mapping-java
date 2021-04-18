/*
 * Story Mapping - Backend
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.backend;

import de.muspellheim.storymapping.contract.MessageHandling;
import de.muspellheim.storymapping.contract.data.Activity;
import de.muspellheim.storymapping.contract.data.Goal;
import de.muspellheim.storymapping.contract.data.Project;
import de.muspellheim.storymapping.contract.data.State;
import de.muspellheim.storymapping.contract.data.UserStory;
import de.muspellheim.storymapping.contract.messages.queries.GetBoardQuery;
import de.muspellheim.storymapping.contract.messages.queries.GetBoardQueryResult;
import java.util.List;

public class MessageHandler implements MessageHandling {
  @Override
  public GetBoardQueryResult handle(GetBoardQuery query) {
    return new GetBoardQueryResult(
        new Project(
            "Story Mappung",
            List.of(
                new Goal(
                    "1",
                    "Story Mapping",
                    List.of(
                        new Activity(
                            "1.1",
                            "Umreise die Projektziele",
                            List.of(
                                new UserStory("1.1.1", "Schreibe Projektvision auf eine Karte"),
                                new UserStory("1.1.2", "Positioniere die Karte oben links"))),
                        new Activity(
                            "1.2",
                            "Beschreibe das Gesamtbild",
                            List.of(
                                new UserStory(
                                    "1.2.1", "Schreibe jede Aktivität auf eine Karte", State.READY),
                                new UserStory(
                                    "1.2.2",
                                    "Ordne die Karten oben in einer Zeile (Backbone)",
                                    State.IN_PROGRESS),
                                new UserStory(
                                    "1.2.3",
                                    "Gruppiere Karten optional nach Ziel oder Nutzer",
                                    State.IN_PROGRESS),
                                new UserStory(
                                    "1.2.4",
                                    "Exportiere Bild mit Ziel/Nutzer als Zeile und Aktivitäten als Spalten"))),
                        new Activity(
                            "1.3",
                            "Entwickle Lösungen",
                            List.of(
                                new UserStory(
                                    "1.3.1",
                                    "Schreibe jede User Stories auf eine Karte",
                                    State.READY),
                                new UserStory(
                                    "1.3.2",
                                    "Ordne die Karten in einer Spalte unter der jeweiligen Aktivität an (Body)",
                                    State.IN_PROGRESS),
                                new UserStory(
                                    "1.3.3",
                                    "Exportiere Bild mit Aktivitäten als Zeile und User Stories als Spalten"))))),
                new Goal(
                    "2",
                    "Kanban",
                    List.of(
                        new Activity(
                            "2.1",
                            "Schneide tragfähige Releases",
                            List.of(
                                new UserStory(
                                    "2.1.1",
                                    "Ordne die User Stories jeder Spalte nach ihrer Priorität"),
                                new UserStory(
                                    "2.1.2",
                                    "Lege für jedes Release eine Swimlane mit User Stories an"),
                                new UserStory(
                                    "2.1.3", "Schreibe den Outcome jedes Releases auf eine Karte"),
                                new UserStory(
                                    "2.1.4",
                                    "Positioniere die Karte mit dem Outcome zu Beginn der jeweiligen Swimlane"),
                                new UserStory(
                                    "2.1.5",
                                    "Exportiere Bild mit Aktivitäten als Zeile und User Stories als Spalten inklusive Swimlanes"))),
                        new Activity(
                            "2.2",
                            "Entwickle eine Strategie für Implementierung",
                            List.of(
                                new UserStory(
                                    "2.2.1",
                                    "Schreibe optional eine Liste von Tasks für eine User Story"),
                                new UserStory(
                                    "2.2.2",
                                    "Kennzeichne den Zustand einer User Story (Ready, in Progress, Done)"),
                                new UserStory(
                                    "2.2.3",
                                    "Exportiere Kanban-Board mit Zustand als Spalten mit Tasks und User Stories als Swimlanes"))))))));
  }
}
