FROM maven:3.8.2-openjdk-11 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn -DskipTests=true package

FROM openjdk:11-jdk
WORKDIR /app
COPY --from=MAVEN_BUILD ./build/target/*.jar /app/sac1-iban-spring.jar

EXPOSE 7071
ENTRYPOINT ["java", "-jar", "sac1-iban-spring.jar"]
