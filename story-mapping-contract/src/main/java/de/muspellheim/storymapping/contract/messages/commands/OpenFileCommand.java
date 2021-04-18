/*
 * Story Mapping - Contract
 * Copyright (c) 2021 Falko Schumann <falko.schumann@muspellheim.de>
 */

package de.muspellheim.storymapping.contract.messages.commands;

import java.nio.file.Path;

public record OpenFileCommand(Path file) {}
