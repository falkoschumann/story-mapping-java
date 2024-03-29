/*
 * Story Mapping - Contract
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.contract.messages.queries;

import de.muspellheim.storymapping.contract.data.Project;

public record GetBoardQueryResult(Project board) {}
