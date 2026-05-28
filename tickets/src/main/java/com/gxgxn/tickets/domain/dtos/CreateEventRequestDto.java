package com.gxgxn.tickets.domain.dtos;

import com.gxgxn.tickets.domain.entities.EventStatusEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequestDto {
    @NotBlank(message = "Event Is Required")
    private String name;

    private String description;

    @NotBlank(message = "Venue Information Is Required")
    private String venue;

    private LocalDateTime start;

    private LocalDateTime end;

    private LocalDateTime salesStart;

    private LocalDateTime salesEnd;

    @NotBlank(message = "Event Status Is Required")
    private EventStatusEnum status;

    @NotNull(message = "At least one ticket type is required")
    @Valid
    private List<CreateTicketTypeRequestDto> ticketTypes;
}
