#!/bin/bash

# Inicializa o tópico no Kafka
kafka-topics --create --topic trading_view --bootstrap-server kafka:29092 --partitions 1 --replication-factor 1

# Inicia o Kafka
/etc/confluent/docker/run

