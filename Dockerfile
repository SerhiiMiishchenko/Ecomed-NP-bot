
FROM amazoncorretto:17
LABEL authors="Serhii Mishchenko"
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw
RUN ./mvnw dependency:resolve
COPY src ./src
EXPOSE 8080/tcp
CMD ["./mvnw", "spring-boot:run"]
