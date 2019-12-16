package de.javaclub.playground.hexagonal.usecases.create

import de.javaclub.playground.hexagonal.domain.DeletionStatus
import de.javaclub.playground.hexagonal.ports.driven.database.model.DeletionProofEntity

data class CreateDeletionProofCommand(
        val message: String,
        val status: DeletionStatus)

fun CreateDeletionProofCommand.toDeletionProofEntity() =
        DeletionProofEntity(
                null,
                this.message,
                status.name
        )