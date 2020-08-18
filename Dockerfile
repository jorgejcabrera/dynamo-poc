#
# Build stage
#
FROM gradle:6.3-jdk11 as compiler
ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME

# copy source code
COPY build.gradle.kts settings.gradle.kts gradlew $APP_HOME
COPY delivery delivery
COPY core core
COPY infrastructure infrastructure

# create application jar
RUN gradle build -x test --no-daemon

# move application jar
RUN mv ./delivery/build/libs/*.jar service.jar

#
# Run stage
#
FROM adoptopenjdk/openjdk11:jre-11.0.4_11-alpine

ENV SERVER_PORT=8080
ENV APP_HOME=/usr/app/
ENV SECURITY_OPTS="-Dnetworkaddress.cache.negative.ttl=0 -Dnetworkaddress.cache.ttl=0"
ENV MAX_RAM_PERCENTAGE="-XX:MaxRAMPercentage=70"
ENV MIN_RAM_PERCENTAGE="-XX:MinRAMPercentage=70"

COPY --from=compiler $APP_HOME/service.jar $APP_HOME/service.jar
WORKDIR $APP_HOME

EXPOSE ${SERVER_PORT}

ENV JAVA_OPTS="$SECURITY_OPTS $MAX_RAM_PERCENTAGE $MIN_RAM_PERCENTAGE"
ENTRYPOINT  exec java $JAVA_OPTS -jar service.jar
