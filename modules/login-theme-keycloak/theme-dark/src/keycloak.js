const { createKeycloakAdapter } = require('keycloakify');

const keycloakConfig = {
  realm: 'balestech',
  url: 'http://localhost:8080/auth',
  clientId: 'balestech_login',
};

const keycloak = createKeycloakAdapter(keycloakConfig);

module.exports = keycloak.init; // Exporte apenas o método 'init'
