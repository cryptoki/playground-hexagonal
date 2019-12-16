package de.javaclub.playground.hexagonal.usecases.create

import de.javaclub.playground.hexagonal.ports.driven.database.DeletionProofEntityRepository
import de.javaclub.playground.hexagonal.test.any
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class CreateDeletionProofServiceTest {

    @Mock
    lateinit var deletionProofEntityRepository: DeletionProofEntityRepository

    @InjectMocks
    lateinit var deletionProofService: DeletionProofService

    @Test
    internal fun `should pass converted entity to repository`() {

        repositoryPersistsEntity()

        deletionProofService.createDeletionProof(aCreateDeletionProofCommand())

        verify(deletionProofEntityRepository).save(aDeletionProofEntityWithoutId())
    }

    @Test
    internal fun `should return converted entity`() {

        repositoryPersistsEntity()

        val deletionProof = deletionProofService.createDeletionProof(aCreateDeletionProofCommand())

        assertThat(deletionProof).isEqualTo(aDeletionProof())
    }

    private fun repositoryPersistsEntity() {
        doReturn(aDeletionProofEntity())
                .`when`(deletionProofEntityRepository).save(any())
    }

}
