package de.javaclub.playground.hexagonal.ports.driver.rest

import de.javaclub.playground.hexagonal.domain.DeletionStatus
import de.javaclub.playground.hexagonal.usecases.create.CreateDeletionProofCommand
import de.javaclub.playground.hexagonal.usecases.create.CreateDeletionProofService
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.created
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
class CreateDeletionProofController(
        private val createDeletionProofService: CreateDeletionProofService
) {

    @PostMapping(value = ["/deletions"], consumes = [APPLICATION_JSON_VALUE])
    fun createDeletionProof(
            @Valid @RequestBody deletionProofRequest: CreateDeletionProofRequest,
            ucBuilder: UriComponentsBuilder): ResponseEntity<Nothing> {

        val deletionProof = createDeletionProofService.createDeletionProof(
                CreateDeletionProofCommand(
                        referenceId = deletionProofRequest.referenceId,
                        message = deletionProofRequest.message,
                        status = DeletionStatus.valueOf(deletionProofRequest.status)))
        return created(ucBuilder.path("/deletions/{id}").buildAndExpand(deletionProof.id.toString()).toUri()).build()
    }


    // -- examples for another way --

    @PostMapping(value = ["/deletions/successful"], consumes = [APPLICATION_JSON_VALUE])
    fun createDeletionProof(
            @Valid @RequestBody successful: CreateSuccessfulDeletionProofRequest,
            ucBuilder: UriComponentsBuilder): ResponseEntity<Nothing> {

        val deletionProof = createDeletionProofService.createDeletionProof(
                CreateDeletionProofCommand(
                        referenceId = successful.referenceId,
                        message = successful.message,
                        status = DeletionStatus.SUCCESSFUL))
        return created(ucBuilder.path("/deletions/{id}").buildAndExpand(deletionProof.id).toUri()).build()
    }

    @PostMapping(value = ["/deletions/failure"], consumes = [APPLICATION_JSON_VALUE])
    fun createDeletionProof(
            @Valid @RequestBody failure: CreateFailureDeletionProofRequest,
            ucBuilder: UriComponentsBuilder): ResponseEntity<Nothing> {

        val deletionProof = createDeletionProofService.createDeletionProof(
                CreateDeletionProofCommand(
                        referenceId = failure.referenceId,
                        message = failure.message,
                        status = DeletionStatus.FAILURE))
        return created(ucBuilder.path("/deletions/{id}").buildAndExpand(deletionProof.id).toUri()).build()
    }
}
