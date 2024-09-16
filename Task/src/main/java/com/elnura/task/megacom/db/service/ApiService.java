package com.elnura.task.megacom.db.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);

    @Autowired
    private RestTemplate restTemplate;

    public void fetchAndLogData() {
        String url = "https://api.restful-api.dev/objects";

        try {
            String response = restTemplate.getForObject(url, String.class);
            logger.info("Response from API: {}", response);
        } catch (Exception e) {
            logger.error("Error occurred while making the GET request", e);
        }
    }
}