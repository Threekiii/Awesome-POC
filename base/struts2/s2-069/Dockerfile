FROM maven:3.9.9-eclipse-temurin-11

WORKDIR /build
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


FROM tomcat:9.0.96-jdk11

RUN rm -rf /usr/local/tomcat/webapps/ROOT

COPY --from=0 \
     /build/target/CVE-2025-68493.war \
     /usr/local/tomcat/webapps/ROOT.war
