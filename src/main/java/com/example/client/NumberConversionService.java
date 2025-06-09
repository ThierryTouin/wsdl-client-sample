package com.example.client;

@Service
public class NumberConversionService {
    
    @Autowired
    private SoapClient soapClient;

    public String convertNumberToWords(BigInteger number) {
        ObjectFactory factory = new ObjectFactory();
        NumberToWords request = factory.createNumberToWords();
        request.setUbiNum(number);
        
        return ((NumberToWordsResponse) soapClient.callWebService(
            "https://www.dataaccess.com/webservicesserver/NumberConversion.wso",
            "http://www.dataaccess.com/webservicesserver/NumberConversion.wso/NumberToWords",
            request
        )).getNumberToWordsResult();
    }
}
