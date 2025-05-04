package com.pye.book.controller;

import com.pye.book.entity.User;
import com.pye.book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    @GetMapping("/save")
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        return "user/save";
    }

    @PostMapping
    public String create(@ModelAttribute User user) {
        System.out.println("▶ 저장할 유저: " + user);
        user.setCreatedAt(LocalDate.now());
        userRepository.save(user);
        return "redirect:/users";
    }

    // 수정 폼 이동
    @GetMapping("/{id}/edit")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
        model.addAttribute("user", user);
        return "user/edit"; // → templates/user/edit.html
    }

    // 수정 처리
    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        userRepository.save(user); // 같은 id면 update 처리됨
        return "redirect:/users";
    }

    // 삭제 처리
    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }
}
