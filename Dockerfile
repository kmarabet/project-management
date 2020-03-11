FROM ubuntu-jdk-11

MAINTAINER Kamil Marabet "kamilmarabet@gmail.com"

# RUN apt-get update && apt-get install -y openjdk-11-jdk

ENV version=aws-db-usage

ENV dbuser=postgres
ENV dbpass=postgres321
#ENV jdbcurl=jdbc:dbdriver://host:port/dbname
ENV jdbcurl=jdbc:postgresql://pmadatabaseaws.cuoydnlxdmn5.eu-central-1.rds.amazonaws.com:5432/pmadatabase

WORKDIR /usr/local/bin

ADD target/pma-app.jar .

ENTRYPOINT ["java", "-jar", "pma-app.jar"]
