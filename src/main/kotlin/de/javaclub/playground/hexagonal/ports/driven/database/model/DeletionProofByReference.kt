package de.javaclub.playground.hexagonal.ports.driven.database.model

data class DeletionProofByReference(
        val referenceId: String,
        val deletionProofEntity: DeletionProofEntity)