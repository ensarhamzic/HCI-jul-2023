package com.example.ispit.ispit;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        System.out.println(request.getPassword());
        System.out.println(request.getUsername());
        return userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
    }
}
