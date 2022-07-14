FROM maven:3.6.0-jdk-11-slim AS build
COPY pom.xml /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean package -DskipTests=true -q

FROM tomcat:9-jre8-slim
RUN rm -rf /usr/local/tomcat/webapps/
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
