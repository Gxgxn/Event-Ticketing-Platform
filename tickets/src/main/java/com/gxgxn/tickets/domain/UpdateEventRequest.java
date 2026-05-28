package com.gxgxn.tickets.domain;

import com.gxgxn.tickets.domain.entities.EventStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventRequest {
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime salesStart;
    private LocalDateTime salesEnd;
    private EventStatusEnum status;
    private String venue;
    private List<UpdateTicketTypeRequest> ticketTypes = new ArrayList<>();
}
