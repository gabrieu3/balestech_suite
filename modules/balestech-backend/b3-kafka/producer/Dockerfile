# Use a imagem base do OpenJDK para Java 20
FROM openjdk:20-jdk

# Copie o binário ncat para o contêiner
COPY ./ncat/ncat /usr/local/bin/ncat

# Diretório de trabalho no contêiner
WORKDIR /app

# Copie o arquivo wait-for-kafka.sh para o diretório /app no contêiner
COPY wait-for-kafka.sh /app/wait-for-kafka.sh

# Copie o arquivo JAR da sua aplicação para o contêiner (certifique-se de que o JAR foi criado previamente ao construir seu projeto Spring Boot)
COPY target/kafka-producer-1.0.0-SNAPSHOT.jar /app/kafka-producer-1.0.0-SNAPSHOT.jar

# Expor a porta que o serviço Spring Boot estará escutando
EXPOSE 8200

# Comando para iniciar o serviço Spring Boot quando o contêiner for iniciado
CMD ["java", "-jar", "kafka-producer-1.0.0-SNAPSHOT.jar"]