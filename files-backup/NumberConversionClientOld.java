package com.example.client;

import com.example.client.wsdl.NumberConversion;
import com.example.client.wsdl.NumberConversionSoapType;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.math.BigInteger;


public class NumberConversionClientOld {

    public static void main(String[] args) throws Exception {


        URL wsdlURL = new URL("https://www.dataaccess.com/webservicesserver/numberconversion.wso?WSDL");
        QName SERVICE_NAME = new QName("http://www.dataaccess.com/webservicesserver/", "NumberConversion");
        Service service = Service.create(wsdlURL, SERVICE_NAME);
        NumberConversionSoapType port = service.getPort(NumberConversionSoapType.class);


        int valeurEntiere = 12345;
        BigInteger valeurBigInteger = BigInteger.valueOf(valeurEntiere);
        String result = port.numberToWords(valeurBigInteger);
        System.out.println("Résultat : " + result);


        
    }
}
