FROM eclipse-temurin:17-jdk-jammy
LABEL authors="Serhii"
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]

ENV BOT_TOKEN="6256284335:AAGPNif2jIEs__in25yNJZwMj1zgVYRP0lA"
ENV BOT_NAME=ecomed_np_bot
ENV BASE_URL="https://api.novaposhta.ua/v2.0/json/"
ENV API_KEY="7a032c20d1ab9ee217a14c920bd834e0"
ENV BASE_PHONE_NUMBER=380672470972

