package com.gxgxn.tickets.mappers;

import com.gxgxn.tickets.domain.CreateEventRequest;
import com.gxgxn.tickets.domain.CreateTicketTypeRequest;
import com.gxgxn.tickets.domain.dtos.*;
import com.gxgxn.tickets.domain.entities.Event;
import com.gxgxn.tickets.domain.entities.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);

    ListEventTicketTypeResponseDto toDto(TicketType ticketType);

    ListEventResponseDto toListEventResponseDto(Event event);
}
