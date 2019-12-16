package de.javaclub.playground.hexagonal.ports.driver.rest

import javax.validation.constraints.NotBlank

data class CreateSuccessfulDeletionProofRequest(
        @NotBlank
        val message: String,
        @NotBlank
        val referenceId: String
)