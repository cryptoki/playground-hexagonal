package de.javaclub.playground.hexagonal.ports.database

import de.javaclub.playground.hexagonal.domain.DeletionStatus
import de.javaclub.playground.hexagonal.ports.driven.database.model.DeletionProofEntity

const val MESSAGE = "a message"
val STATUS = DeletionStatus.SUCCESSFUL.name

fun aDeletionProofEntity(): DeletionProofEntity =
        DeletionProofEntity(id= null, message = MESSAGE, status = STATUS)
