package com.example.client.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Component
public class GenericSoapClientFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericSoapClientFactory.class);

    public <T extends WebServiceGatewaySupport> T createClient(T client, SoapClientProperties props) throws Exception {

        LOGGER.info("createClient()");
        
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(props.getContextPath());
        marshaller.afterPropertiesSet();

        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        client.setDefaultUri(props.getDefaultUri());

        if (props.getInterceptors() != null) {
            client.setInterceptors(props.getInterceptors().toArray(new ClientInterceptor[0]));
        }

        LOGGER.info("createClient() : client=" + client.toString());

        return client;
    }
}
