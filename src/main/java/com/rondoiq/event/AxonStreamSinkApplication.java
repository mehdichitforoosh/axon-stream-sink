package com.rondoiq.event;

import com.rondoiq.event.outbound.AxonOutboundChannelAdapter;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@SpringBootApplication
@EnableBinding(Processor.class)
public class AxonStreamSinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(AxonStreamSinkApplication.class, args);
    }

    @Bean
    public EmbeddedEventStore eventStore(EventStorageEngine storageEngine, AxonConfiguration configuration) {
        return EmbeddedEventStore.builder()
                .storageEngine(storageEngine)
                .messageMonitor(configuration.messageMonitor(EventStore.class, "eventStore"))
                .build();
    }

    @Bean
    public EventStorageEngine storageEngine() {
        return new InMemoryEventStorageEngine();
    }


    @Bean
    public IntegrationFlow flow(EventBus eventBus) {
        return IntegrationFlows.from(Processor.INPUT)
                .handle(new AxonOutboundChannelAdapter(eventBus))
                .get();
    }

    @Autowired
    public void configure(EventProcessingConfigurer config) {
        config.usingSubscribingEventProcessors();
    }


}
