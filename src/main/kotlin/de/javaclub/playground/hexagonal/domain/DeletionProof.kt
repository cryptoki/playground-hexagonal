package de.javaclub.playground.hexagonal.domain

import java.util.*

data class DeletionProof(
        val id: UUID,
        val referenceId: String,
        val message: String,
        val status: DeletionStatus) {
    init {
        require(message.isNotBlank()) { "message must be not blank"}
        require(referenceId.isNotBlank()) {"need a valid referenceId"}
    }
}

