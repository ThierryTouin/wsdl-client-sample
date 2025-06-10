package com.example.client;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


@Configuration
public class SoapClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.client.wsdl");
        return marshaller;
    }

    @Bean
    public NumberConversionClient soapClient(Jaxb2Marshaller marshaller) {
        NumberConversionClient client = new NumberConversionClient();
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        client.setDefaultUri("https://www.dataaccess.com/webservicesserver/NumberConversion.wso");
        return client;
    }
}