FROM maven:3.6.0-jdk-11-slim AS build
COPY pom.xml /app/
COPY src /app/src
ENV SPRING_DATASOURCE_URL ${SPRING_DATASOURCE_URL}
ENV SPRING_DATASOURCE_USERNAME ${SPRING_DATASOURCE_USERNAME}
ENV SPRING_DATASOURCE_PASSWORD ${SPRING_DATASOURCE_PASSWORD}
RUN mvn -f /app/pom.xml clean package -DskipTests=false -q

FROM tomcat:9-jre8-slim
RUN rm -rf /usr/local/tomcat/webapps/
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
