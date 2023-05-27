# ===== IMAGEM DOCKER =====
FROM openjdk:20-ea-4-jdk

# ===== ARGUMENTOS =====
ARG BALESTECH_SUITE_B3_VERSION

# ===== CRIA DIRETÓRIO DESTINO =====
RUN mkdir -p /app/balestech

# ===== VARIÁVEL DE AMBIENTE =====
ENV APP_NAME=b3-${BALESTECH_SUITE_B3_VERSION}.jar

# ===== MOVE O JAR PARA O CONTAINER =====
COPY /modules/balestech-backend/b3/target/b3-${BALESTECH_SUITE_B3_VERSION}.jar /app/balestech/

# ===== EXPÕE A APLICAÇÃO NA PORTA: =====
#EXPOSE 8051

# ===== EXECUTA O PROGRAMA =====
ENTRYPOINT [ "java", "-jar", "/app/balestech/b3-1.0.0-SNAPSHOT.jar"]

##############
# Comando de build da Imagem
#################
# > docker build -t balestech-suite-b3:1.0 --build-arg BALESTECH_SUITE_B3_VERSION=1.0.0-SNAPSHOT .


##############
# Comando de Run
#################
# > docker run -p 8051:8051 balestech-suite-b3:1.0 b3
