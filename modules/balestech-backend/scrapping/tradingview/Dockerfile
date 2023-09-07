# Use a imagem base do OpenJDK para Java 20
FROM openjdk:20-jdk

# Diretório de trabalho no contêiner
WORKDIR /app

# Copie o arquivo wait-for.sh para o contêiner
COPY wait-for-kafka-producer.sh /app/wait-for-kafka-producer.sh

# Copie o arquivo JAR da sua aplicação para o contêiner (certifique-se de que o JAR foi criado previamente ao construir seu projeto Spring Boot)
COPY target/tradingview-1.0.0-SNAPSHOT.jar /app/tradingview-1.0.0-SNAPSHOT.jar

# Expor a porta que o serviço Spring Boot estará escutando
EXPOSE 8100

# Comando para iniciar o serviço Spring Boot quando o contêiner for iniciado
CMD ["java", "-jar", "tradingview-1.0.0-SNAPSHOT.jar"]


