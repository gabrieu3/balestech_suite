# ===== IMAGEM DOCKER =====
FROM openjdk:20-ea-4-jdk


# ===== ARGUMENTOS =====
ARG BALESTECH_SUITE_B3_VERSION


# ===== CRIA DIRETÓRIO DESTINO =====
RUN mkdir -p /app/balestech


# ===== VARIÁVEL DE AMBIENTE =====
ENV APP_NAME=b3-${BALESTECH_SUITE_B3_VERSION}.jar
ENV LOG_DIR=/var/log

# ===== MOVE O JAR PARA O CONTAINER =====
COPY /target/b3-${BALESTECH_SUITE_B3_VERSION}.jar /app/balestech/

# ===== MOVE O STARTUP PARA O CONTAINER =====
COPY startup.sh /app/balestech/startup.sh
RUN chmod +x /app/balestech/startup.sh

# ===== INSTALA DEPENDENCIAS DO SISTEMA =====
#RUN apt update && \
#    apt install -y apt-utils && \
#    apt install -o APT::Immediate-Configure=false -f -y curl && \
#    apt install -y netcat grep procps iproute2 net-tools inetutils-ping telnet lsof


# ===== INSTALA E CONFIGURA O FILEBEAT =====
ENV FILEBEAT_CFG=/etc/filebeat/filebeat.yml
ENV FILEBEAT_VERSION=7.10.0
COPY filebeat.yml /etc/filebeat/filebeat.yml
RUN curl https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-${FILEBEAT_VERSION}-linux-x86_64.tar.gz -o /filebeat.tar.gz && \
    mkdir -p /tmp/filebeat && \
    tar xzvf /filebeat.tar.gz -C /tmp/filebeat && \
    cp /tmp/filebeat/filebeat-*/filebeat /usr/bin/filebeat && \
    rm -rf /tmp/filebeat && \
    chmod go-w /etc/filebeat/filebeat.yml


# ===== EXPÕE A APLICAÇÃO NA PORTA: =====
#EXPOSE 8051


# ===== EXECUTA O PROGRAMA =====
CMD ["/app/balestech/startup.sh"]

##############
# Para criar uma rede para os containers
#################
#
# > docker network create balestech_net


##############
# Comando de build da Imagem
#################
# > docker build -t balestech-suite-b3:1.0 --build-arg BALESTECH_SUITE_B3_VERSION=1.0.0-SNAPSHOT .


##############
# Comando de Run
#################
# > docker run -p 8051:8051 --name b3 --network balestech_net balestech-suite-b3:1.0 


##############
# Acessar o Container
#################
# > docker exec -it b3 /bin/bash


##############
# Para iniciar o filebeat
#################
# > ./usr/bin/filebeat -c /etc/filebeat/filebeat.yml


##############
# Para iniciar ELK
#################
#
# > docker run -p 5601:5601 -p 9200:9200 -p 5044:5044 -it --name elk --network balestech_net sebp/elk:7.10.0


##############
# Comando para permitir o ELK Subir
#################
#
# > sudo sysctl vm.max_map_count=262144


##############
# Limpar containers
#################
#
# sudo sysctl vm.max_map_count=262144
# docker stop balestech_suite_balestech-b3_1 balestech_suite_elk_1 balestech_suite_portainer_1
# docker rm balestech_suite_balestech-b3_1 balestech_suite_elk_1 balestech_suite_portainer_1
# docker rmi balestech_suite_balestech-b3
# docker volume rm balestech_suite_elk-data balestech_suite_portainer-data
# docker-compose up