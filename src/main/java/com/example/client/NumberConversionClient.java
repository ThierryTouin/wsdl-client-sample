package com.example.client;

import com.example.client.wsdl.NumberToWords;
import com.example.client.wsdl.NumberToWordsResponse;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;



import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class SoapClient extends WebServiceGatewaySupport {
    
    public Object callWebService(String url, String soapAction, Object request) {
        return getWebServiceTemplate().marshalSendAndReceive(
            url, 
            request,
            new SoapActionCallback(soapAction)
        );
    }
}
