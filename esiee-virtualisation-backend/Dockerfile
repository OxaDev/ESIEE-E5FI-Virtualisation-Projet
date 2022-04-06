FROM openjdk:16-alpine3.13
VOLUME /tmp
EXPOSE 9090
ADD ./target/backend-virtualisation-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]