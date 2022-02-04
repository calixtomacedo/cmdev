#FROM openjdk:8-jdk-alpine
#FROM adoptopenjdk/openjdk11:alpine

FROM adoptopenjdk/openjdk16:alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} login-app.jar
ENTRYPOINT ["java","-jar","/login-app.jar"]

# docker build -t cmdev/login-app .

# docker run -it -p 8080:8080 cmdev/login-app