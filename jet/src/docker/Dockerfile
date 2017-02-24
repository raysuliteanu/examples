FROM java:8-jre

# no default for APPNAME; if not specified, docker build will fail
# see https://docs.docker.com/engine/reference/builder/#/arg
ARG APPNAME
ARG APP_VER

COPY ${APPNAME}-${APP_VER}.jar /app.jar

EXPOSE 8080 8443

ENTRYPOINT ["java", "-jar", "/app.jar"]
