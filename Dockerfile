FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package -q

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/webframework-lab.jar app.jar
EXPOSE 8080
ENV PORT=8080
ENV APP_ENV=production
ENV GREETING_PREFIX=Hello
CMD ["java", "-jar", "app.jar"]
