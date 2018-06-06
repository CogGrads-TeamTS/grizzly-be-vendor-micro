FROM openjdk:8-jre-alpine
MAINTAINER Piotr Minkowski <piotr.minkowski@gmail.com>
ADD target/vendor-service.jar vendor-service.jar
ENTRYPOINT ["java", "-jar", "/vendor-service.jar"]
EXPOSE 4444