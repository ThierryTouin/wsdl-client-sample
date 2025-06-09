package com.example.client;

import com.example.client.wsdl.NumberToWords;
import com.example.client.wsdl.NumberToWordsResponse;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;



@Component
public class NumberConversionClient extends WebServiceGatewaySupport {


    private final WebServiceTemplate webServiceTemplate;

    @Autowired
    public NumberConversionClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public String convertToWords(int number) {
        NumberToWords request = new NumberToWords();
        request.setUbiNum(java.math.BigInteger.valueOf(number));

        NumberToWordsResponse response = (NumberToWordsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                        "https://www.dataaccess.com/webservicesserver/numberconversion.wso",
                        request,
                        new SoapActionCallback("https://www.dataaccess.com/webservicesserver/NumberConversion.wso/NumberToWords"));

        return response.getNumberToWordsResult();
    }
}
