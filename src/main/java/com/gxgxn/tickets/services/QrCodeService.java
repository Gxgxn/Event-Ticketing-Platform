package com.gxgxn.tickets.services;

import com.gxgxn.tickets.domain.entities.QrCode;
import com.gxgxn.tickets.domain.entities.Ticket;

public interface QrCodeService {
    QrCode generateQrCode(Ticket ticket);
}
