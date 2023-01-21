FROM amazoncorretto:17-alpine-jdk
EXPOSE 8080
COPY sqlite-postgres-1.0.jar sqlite-postgres-1.0.jar
ENTRYPOINT ["java","-jar","/sqlite-postgres-1.0.jar"]