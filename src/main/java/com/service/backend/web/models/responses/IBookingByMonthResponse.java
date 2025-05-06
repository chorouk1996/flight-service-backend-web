package com.service.backend.web.models.responses;

public interface IBookingByMonthResponse {

     String getMonth();

     long getBookingCount();

     double getRevenue();
}
