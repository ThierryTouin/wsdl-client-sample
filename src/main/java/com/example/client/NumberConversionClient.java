package com.example.client;



import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.example.client.wsdl.NumberToWords;
import com.example.client.wsdl.NumberToWordsResponse;

public class NumberConversionClient extends WebServiceGatewaySupport {
    
    private static final Logger log = LoggerFactory.getLogger(NumberConversionClient.class);

    public String numberToWord(String url, String soapAction, int request) {


        NumberToWords numberToWords = new NumberToWords();
        numberToWords.setUbiNum(BigInteger.valueOf(request));


        NumberToWordsResponse numberToWordsResponse = (NumberToWordsResponse) getWebServiceTemplate().marshalSendAndReceive(
            url, 
            numberToWords,
            new SoapActionCallback(soapAction)
        );


        String result = numberToWordsResponse.getNumberToWordsResult();

        return result;
    }
}
