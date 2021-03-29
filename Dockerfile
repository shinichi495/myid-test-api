FROM adoptopenjdk/openjdk11:latest
MAINTAINER experto.com
VOLUME /tmp
EXPOSE 9090
ADD configuration/target/configuration-1.0.0.jar configuration-1.0.0.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/configuration-1.0.0.jar"]