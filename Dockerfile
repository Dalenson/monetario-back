# define a imagem base
FROM openjdk:17
ADD target/autenticacao-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

expose 8081