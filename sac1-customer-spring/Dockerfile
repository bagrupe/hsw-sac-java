FROM maven:3.8-openjdk-17 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn -DskipTests=true package

FROM openjdk:17-jdk
WORKDIR /app
COPY --from=MAVEN_BUILD ./build/target/*.jar /app/sac1-customer-spring.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "sac1-customer-spring.jar"]
