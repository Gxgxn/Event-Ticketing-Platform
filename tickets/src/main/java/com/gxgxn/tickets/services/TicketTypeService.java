package com.gxgxn.tickets.services;

import com.gxgxn.tickets.domain.entities.Ticket;
import com.gxgxn.tickets.domain.entities.TicketType;

import java.util.UUID;

public interface TicketTypeService {
    Ticket purchaseTicket(UUID userId, UUID ticketType);
}
