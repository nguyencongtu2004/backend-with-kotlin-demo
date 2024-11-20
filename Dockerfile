# Build Stage
FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

# Runtime Stage
FROM openjdk:11
EXPOSE 8080:8080
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/ktor-demo.jar
ENTRYPOINT ["java", "-jar", "/app/ktor-demo.jar"]
