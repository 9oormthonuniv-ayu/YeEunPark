package com.pye.book.controller;

import com.pye.book.entity.Genre;
import com.pye.book.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreController {

    private final GenreRepository genreRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("genres", genreRepository.findAll());
        return "genre/list";
    }

    @GetMapping("/save")
    public String saveForm(Model model) {
        model.addAttribute("genre", new Genre());
        return "genre/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Genre genre) {
        genreRepository.save(genre);
        return "redirect:/genres";
    }
}
