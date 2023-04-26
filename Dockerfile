FROM openjdk:17-alpine
MAINTAINER komfortcieplny.com
COPY target/NBPExchangeRatesViewer-0.0.1-SNAPSHOT.jar NBPExchangeRatesViewer.jar
ENTRYPOINT ["java","-jar","/NBPExchangeRatesViewer.jar"]