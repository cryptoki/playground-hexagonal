package de.javaclub.playground.hexagonal.ports.driven.database.model

import de.javaclub.playground.hexagonal.domain.DeletionProof
import de.javaclub.playground.hexagonal.domain.DeletionStatus
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Table("DELETION_PROOF")
data class DeletionProofEntity(
        @Id
        var id: String?,

        @NotBlank
        @Size(max = 255)
        val message: String,

        @NotBlank
        @Size(max = 255)
        val status: String) {
}

fun DeletionProofEntity.toDeletionProof() =
        DeletionProof(
                UUID.fromString(this.id),
                this.message,
                DeletionStatus.valueOf(status)
        )

