package de.javaclub.playground.hexagonal.usecases.create

import de.javaclub.playground.hexagonal.domain.DeletionProof
import de.javaclub.playground.hexagonal.ports.driven.database.DeletionProofEntityRepository
import de.javaclub.playground.hexagonal.ports.driven.database.model.toDeletionProof
import org.springframework.stereotype.Service

@Service
class CreateDeletionProofService(
        private val deletionProofEntityRepository: DeletionProofEntityRepository) {

    fun createDeletionProof(command: CreateDeletionProofCommand): DeletionProof =
            deletionProofEntityRepository.save(command.toDeletionProofEntity()).toDeletionProof()
}
