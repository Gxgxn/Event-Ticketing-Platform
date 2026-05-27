package com.gxgxn.tickets.controllers;

import com.gxgxn.tickets.domain.CreateEventRequest;
import com.gxgxn.tickets.domain.dtos.CreateEventRequestDto;
import com.gxgxn.tickets.domain.dtos.CreateEventResponseDto;
import com.gxgxn.tickets.domain.dtos.ListEventResponseDto;
import com.gxgxn.tickets.domain.entities.Event;
import com.gxgxn.tickets.mappers.EventMapper;
import com.gxgxn.tickets.services.EventService;
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
    public ResponseEntity<ListEventResponseDto> listEvents(
            @AuthenticationPrincipal Jwt jwt, Pageable pageable
    ) {
       eventService.listEventsForOrganizer();
    }
}
