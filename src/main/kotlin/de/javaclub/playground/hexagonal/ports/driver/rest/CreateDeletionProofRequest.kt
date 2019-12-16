package de.javaclub.playground.hexagonal.ports.driver.rest

import javax.validation.constraints.NotBlank

data class CreateDeletionProofRequest(
        @NotBlank
        val message: String,

        @NotBlank
        val status: String
)