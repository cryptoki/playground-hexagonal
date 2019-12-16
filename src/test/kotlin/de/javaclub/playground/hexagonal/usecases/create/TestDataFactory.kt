package de.javaclub.playground.hexagonal.usecases.create

import de.javaclub.playground.hexagonal.domain.DeletionProof
import de.javaclub.playground.hexagonal.domain.DeletionStatus
import de.javaclub.playground.hexagonal.ports.driven.database.model.DeletionProofEntity
import de.javaclub.playground.hexagonal.ports.driver.rest.CreateDeletionProofRequest
import java.util.*

val ID: UUID = UUID.randomUUID()
const val REFERENCE = "a reference"
const val MESSAGE = "a message"

fun aDeletionProof() =
        DeletionProof(
                id = ID,
                referenceId = REFERENCE,
                message = MESSAGE,
                status = DeletionStatus.SUCCESSFUL)

fun aDeletionProofEntity() =
        DeletionProofEntity(
                id= ID.toString(),
                referenceId = REFERENCE,
                message = MESSAGE,
                status = DeletionStatus.SUCCESSFUL.name)

fun aDeletionProofEntityWithoutId() =
        DeletionProofEntity(
                referenceId = REFERENCE,
                message = MESSAGE,
                status = DeletionStatus.SUCCESSFUL.name)

fun aCreateDeletionProofRequest() =
        CreateDeletionProofRequest(
                referenceId = REFERENCE,
                message = MESSAGE,
                status = DeletionStatus.SUCCESSFUL.name
        )

fun aCreateDeletionProofCommand() =
        CreateDeletionProofCommand(
                referenceId = REFERENCE,
                message = MESSAGE,
                status = DeletionStatus.SUCCESSFUL)
