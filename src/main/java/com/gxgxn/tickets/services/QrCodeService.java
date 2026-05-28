package com.gxgxn.tickets.services;

import com.gxgxn.tickets.domain.entities.QrCode;
import com.gxgxn.tickets.domain.entities.Ticket;

import java.util.UUID;

public interface QrCodeService {
    QrCode generateQrCode(Ticket ticket);
    byte[] getQrCodeImageForUserAndTicket(UUID userId, UUID ticketId);
}
