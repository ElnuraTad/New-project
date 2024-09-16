package com.elnura.task.megacom.controller;

import com.elnura.task.megacom.db.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApiCommandLineRunner implements CommandLineRunner {

    @Autowired
    private ApiService apiService;

    @Override
    public void run(String... args) throws Exception {
        apiService.fetchAndLogData();
    }
}
