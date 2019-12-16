package de.javaclub.playground.hexagonal.ports.driver.rest

import javax.validation.constraints.NotBlank

data class CreateFailureDeletionProofRequest(
        @NotBlank
        val message: String
)