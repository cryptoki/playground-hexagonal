package de.javaclub.playground.hexagonal.ports.database

import de.javaclub.playground.hexagonal.domain.DeletionStatus
import de.javaclub.playground.hexagonal.ports.driven.database.model.DeletionProofEntity

const val MESSAGE = "a message"
const val REFERENCE = "an reference"
val STATUS = DeletionStatus.SUCCESSFUL.name

fun aDeletionProofEntity(): DeletionProofEntity =
        DeletionProofEntity(referenceId = REFERENCE, message = MESSAGE, status = STATUS)
