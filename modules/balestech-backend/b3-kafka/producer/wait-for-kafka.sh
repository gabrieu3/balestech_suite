#!/bin/bash

host="$1"
port="$2"
max_attempts=30
attempt=1
timeout=10

# Esperar até que a conexão com o serviço do Kafka seja bem-sucedida ou atingir o número máximo de tentativas
#until [ $attempt -gt $max_attempts ]; do
#    nc -z "$host" "$port" 2>/dev/null
#    result=$?
#    if [ $result -eq 0 ]; then
#        echo "O serviço do Kafka está saudável e pronto para aceitar conexões."
#        exit 0
#    fi
#
#    echo "Aguardando o serviço do Kafka estar saudável..."
#    sleep "$timeout"
#    attempt=$((attempt + 1))
#done

#until [ $attempt -gt $max_attempts ]; do
#  if wget --spider "$host:$port" 2>/dev/null; then
#    echo "O serviço do Kafka está saudável e pronto para aceitar conexões."
#    exit 0
#  fi
#  sleep "$timeout"
#  attempt=$((attempt + 1))
#  echo "Aguardando a disponibilidade de $host:$port..."
#done
#echo "Não foi possível se conectar ao serviço do Kafka após $max_attempts tentativas."
#exit 1

until [ $attempt -gt $max_attempts ]; do
  if curl -s telnet://${kafka_host}:${kafka_port} | grep "Connected"; then
    echo "O serviço do Kafka está saudável e pronto para aceitar conexões."
    exit 0
  fi

  sleep "$timeout"
  attempt=$((attempt + 1))
  echo "Aguardando a disponibilidade de $host:$port..."
done

echo "Não foi possível se conectar ao serviço do Kafka após $max_attempts tentativas."
exit 1
