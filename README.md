# NBPExchangeRatesViewer

## About Application
NBPExchangeRatesViewer is a microservice made in Java with Spring Boot. Server is responsible for querying data from the Narodowy Bank Polski's public APIs and return relevant information from them.

## Guides

### How to run
To run the application you need to create jar file with command:

`
mvn package
`

After that you can run the application with command:

`
java -jar target/NBPExchangeRatesViewer-0.0.1-SNAPSHOT.jar
`

### How To Dockerize

There is prepared Dockerfile so you can easily create your own docker image with command:

`
docker build --tag=nbp-exchange:latest .
`

Next step is to run your docker image:

`
docker run -d -p8080:8080 nbp-exchange:latest
`

## REST API
Service provides its API on the following endpoints (all of them is a GET method):
* http://localhost:8080/api/v1/exchangerates/average/{code}/{efficientDate} - to get average exchange rate of currency which code you provide {code} on provided date {eficientDate}
* http://localhost:8080/api/v1/exchangerates/minmax/{code}/{topCount} - to get min and max average exchange rate value of currency which code you provide {code} of the last N quotations {topCount}
* http://localhost:8080/api/v1/exchangerates/maxdiff/{code}/{topCount} - to get max difference between the bid and ask exchange rate of currency which code you provide {code} of the last N quotations {topCount}

### Parameters conditions and validation:
* {code} must be a valid currency code included in the "Table A of average exchange rates of foreign currencies" https://nbp.pl/en/statistic-and-financial-reporting/rates/table-a/ for average and min-max values and "Table C of buy and sell rates of foreign currencies" https://nbp.pl/en/statistic-and-financial-reporting/rates/table-c/ for major difference value, otherwise the return is `400 - Bad Request`
* {efficientDate} must be a valid date in `yyyy-MM-dd` format, otherwise the return is `400 - Bad Request`. In the case of holidays and weekends, the application returns `404 - Not Found`
* {topCount} is a number of last quotations to check exchange rates, must be in range 1-255 otherwise the return is `400 - Bad Request`

### Output Format

All endpoints provide date in a String format.

## Usage

### Http requests

To get average exchange rate of Thailand bat on 2023-04-25 use:
http://localhost:8080/api/v1/exchangerates/average/thb/2023-04-24

The respose will be:<br>
`code: THB`<br>
`effectiveDate: 2023-04-25`<br>
`average: 0.1213`

### Swagger UI

There is Swagger UI provided on http://localhost:8080/swagger-ui/index.html

## Tests

Some of the functionality has been tested with automatic tests in CurrencyCodeTableATest, CurrencyCodeTableBTest, ExchangeRatesControllerTest and ExchangeRatesServiceTest classes 