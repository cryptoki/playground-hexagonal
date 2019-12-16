package de.javaclub.playground.hexagonal.ports.driven.database

import de.javaclub.playground.hexagonal.ports.driven.database.model.DeletionProofEntity
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent
import java.util.UUID.randomUUID


@Configuration
class DeletionProofJdbcConfiguration {

    @Bean
    fun idInitialization(): ApplicationListener<BeforeSaveEvent> {

        return ApplicationListener { event ->

            val entity = event.entity
            if (entity is DeletionProofEntity) {
                entity.id = randomUUID().toString()
            }
        }
    }
}