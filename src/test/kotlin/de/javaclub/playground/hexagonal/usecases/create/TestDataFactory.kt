package de.javaclub.playground.hexagonal.usecases.create

import de.javaclub.playground.hexagonal.domain.DeletionProof
import de.javaclub.playground.hexagonal.domain.DeletionStatus
import de.javaclub.playground.hexagonal.ports.driven.database.model.DeletionProofEntity
import de.javaclub.playground.hexagonal.ports.driver.rest.CreateDeletionProofRequest
import java.util.*

val ID: UUID = UUID.randomUUID()
const val MESSAGE = "a message"

fun aDeletionProof() =
        DeletionProof(
                ID,
                MESSAGE,
                DeletionStatus.SUCCESSFUL)

fun aDeletionProofEntity() =
        DeletionProofEntity(
                ID.toString(),
                MESSAGE,
                DeletionStatus.SUCCESSFUL.name)

fun aDeletionProofEntityWithoutId() =
        DeletionProofEntity(
                null,
                MESSAGE,
                DeletionStatus.SUCCESSFUL.name)

fun aCreateDeletionProofRequest() =
        CreateDeletionProofRequest(
                MESSAGE,
                DeletionStatus.SUCCESSFUL.name
        )

fun aCreateDeletionProofCommand() = CreateDeletionProofCommand(MESSAGE, DeletionStatus.SUCCESSFUL)
