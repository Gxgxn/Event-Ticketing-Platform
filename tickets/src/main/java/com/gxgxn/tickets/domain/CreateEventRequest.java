package com.gxgxn.tickets.domain;


import com.gxgxn.tickets.domain.entities.EventStatusEnum;
import com.gxgxn.tickets.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequest {
    private String name;
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime salesStart;
    private LocalDateTime salesEnd;
    private EventStatusEnum status;
    private String venue;
    private List<CreateTicketTypeRequest> ticketTypes = new ArrayList<>();
}
