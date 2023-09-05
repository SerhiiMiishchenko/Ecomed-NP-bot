FROM amazoncorretto:17
LABEL authors="Serhii Mishchenko"
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
RUN chmod +x ./mvnw
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]
#ENV FLY_API_HOSTNAME="https://api.machines.dev"


