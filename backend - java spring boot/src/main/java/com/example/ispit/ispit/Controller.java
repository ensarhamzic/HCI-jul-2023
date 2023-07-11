package com.example.ispit.ispit;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping("/controller")
@RequiredArgsConstructor
public class Controller {
    private final MyService service;
}
