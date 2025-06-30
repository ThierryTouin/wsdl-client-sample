package com.example.client.number;

import com.example.client.core.HeaderSecurityInterceptor;
import com.example.client.wsdl.NumberToWords;
import com.example.client.wsdl.NumberToWordsResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Stream;

public class NumberConversionClient extends WebServiceGatewaySupport {

    public String numberToWord(String url, String soapAction, int request, HeaderSecurityInterceptor optionalSecurity) {
        NumberToWords numberToWords = new NumberToWords();
        numberToWords.setUbiNum(BigInteger.valueOf(request));

        if (optionalSecurity != null) {
            ClientInterceptor[] existing = getWebServiceTemplate().getInterceptors();
            ClientInterceptor[] combined = Stream.concat(
                Arrays.stream(existing != null ? existing : new ClientInterceptor[0]),
                Stream.of(optionalSecurity)
            ).toArray(ClientInterceptor[]::new);

            getWebServiceTemplate().setInterceptors(combined);
        }

        NumberToWordsResponse response = (NumberToWordsResponse) getWebServiceTemplate().marshalSendAndReceive(
            url,
            numberToWords,
            new SoapActionCallback(soapAction)
        );

        return response.getNumberToWordsResult();
    }
}
