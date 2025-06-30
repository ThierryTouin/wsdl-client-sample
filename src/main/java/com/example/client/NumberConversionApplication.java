package com.example.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.client.number.NumberConversionClient;


@SpringBootApplication
public class NumberConversionApplication implements CommandLineRunner {

    @Autowired
    private NumberConversionClient numberConversionClient;

    public static void main(String[] args) {
        SpringApplication.run(NumberConversionApplication.class, args);
    }

    @Override // Important: Use @Override to confirm you're overriding an interface method
    public void run(String... args) throws Exception {
        String conv = "456";

        if (args.length > 0) {
            conv = args[0];
        }

        int convInt = Integer.parseInt(conv);

        // Use the injected client
        String response = numberConversionClient.numberToWord(
            "https://www.dataaccess.com/webservicesserver/NumberConversion.wso",
            "http://www.dataaccess.com/webservicesserver/NumberConversion.wso/NumberToWords",
            convInt,
            null);
        System.err.println("\r\n>>>>>>>>>>>>>>" + response);
    }
}