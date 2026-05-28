package com.gxgxn.tickets.services.impl;

import com.gxgxn.tickets.domain.entities.Ticket;
import com.gxgxn.tickets.domain.entities.TicketStatusEnum;
import com.gxgxn.tickets.domain.entities.TicketType;
import com.gxgxn.tickets.domain.entities.User;
import com.gxgxn.tickets.exceptions.TicketTypeNotFoundException;
import com.gxgxn.tickets.exceptions.TicketsSoldOutException;
import com.gxgxn.tickets.exceptions.UserNotFoundException;
import com.gxgxn.tickets.repositories.TicketRepository;
import com.gxgxn.tickets.repositories.TicketTypeRepository;
import com.gxgxn.tickets.repositories.UserRepository;
import com.gxgxn.tickets.services.QrCodeService;
import com.gxgxn.tickets.services.TicketTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketTypeServiceImpl implements TicketTypeService {
    private final UserRepository userRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final TicketRepository ticketRepository;
    private final QrCodeService qrCodeService;

    @Override
    @Transactional
    public Ticket purchaseTicket(UUID userId, UUID ticketTypeId) {
        // Look up the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with ID %s was not found", userId)
                ));

        // Get ticket type with pessimistic lock
        TicketType ticketType = ticketTypeRepository.findByIdWithLock(ticketTypeId)
                .orElseThrow(() -> new TicketTypeNotFoundException(
                        String.format("Ticket type with ID %s was not found", ticketTypeId)
                ));

        // Check ticket availability
        int purchasedTickets = ticketRepository.countByTicketTypeId(ticketType.getId());
        Integer totalAvailable = ticketType.getTotalAvailable();

        if(purchasedTickets + 1 > totalAvailable) {
            throw new TicketsSoldOutException();
        }

        // Create new ticket
        Ticket ticket = new Ticket();
        ticket.setStatus(TicketStatusEnum.PURCHASED);
        ticket.setTicketType(ticketType);
        ticket.setPurchaser(user);

        // Save and generate QR code
        Ticket savedTicket = ticketRepository.save(ticket);
        qrCodeService.generateQrCode(savedTicket);

        return ticketRepository.save(savedTicket);
    }


}