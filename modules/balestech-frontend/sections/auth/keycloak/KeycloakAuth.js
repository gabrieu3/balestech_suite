import Keycloak from 'keycloak-js';

const keycloakConfig = {
  url: 'http://localhost:8080',
  realm: 'Balestech',
  clientId: 'balestech_login',
};

const keycloak = new Keycloak(keycloakConfig);

export default keycloak;
