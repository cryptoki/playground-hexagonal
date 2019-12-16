package de.javaclub.playground.hexagonal.usecases.create

import de.javaclub.playground.hexagonal.ports.driver.rest.CreateDeletionProofController
import de.javaclub.playground.hexagonal.test.any
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.util.UriComponentsBuilder.fromUriString
import java.net.URI

@ExtendWith(MockitoExtension::class)
class CreateDeletionProofControllerTest {

    @Mock
    lateinit var deletionProofService: DeletionProofService

    @InjectMocks
    lateinit var createDeletionProofController: CreateDeletionProofController

    @Test
    fun `should respond with CREATED and location of new resource`() {
        serviceReturnsPersistedDeletionProof()

        val response = createDeletionProofController.createDeletionProof(
                aCreateDeletionProofRequest(),
                fromUriString("http://localhost")
        )

        assertThat(response.statusCode).isEqualTo(CREATED)
        assertThat(response.headers.location).isEqualTo(URI("http://localhost/deletions/$ID"))
    }

    @Test
    fun `should pass request as command to service`() {
        serviceReturnsPersistedDeletionProof()

        createDeletionProofController.createDeletionProof(
                aCreateDeletionProofRequest(),
                fromUriString("http://localhost")
        )

        verify(deletionProofService).createDeletionProof(aCreateDeletionProofCommand())
    }

    private fun serviceReturnsPersistedDeletionProof() {
        doReturn(aDeletionProof())
                .`when`(deletionProofService).createDeletionProof(any())
    }

}
