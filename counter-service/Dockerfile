FROM openjdk:17
VOLUME /tmp
EXPOSE 8085
ARG JAR_FILE=build/libs/*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]