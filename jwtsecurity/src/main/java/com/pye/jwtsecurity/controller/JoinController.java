package com.pye.jwtsecurity.controller;

import com.pye.jwtsecurity.dto.JoinDTO;
import com.pye.jwtsecurity.service.JoinService;
import org.springframework.web.bind.annotation.*;

@RestController
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinProcess(@RequestBody JoinDTO joinDTO) {
        joinService.joinProcess(joinDTO);
        return "회원가입 완료";
    }
}