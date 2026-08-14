package com.studentdashboard.studentdashboard.controller;
import com.studentdashboard.studentdashboard.JwtUtil;
import com.studentdashboard.studentdashboard.model.User;
import com.studentdashboard.studentdashboard.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = authService.registerUser(
                request.fullName(),
                request.email(),
                request.password(),
                request.college(),
                request.branch(),
                request.year()
        );
        return ResponseEntity.ok("User registered successfully");
    }

    record RegisterRequest(
            String fullName,
            String email,
            String password,
            String college,
            String branch,
            String year
    ) {}
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = authService.findByEmail(request.email());

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new LoginResponse(token, user.getEmail(), user.getFullName(), user.getId()));
    }

    record LoginResponse(String token, String email, String fullName, Long userId) {}
    record LoginRequest(String email, String password) {}
}