# define a imagem base
FROM openjdk:17
# define o mantenedor da imagem
LABEL maintainer="Dale"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN bash -c "touch /app.jar"
ENTRYPOINT ["java","-jar","/app.jar"]

