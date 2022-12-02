# define a imagem base
FROM openjdk:17
# define o mantenedor da imagem
LABEL maintainer="Dale"
ADD target/autenticacao-0.0.1-SNAPSHOT.jar autenticacao.jar
ENTRYPOINT ["java","-jar","autenticacao.jar"]

