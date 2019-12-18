package de.javaclub.playground.hexagonal.ports.driven

import de.javaclub.playground.hexagonal.domain.DeletionProof
import de.javaclub.playground.hexagonal.usecases.create.CreateDeletionProofCommand

interface DeletionProofRepositoryPort {
    fun save(deletionProofCommand: CreateDeletionProofCommand): DeletionProof
}