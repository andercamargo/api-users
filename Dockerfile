FROM openjdk:21
LABEL authors="andercamargo"

WORKDIR /api-users
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar api-users.jar

ENTRYPOINT ["java", "-jar", "api-users.jar"]