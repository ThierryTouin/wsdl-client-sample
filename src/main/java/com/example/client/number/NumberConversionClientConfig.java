package com.example.client.number;

import com.example.client.core.GenericSoapClientFactory;
import com.example.client.core.SoapClientProperties;
import com.example.client.core.SoapLogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class NumberConversionClientConfig {

    @Bean
    public NumberConversionClient numberConversionClient(GenericSoapClientFactory factory) throws Exception {
        SoapClientProperties props = new SoapClientProperties();
        props.setContextPath("com.example.client.wsdl");
        props.setDefaultUri("https://www.dataaccess.com/webservicesserver/NumberConversion.wso");

        props.setInterceptors(List.of(new SoapLogInterceptor()));

        return factory.createClient(new NumberConversionClient(), props);
    }
}
