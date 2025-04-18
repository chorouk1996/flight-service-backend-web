package com.service.backend.web.services.implementation;

import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.repositories.BookingRepository;
import com.service.backend.web.services.interfaces.IBookingService;
import com.service.backend.web.services.mapper.BookingMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.service.backend.web.services.mapper.BookingMapper.mapBookingDtoToEntity;

@Service
public class BookingService implements IBookingService {

    BookingRepository bookingRepository;

    @Override
    public void addBooking(BookingDto booking) {
        bookingRepository.save(mapBookingDtoToEntity(booking));
    }

    @Override
    public List<BookingDto> getAllBooking() {
        return bookingRepository.findAll().stream().map(BookingMapper::mapBookingEntityToDto).toList();
    }

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
}
