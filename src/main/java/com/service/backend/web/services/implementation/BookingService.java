package com.service.backend.web.services.implementation;

import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.repositories.BookingRepository;
import com.service.backend.web.services.interfaces.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.service.backend.web.services.mapper.BookingMapper.mapBookingDtoToEntity;
import static com.service.backend.web.services.mapper.BookingMapper.mapBookingEntityToDto;

@Service
public class BookingService implements IBookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public void addBooking(BookingDto booking) {
         bookingRepository.save(mapBookingDtoToEntity(booking));
    }

    @Override
    public List<BookingDto> getAllBooking() {
        return bookingRepository.findAll().stream().map(booking->mapBookingEntityToDto(booking)).toList();
    }


}
