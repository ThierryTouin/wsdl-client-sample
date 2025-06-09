package com.example.client;


import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class SoapClientConfig {

    /* 
    @Bean
    public WebServiceTemplate webServiceTemplate() {
        WebServiceTemplate template = new WebServiceTemplate();
        template.setMessageSender(new HttpComponentsMessageSender());
        return template;
    }
    */

    @Bean
    public SaajSoapMessageFactory messageFactory() throws SOAPException {
        SaajSoapMessageFactory factory = new SaajSoapMessageFactory(
            MessageFactory.newInstance()
        );
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(SaajSoapMessageFactory messageFactory) {
        WebServiceTemplate template = new WebServiceTemplate(messageFactory);
        template.setDefaultUri("https://www.dataaccess.com/webservicesserver/numberconversion.wso");
        return template;
    }


}