package com.pye.book.controller;

import com.pye.book.entity.Author;
import com.pye.book.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "author/list";
    }

    @GetMapping("/save")
    public String saveForm(Model model) {
        model.addAttribute("author", new Author());
        return "author/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Author author) {
        authorRepository.save(author);
        return "redirect:/authors";
    }
}
