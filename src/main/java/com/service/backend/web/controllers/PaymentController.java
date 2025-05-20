package com.service.backend.web.controllers;


import com.service.backend.web.models.responses.PaymentResponse;
import com.service.backend.web.services.helper.SecurityHelper;
import com.service.backend.web.services.interfaces.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/bookings")
public class PaymentController {


    @Autowired
    IPaymentService paymentService;

    @PutMapping("{bookingId}/pay")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<PaymentResponse> pay(@PathVariable Long bookingId) {
        return new ResponseEntity<>(paymentService.pay(bookingId, SecurityHelper.getUserConnected().getUsername()),HttpStatus.OK);
    }
}
