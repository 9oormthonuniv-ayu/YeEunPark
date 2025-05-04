package com.pye.book.controller;

import com.pye.book.entity.Author;
import com.pye.book.entity.Book;
import com.pye.book.entity.Genre;
import com.pye.book.repository.AuthorRepository;
import com.pye.book.repository.BookRepository;
import com.pye.book.repository.GenreRepository;
import com.pye.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    // 도서 목록 페이지
    @GetMapping
    public String getBookList(Model model) {
        List<Book> bookList = bookService.findAll();
        model.addAttribute("books", bookList);
        return "book/list"; // → templates/book/list.html
    }

    // 도서 등록 폼 페이지
    @GetMapping("/save")
    public String saveForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        return "book/save"; // → templates/book/save.html
    }

    // 도서 등록 처리
    @PostMapping("/save")
    public String saveBook(@RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String publisher,
                           @RequestParam int stock,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishedAt,
                           @RequestParam Long authorId,
                           @RequestParam Long genreId) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("작가 없음"));

        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new IllegalArgumentException("장르 없음"));

        Book book = Book.builder()
                .title(title)
                .isbn(isbn)
                .publisher(publisher)
                .stock(stock)
                .publishedAt(publishedAt)
                .author(author)
                .genre(genre)
                .build();

        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 도서가 없습니다."));
        model.addAttribute("book", book);
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        return "book/edit";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book,
                             @RequestParam Long authorId,
                             @RequestParam Long genreId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("작가 없음"));
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new IllegalArgumentException("장르 없음"));

        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
        return "redirect:/books";
    }
}