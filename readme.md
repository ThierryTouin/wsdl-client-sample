
## Commands
mvn clean compile
mvn spring-boot:run

Résultat : one hundred twenty three




mvn org.codehaus.mojo:jaxws-maven-plugin:wsimport \
  -DwsdlUrl=https://www.dataaccess.com/webservicesserver/numberconversion.wso?WSDL \
  -DpackageName=com.example.ws
