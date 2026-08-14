package com.studentdashboard.studentdashboard.controller;

import com.studentdashboard.studentdashboard.service.ChatbotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatbot")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChatbotController {

    private final ChatbotService chatbotService;

    @PostMapping("/ask")
    public ResponseEntity<?> ask(@RequestBody QuestionRequest request) {
        String answer = chatbotService.askQuestion(request.question());
        return ResponseEntity.ok(new AnswerResponse(answer));
    }

    record QuestionRequest(String question) {}
    record AnswerResponse(String answer) {}
}