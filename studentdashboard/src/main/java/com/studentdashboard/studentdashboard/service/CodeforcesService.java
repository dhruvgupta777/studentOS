package com.studentdashboard.studentdashboard.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class CodeforcesService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Object getCodeforcesStats(String handle) {
        String url = "https://codeforces.com/api/user.info?handles=" + handle;
        return restTemplate.getForObject(url, Map.class);
    }

    public Object getRecentContests(String handle) {
        String url = "https://codeforces.com/api/user.rating?handle=" + handle;
        return restTemplate.getForObject(url, Map.class);
    }
}