package com.gxgxn.tickets.controllers;
import static  com.gxgxn.tickets.util.JwtUtil.parseUserId;
import com.gxgxn.tickets.domain.CreateEventRequest;
import com.gxgxn.tickets.domain.UpdateEventRequest;
import com.gxgxn.tickets.domain.dtos.*;
import com.gxgxn.tickets.domain.entities.Event;
import com.gxgxn.tickets.mappers.EventMapper;
import com.gxgxn.tickets.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path= "/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    @PostMapping
    public ResponseEntity<CreateEventResponseDto> createEvent(CreateEventRequestDto dto,
                                                             @AuthenticationPrincipal Jwt jwt,
                                                             @RequestBody CreateEventRequestDto createEventRequestDto) {
        CreateEventRequest createEventRequest =  eventMapper.fromDto(createEventRequestDto);
        UUID  userId = UUID.fromString(jwt.getSubject());

        Event createdEvent =  eventService.createEvent(userId, createEventRequest);

        CreateEventResponseDto createEventResponseDto = eventMapper.toDto(createdEvent);

        return new ResponseEntity<>(createEventResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<Page<ListEventResponseDto>> listEvents(
            @AuthenticationPrincipal Jwt jwt, Pageable pageable
    ) {
        UUID userId = parseUserId(jwt);
        Page<Event> events = eventService.listEventsForOrganizer(userId, pageable);
        return ResponseEntity.ok(
                events.map(eventMapper::toListEventResponseDto)
        );
    }

    @GetMapping(path = "/{eventId}")
    public ResponseEntity<GetEventDetailsResponseDto> getEvent(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID eventId
    ) {
        // Get the user's ID from the JWT token
        UUID userId = parseUserId(jwt);

        // Call the service layer and transform the response
        return eventService.getEventForOrganizer(userId, eventId)
                .map(eventMapper::toGetEventDetailsResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{eventId}")
    public ResponseEntity<UpdateEventResponseDto> updateEvent(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID eventId,
            @Valid @RequestBody UpdateEventRequestDto updateEventRequestDto){
        UpdateEventRequest updateEventRequest = eventMapper.fromDto(updateEventRequestDto);
        UUID userId = parseUserId(jwt);

        Event updatedEvent = eventService.updateEventForOrganizer(
                userId, eventId, updateEventRequest
        );

        UpdateEventResponseDto updateEventResponseDto = eventMapper.toUpdateEventResponseDto(updatedEvent);

        return ResponseEntity.ok(updateEventResponseDto);
    }
}
