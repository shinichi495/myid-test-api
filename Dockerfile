FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
EXPOSE 9090
ADD configuration/target/configuration-1.0.0.jar configuration-1.0.0.jar
RUN curl -fsSLO https://get.docker.com/builds/Linux/x86_64/docker-17.04.0-ce.tgz && tar xzvf docker-17.04.0-ce.tgz && mv docker/docker /usr/local/bin && rm -r docker docker-17.04.0-ce.tgz
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/configuration-1.0.0.jar"]