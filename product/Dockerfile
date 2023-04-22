FROM eclipse-temurin:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} product.jar
ENTRYPOINT ["java", "-jar", "/product.jar"]