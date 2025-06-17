package com.example.client;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;


@Configuration
public class SoapClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.client.wsdl");
        return marshaller;
    }

    @Bean
    public NumberConversionClient soapClient(Jaxb2Marshaller marshaller) throws Exception {
        NumberConversionClient client = new NumberConversionClient();
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        client.setDefaultUri("https://www.dataaccess.com/webservicesserver/NumberConversion.wso");

        // Ajout de l'interceptor
        client.setInterceptors(new ClientInterceptor[]{
            new SoapLogInterceptor()
        });

        return client;
    }
}