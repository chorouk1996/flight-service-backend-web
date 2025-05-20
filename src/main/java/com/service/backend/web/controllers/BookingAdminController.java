package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.requests.CreateBookingRequest;
import com.service.backend.web.models.requests.SearchBookingRequest;
import com.service.backend.web.models.responses.CreateBookingResponse;
import com.service.backend.web.models.responses.SearchBookingResponse;
import com.service.backend.web.services.helper.SecurityHelper;
import com.service.backend.web.services.interfaces.IBookingService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/booking")
@AllArgsConstructor
public class BookingAdminController {

    private final IBookingService bookingService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public BookingDto getBooking(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<BookingDto>> getAllBooking(@RequestParam(required = false, defaultValue = "0") int page,@RequestParam(required = false, defaultValue = "10") int size) {
        return new ResponseEntity<>(bookingService.getAllBooking(page,size), HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public CreateBookingResponse addBooking(@RequestBody @Valid CreateBookingRequest booking) {
         return bookingService.addBooking(booking, SecurityHelper.getUserConnected().getUsername());
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BookingDto> updateBooking(@RequestBody @Valid BookingDto booking) {
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @PutMapping("/cancel/{bookingId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void cancelBooking(@PathVariable Long bookingId) {
         bookingService.cancelBooking(bookingId);
    }

    @PutMapping("/confirm/{bookingId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void confirmBooking(@PathVariable Long bookingId) {
        bookingService.confirmBooking(bookingId);
    }

    @PostMapping("/search")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<SearchBookingResponse> searchBooking(@RequestBody SearchBookingRequest request){
        return bookingService.searchBooking(request);
    }

    @GetMapping("/export")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void exportBooking(HttpServletResponse response){
         bookingService.exportAllBookingto(response);
    }
}
