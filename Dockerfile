FROM amazoncorretto:17
LABEL authors="Serhii Mishchenko"
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw
RUN ./mvnw dependency:resolve
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]
EXPOSE 80/tcp



