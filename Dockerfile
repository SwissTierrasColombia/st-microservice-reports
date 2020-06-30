FROM openjdk:12

VOLUME /tmp

ADD ./target/st-microservice-reports-0.0.1-SNAPSHOT.jar st-microservice-reports.jar

EXPOSE 8080

ENTRYPOINT java -jar /st-microservice-reports.jar