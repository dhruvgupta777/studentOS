package com.studentdashboard.studentdashboard.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.Map;

@Service
public class LeetCodeService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Object getLeetCodeStats(String username) {
        String url = "https://leetcode-stats-api.herokuapp.com/" + username;

        try {
            return restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            // fallback to another API
            String url2 = "https://alfa-leetcode-api.onrender.com/" + username;
            return restTemplate.getForObject(url2, Map.class);
        }
    }
}