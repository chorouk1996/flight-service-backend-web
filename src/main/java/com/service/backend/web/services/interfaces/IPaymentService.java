package com.service.backend.web.services.interfaces;


import com.service.backend.web.models.responses.PaymentResponse;

public interface IPaymentService {

   PaymentResponse pay(Long id, String username);

}
