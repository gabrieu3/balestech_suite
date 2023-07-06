#!/bin/bash

# Iniciar Filebeat em segundo plano
filebeat -c /etc/filebeat/filebeat.yml &

# Iniciar aplicação Java
java -jar app/balestech/b3-1.0.0-SNAPSHOT.jar