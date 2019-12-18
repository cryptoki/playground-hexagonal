package de.javaclub.playground.hexagonal.usecases.create

import de.javaclub.playground.hexagonal.domain.DeletionProof
import de.javaclub.playground.hexagonal.ports.driven.DeletionProofRepositoryPort
import org.springframework.stereotype.Service

@Service
class CreateDeletionProofService(
        private val deletionProofRepositoryPort: DeletionProofRepositoryPort) {

    fun createDeletionProof(command: CreateDeletionProofCommand): DeletionProof =
            deletionProofRepositoryPort.save(command)
}
