FROM openjdk:11

ARG XMX=1024m
ARG PROFILE=production
ARG CLOUD_CONFIG

ENV XMX=$XMX
ENV PROFILE=$PROFILE
ENV CLOUD_CONFIG=$CLOUD_CONFIG

VOLUME /tmp

ADD ./target/st-microservice-reports-1.0.0.jar st-microservice-reports.jar

EXPOSE 8080

ENTRYPOINT java -Xmx$XMX -jar /st-microservice-reports.jar --spring.profiles.active=$PROFILE --spring.cloud.config.uri=$CLOUD_CONFIG