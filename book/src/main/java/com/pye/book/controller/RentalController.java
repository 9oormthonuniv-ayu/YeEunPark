package com.pye.book.controller;

import com.pye.book.entity.Book;
import com.pye.book.entity.Rental;
import com.pye.book.entity.User;
import com.pye.book.repository.BookRepository;
import com.pye.book.repository.RentalRepository;
import com.pye.book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rentals")
public class RentalController {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @GetMapping
    public String list(Model model) {
        List<Rental> rentals = rentalRepository.findAll();
        model.addAttribute("rentals", rentals);
        return "rental/list";
    }

    @GetMapping("/save")
    public String saveForm(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        return "rental/save";
    }

    @PostMapping("/save")
    public String saveRental(@RequestParam Long userId,
                             @RequestParam Long bookId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("책 없음"));

        Rental rental = Rental.builder()
                .user(user)
                .book(book)
                .rentalDate(LocalDate.now())
                .dueDate(LocalDate.now().plusDays(14))
                .build();

        rentalRepository.save(rental);
        return "redirect:/rentals";
    }

    @PostMapping("/{id}/return")
    public String returnRental(@PathVariable Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("대여 기록 없음"));
        rental.setReturnDate(LocalDate.now());
        rentalRepository.save(rental);
        return "redirect:/rentals";
    }
}
