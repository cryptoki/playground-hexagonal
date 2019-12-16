package de.javaclub.playground.hexagonal.usecases.interpret

import de.javaclub.playground.hexagonal.domain.DeletionStatus
import de.javaclub.playground.hexagonal.ports.driven.database.DeletionProofEntityRepository
import de.javaclub.playground.hexagonal.usecases.aRandomDeletionProofEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class CreateStatisticServiceTest {

    @Mock
    lateinit var deletionProofEntityRepository: DeletionProofEntityRepository

    @InjectMocks
    lateinit var createStatisticService: CreateStatisticService

    @Test
    internal fun `should create statistic`() {
        repositoryPersistsEntities()

        val statistic = createStatisticService.interpretAndCreate()

        assertThat(statistic.successful)
                .isNotEmpty
                .hasSize(1)
        assertThat(statistic.failure)
                .isNotEmpty
                .hasSize(1)
        assertThat(statistic.repaired)
                .isNotEmpty
                .hasSize(2)

        Mockito.verify(deletionProofEntityRepository).findSuccessful()
        Mockito.verify(deletionProofEntityRepository).findFailure()
        Mockito.verify(deletionProofEntityRepository).findRepaired()
    }

    private fun repositoryPersistsEntities() {
        Mockito.doReturn(listOf(
                aRandomDeletionProofEntity(status = DeletionStatus.SUCCESSFUL.name)))
                .`when`(deletionProofEntityRepository).findSuccessful()

        Mockito.doReturn(listOf(
                aRandomDeletionProofEntity(status = DeletionStatus.FAILURE.name)))
                .`when`(deletionProofEntityRepository).findFailure()

        val failure = aRandomDeletionProofEntity(status = DeletionStatus.FAILURE.name)
        Mockito.doReturn(listOf(
                failure,
                aRandomDeletionProofEntity(referenceId = failure.referenceId, status = DeletionStatus.SUCCESSFUL.name)))
                .`when`(deletionProofEntityRepository).findRepaired()
    }
}