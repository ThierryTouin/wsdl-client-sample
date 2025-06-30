package com.example.client.core;

import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.SoapHeader;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

public class HeaderSecurityInterceptor implements ClientInterceptor {

    private final String username;
    private final String password;

    public HeaderSecurityInterceptor(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
        try {
            SoapMessage message = (SoapMessage) messageContext.getRequest();
            SoapHeader header = message.getSoapHeader();

            String xml = String.format("""
                <wsse:Security xmlns:wsse="http://schemas.xmlsoap.org/ws/2002/12/secext">
                  <wsse:UsernameToken>
                    <wsse:Username>%s</wsse:Username>
                    <wsse:Password>%s</wsse:Password>
                  </wsse:UsernameToken>
                </wsse:Security>
            """, username, password);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new StreamSource(new StringReader(xml)), header.getResult());

        } catch (Exception e) {
            throw new WebServiceClientException("Failed to add WS-Security header", e) {};
        }
        return true;
    }

    @Override public boolean handleResponse(MessageContext messageContext) { return true; }
    @Override public boolean handleFault(MessageContext messageContext) { return true; }
    @Override public void afterCompletion(MessageContext messageContext, Exception ex) {}
}
