package com.studentdashboard.studentdashboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GitHubService {

    private final RestTemplate restTemplate;

    public Map<String, Object> getGitHubStats(String username) {
        String url = "https://api.github.com/users/" + username;
        Map<String, Object> userInfo = restTemplate.getForObject(url, Map.class);
        return userInfo;
    }

    public Object getRepositories(String username) {
        String url = "https://api.github.com/users/" + username + "/repos?sort=updated&per_page=10";
        return restTemplate.getForObject(url, Object.class);
    }
}