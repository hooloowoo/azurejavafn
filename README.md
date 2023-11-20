Test locally:


curl --dump-header x.xxx -X POST http://localhost:7071/api/v1/webtolead/json -H "Content-Type: application/x-www-form-urlencoded" -d '{"firstName":"TEST","lastName":"ELEK","emailAddress":"example@example.com","zipCode":"1075"}'
curl --dump-header x.xxx -X POST http://localhost:7071/api/v1/webtolead -H "Content-Type: application/x-www-form-urlencoded" -d 'firstName=TEST&lastName=ELEK&emailAddress=example@example.com&zipCode=1075'

On Azure:

curl --dump-header x.xxx -X POST https://nn-hu-salesforce-webtolead-services-1700051710293.azurewebsites.net/api/v1/webtolead/json -H "Content-Type: application/x-www-form-urlencoded" -d '{"firstName":"TEST","lastName":"ELEK","emailAddress":"example@example.com","zipCode":"1075"}'
curl --dump-header x.xxx -X POST https://nn-hu-salesforce-webtolead-services-1700051710293.azurewebsites.net/api/v1/webtolead -H "Content-Type: application/x-www-form-urlencoded" -d 'firstName=TEST&lastName=ELEK&emailAddress=example@example.com&zipCode=1075'


Run Locally:
mvn clean package
mvn azure-functions:run

Deploy to Azure:
mvn azure-functions:deploy
