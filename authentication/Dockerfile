FROM eclipse-temurin:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} authentication.jar
ENTRYPOINT ["java", "-jar", "/authentication.jar"]