package com.pye.book.service;

import com.pye.book.entity.Rental;
import com.pye.book.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;

    public List<Rental> findAll() {
        return rentalRepository.findAll();
    }

    public Rental rent(Rental rental) {
        rental.setRentalDate(LocalDate.now());
        rental.setDueDate(LocalDate.now().plusDays(14));
        return rentalRepository.save(rental);
    }

    public Rental returnBook(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("대여 내역 없음"));
        rental.setReturnDate(LocalDate.now());
        return rentalRepository.save(rental);
    }
}
