FROM openjdk:17-alpine
WORKDIR /demo-bank
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar demo-bank-0.0.1-SNAPSHOT.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo-bank-0.0.1-SNAPSHOT.jar"]
