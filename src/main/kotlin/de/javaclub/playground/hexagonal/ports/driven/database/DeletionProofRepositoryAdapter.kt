package de.javaclub.playground.hexagonal.ports.driven.database

import de.javaclub.playground.hexagonal.domain.DeletionProof
import de.javaclub.playground.hexagonal.ports.driven.DeletionProofRepositoryPort
import de.javaclub.playground.hexagonal.ports.driven.database.model.toDeletionProof
import de.javaclub.playground.hexagonal.usecases.create.CreateDeletionProofCommand
import de.javaclub.playground.hexagonal.usecases.create.toDeletionProofEntity
import org.springframework.stereotype.Repository

@Repository
class DeletionProofRepositoryAdapter(
        private val deletionProofEntityRepository: DeletionProofEntityRepository) : DeletionProofRepositoryPort {

    override fun save(deletionProofCommand: CreateDeletionProofCommand): DeletionProof {
        return deletionProofEntityRepository
                .save(deletionProofCommand.toDeletionProofEntity())
                .toDeletionProof()
    }
}