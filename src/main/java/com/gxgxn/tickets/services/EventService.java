package com.gxgxn.tickets.services;

import com.gxgxn.tickets.domain.CreateEventRequest;
import com.gxgxn.tickets.domain.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerID, CreateEventRequest event);

    Page<Event> listEventsForOrganizer(UUID organizerId, Pageable pageable);
}
