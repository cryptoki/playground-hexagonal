package de.javaclub.playground.hexagonal.ports.database

import de.javaclub.playground.hexagonal.ports.driven.database.DeletionProofEntityRepository
import de.javaclub.playground.hexagonal.ports.driven.database.model.DeletionProofEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DeletionProofEntityRepositoryIT {

    @Autowired
    lateinit var deletionProofEntityRepository: DeletionProofEntityRepository

    @Test
    fun `it should find a persisted entity`() {
        val persisted = aPersistedDeletionProofEntity()

        val result = deletionProofEntityRepository.findById(persisted.id!!)

        assertThat(result.isPresent).isTrue()
        assertThat(result.get().message).isEqualTo(MESSAGE)
    }

    private fun aPersistedDeletionProofEntity(): DeletionProofEntity =
            deletionProofEntityRepository.save(aDeletionProofEntity())
}