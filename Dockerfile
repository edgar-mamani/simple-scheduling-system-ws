FROM openjdk:11
LABEL MAINTAINER "Miguel Mamani <miguel.coder.pe@gmail.com>"
ADD target/simple-scheduling-system.jar simple-scheduling-system.jar
ENTRYPOINT ["java", "-jar", "simple-scheduling-system.jar"]
EXPOSE 8080
