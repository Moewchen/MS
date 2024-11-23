package com.bht.MediTrack;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class PublisherEvent {

    private final ApplicationEventPublisher applicationEventPublisher;

    public PublisherEvent(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(Object event) {
        applicationEventPublisher.publishEvent(event);
    }
}
