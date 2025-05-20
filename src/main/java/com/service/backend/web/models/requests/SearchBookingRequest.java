package com.service.backend.web.models.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchBookingRequest {

    private String email;
    private String status;
    private String flightNumber;
    private int page;
    private int size;


}
