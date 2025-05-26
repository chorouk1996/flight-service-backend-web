package com.service.backend.web.models.requests;


import lombok.Data;

@Data
public class SearchBookingRequest {

    private String email;
    private String status;
    private String flightNumber;
    private int page;
    private int size;


}
