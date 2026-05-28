package com.gxgxn.tickets.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTicketTypeRequest {
    private String name;
    private UUID id;
    private Double price;
    private String description;
    private Integer totalAvailable;

}
