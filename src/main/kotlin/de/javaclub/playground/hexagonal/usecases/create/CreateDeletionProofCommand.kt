package de.javaclub.playground.hexagonal.usecases.create

import de.javaclub.playground.hexagonal.domain.DeletionStatus
import de.javaclub.playground.hexagonal.ports.driven.database.model.DeletionProofEntity

data class CreateDeletionProofCommand(
        val referenceId: String,
        val message: String,
        val status: DeletionStatus)

fun CreateDeletionProofCommand.toDeletionProofEntity() =
        DeletionProofEntity(
                message = this.message,
                referenceId = this.referenceId,
                status = status.name
        )