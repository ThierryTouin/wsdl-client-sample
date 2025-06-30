package com.example.client.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapMessage;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class SoapLogInterceptor implements ClientInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SoapLogInterceptor.class);

    private final Transformer transformer;

    public SoapLogInterceptor() throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        this.transformer = transformerFactory.newTransformer();
    }

    @Override
    public boolean handleRequest(MessageContext messageContext) {
        try {
            LOGGER.info("SOAP Request:\n{}", extractMessage(messageContext.getRequest()));
        } catch (Exception e) {
            LOGGER.warn("Failed to log SOAP request", e);
        }
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) {
        try {
            LOGGER.info("SOAP Response:\n{}", extractMessage(messageContext.getResponse()));
        } catch (Exception e) {
            LOGGER.warn("Failed to log SOAP response", e);
        }
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext) {
        try {
            LOGGER.error("SOAP Fault:\n{}", extractMessage(messageContext.getResponse()));
        } catch (Exception e) {
            LOGGER.warn("Failed to log SOAP fault", e);
        }
        return true;
    }

    private String extractMessage(org.springframework.ws.WebServiceMessage message) throws Exception {
        StringWriter writer = new StringWriter();
        transformer.transform(message.getPayloadSource(), new StreamResult(writer));
        return writer.toString();
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Exception ex) throws WebServiceClientException {
        if (ex != null) {
            LOGGER.error("ex:\n{}", ex.getMessage());
        }
        try {
            LOGGER.info("SOAP Response:\n{}", extractMessage(messageContext.getResponse()));
        } catch (Exception e) {
            LOGGER.warn("Failed to log SOAP response", e);
        }
    }
}
