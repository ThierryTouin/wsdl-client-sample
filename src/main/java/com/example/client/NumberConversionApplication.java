package com.example.client;

import com.example.client.NumberConversionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class NumberConversionApplication implements CommandLineRunner {

    @Autowired
    private NumberConversionClient client;

    public static void main(String[] args) {
        SpringApplication.run(NumberConversionApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String result = client.convertToWords(12345);
        System.out.println("Résultat : " + result);
    }
}