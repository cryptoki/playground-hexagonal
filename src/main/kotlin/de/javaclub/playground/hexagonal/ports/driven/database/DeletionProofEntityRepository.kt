package de.javaclub.playground.hexagonal.ports.driven.database

import de.javaclub.playground.hexagonal.ports.driven.database.model.DeletionProofEntity
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface DeletionProofEntityRepository : CrudRepository<DeletionProofEntity, String> {
    @Query("""
        SELECT * FROM DELETION_PROOF WHERE status = 'SUCCESSFUL' AND reference_id NOT IN (
	        SELECT reference_id FROM deletion_proof WHERE status = 'FAILURE')""")
    fun findSuccessful(): List<DeletionProofEntity>

    @Query("""
        SELECT * FROM DELETION_PROOF WHERE status = 'FAILURE' AND reference_id NOT IN (
	        SELECT reference_id FROM deletion_proof WHERE status = 'SUCCESSFUL')""")
    fun findFailure(): List<DeletionProofEntity>

    @Query("""
        SELECT * FROM DELETION_PROOF WHERE reference_id IN (
	        SELECT reference_id FROM deletion_proof WHERE status = 'SUCCESSFUL'
            INTERSECT
            SELECT reference_id FROM deletion_proof WHERE status = 'FAILURE')""")
    fun findRepaired(): List<DeletionProofEntity>

}