FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./distro/smtp-server-mock.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
