package com.service.backend.web.controllers;


import com.service.backend.web.security.UserDetailsImpl;
import com.service.backend.web.services.interfaces.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/bookings")
public class PaymentController {


    @Autowired
    IPaymentService paymentService;

    @PutMapping("{bookingId}/pay")
    public ResponseEntity<Void> pay(@PathVariable Long bookingId) {

        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        paymentService.pay(bookingId, user.getUsername());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
