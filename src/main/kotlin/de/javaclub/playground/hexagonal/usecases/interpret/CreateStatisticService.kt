package de.javaclub.playground.hexagonal.usecases.interpret

import de.javaclub.playground.hexagonal.domain.DeletionStatistic
import de.javaclub.playground.hexagonal.ports.driven.database.DeletionProofEntityRepository
import de.javaclub.playground.hexagonal.ports.driven.database.model.toDeletionProof
import org.springframework.stereotype.Service

@Service
class CreateStatisticService(
        private val deletionProofEntityRepository: DeletionProofEntityRepository) {

    fun interpretAndCreate(): DeletionStatistic {
        val successful = deletionProofEntityRepository
                .findSuccessful()
                .map { deletionProofEntity -> deletionProofEntity.toDeletionProof() }
        val failure = deletionProofEntityRepository
                .findFailure()
                .map { deletionProofEntity -> deletionProofEntity.toDeletionProof() }
        val repaired = deletionProofEntityRepository
                .findRepaired()
                .map { deletionProofEntity -> deletionProofEntity.toDeletionProof() }
        return DeletionStatistic(
                successful = successful,
                failure = failure,
                repaired = repaired);
    }
}