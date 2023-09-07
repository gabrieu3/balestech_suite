import React, { useEffect, useState } from 'react';
import Keycloak from 'keycloak-js';

const Login3 = () => {
  const [authenticated, setAuthenticated] = useState(false);

  useEffect(() => {
    const keycloak = new Keycloak({
      url: 'https://localhost:8080/auth', // URL do servidor Keycloak
      realm: 'Balestech', // Nome do realm no Keycloak
      clientId: 'balestech_front', // ID do cliente configurado no Keycloak
    });

    keycloak
      .init({ onLoad: 'check-sso', promiseType: 'native' })
      .then((authenticated) => {
        setAuthenticated(authenticated);
      })
      .catch((error) => {
        console.log('Authentication error:', error);
      });
  }, []);

  const handleLogin = () => {
    window.location.href = 'http://localhost:8080/auth/realms/balestech/balestech_front'; // Redireciona para a página de login do Keycloak
  };

  return (
    <div>
      {authenticated ? (
        <div>
          {/* Conteúdo após a autenticação */}
          <h1>Olá, usuário autenticado!</h1>
        </div>
      ) : (
        <div>
          {/* Conteúdo para o login */}
          <h1>Por favor, faça login:</h1>
          <button onClick={handleLogin}>Login com Keycloak</button>
        </div>
      )}
    </div>
  );
};

export default Login3;
