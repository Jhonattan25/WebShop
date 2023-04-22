FROM eclipse-temurin:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} gateway.jar
ENTRYPOINT ["java", "-jar", "/gateway.jar"]