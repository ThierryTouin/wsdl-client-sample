package com.example.client;


import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class SoapConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.wsdl");
        return marshaller;
    }

    @Bean
    public SoapClient soapClient(Jaxb2Marshaller marshaller) {
        SoapClient client = new SoapClient();
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        client.setDefaultUri("http://example.com/soap-service");
        return client;
    }
}