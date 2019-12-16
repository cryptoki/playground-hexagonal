package de.javaclub.playground.hexagonal.configuration

import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.relational.core.mapping.event.RelationalEvent

@Configuration
class DataJdbcConfiguration {

    @Bean
    fun loggingListener(): ApplicationListener<*> {

        return ApplicationListener<ApplicationEvent> { event->
            if (event is RelationalEvent) {
                println("Received an event: $event")
            }
        }
    }
}
