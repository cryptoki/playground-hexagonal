package de.javaclub.playground.hexagonal.ports.driven.database

import de.javaclub.playground.hexagonal.ports.driven.database.model.DeletionProofEntity
import org.springframework.data.repository.CrudRepository

interface DeletionProofEntityRepository : CrudRepository<DeletionProofEntity, String>