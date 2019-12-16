package de.javaclub.playground.hexagonal.domain

import java.util.*

data class DeletionProof(
        val id: UUID,
        val message: String,
        val status: DeletionStatus) {
    init {
        require(message.isNotBlank()) { "message must be not blank"}
    }
}

