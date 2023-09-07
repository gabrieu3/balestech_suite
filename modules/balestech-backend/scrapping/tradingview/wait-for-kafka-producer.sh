#!/bin/sh

#"http://localhost:8200/kafka/actuator/health"
HEALTHCHECK_URL="$1"
EXPECTED_HTTP_STATUS=200
SLEEP_INTERVAL=10  # Intervalo de espera em segundos

echo "Aguardando a disponibilidade de $HEALTHCHECK_URL..."

while true; do
  http_status=$(curl -o /dev/null -w "%{http_code}" -s $HEALTHCHECK_URL)
  if [ "$http_status" -eq $EXPECTED_HTTP_STATUS ]; then
    echo "A aplicação está saudável (HTTP status: $http_status)."
    break
  fi
  sleep $SLEEP_INTERVAL
done

echo "A aplicação está pronta para ser usada."

java -jar tradingview-1.0.0-SNAPSHOT.jar
