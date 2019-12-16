package de.javaclub.playground.hexagonal.ports.driven.database

import de.javaclub.playground.hexagonal.domain.DeletionStatus
import de.javaclub.playground.hexagonal.ports.driven.database.model.DeletionProofEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DeletionProofEntityRepositoryIT {

    @Autowired
    lateinit var deletionProofEntityRepository: DeletionProofEntityRepository

    @BeforeEach
    fun setup() {
        deletionProofEntityRepository.deleteAll()
    }

    @Test
    fun `it should find a persisted entity`() {
        val persisted = aPersistedDeletionProofEntity()

        val result = deletionProofEntityRepository.findById(persisted.id!!)

        assertThat(result.isPresent).isTrue()
        assertThat(result.get().message).isEqualTo(MESSAGE)
    }

    @Test
    fun `it should find a repaired deletion proof entity`() {
        aPersistedDeletionProofEntity(deletionStatus = DeletionStatus.FAILURE)
        aPersistedDeletionProofEntity(deletionStatus = DeletionStatus.SUCCESSFUL)
        aPersistedDeletionProofEntityWithReference("12345")

        val result = deletionProofEntityRepository.findRepaired()

        assertThat(result).hasSize(2)
    }

    @Test
    fun `it should find a successful deletion proof entity`() {
        aPersistedDeletionProofEntity(deletionStatus = DeletionStatus.FAILURE)
        aPersistedDeletionProofEntity(deletionStatus = DeletionStatus.SUCCESSFUL)
        val successful = aPersistedDeletionProofEntityWithReference("123456")

        val result = deletionProofEntityRepository.findSuccessful()

        assertThat(result)
                .hasSize(1)
                .hasOnlyOneElementSatisfying { deletionProofEntity ->
                    assertThat(deletionProofEntity).isEqualTo(successful)
                }
    }

    @Test
    fun `it should find a failure deletion proof entity`() {
        val failure = aPersistedDeletionProofEntity(deletionStatus = DeletionStatus.FAILURE)
        aPersistedDeletionProofEntityWithReference("123456")

        val result = deletionProofEntityRepository.findFailure()

        assertThat(result)
                .hasSize(1)
                .hasOnlyOneElementSatisfying { deletionProofEntity ->
                    assertThat(deletionProofEntity).isEqualTo(failure)
                }
    }

    private fun aPersistedDeletionProofEntity(): DeletionProofEntity =
            deletionProofEntityRepository.save(aDeletionProofEntity())

    private fun aPersistedDeletionProofEntity(deletionStatus: DeletionStatus): DeletionProofEntity =
            deletionProofEntityRepository.save(aDeletionProofEntity(deletionStatus))

    private fun aPersistedDeletionProofEntityWithReference(referenceId: String): DeletionProofEntity =
            deletionProofEntityRepository.save(aDeletionProofEntityWithReference(referenceId))
}