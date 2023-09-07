import React, { useEffect, useState } from 'react';
const initKeycloakFunction = require('./keycloak');

// Restante do seu código...


function App() {
  const [authenticated, setAuthenticated] = useState(false);

  useEffect(() => {
    const initKeycloak = async () => {
      console.log("initKeycloak");
      try {
        await initKeycloakFunction({ onLoad: 'login-required', redirectUri: "http://localhost:3000/home" });
        setAuthenticated(true);
      } catch (error) {
        console.error('Erro ao inicializar o Keycloak:', error);
      }
      console.log("fimKeycloak");
    };

    initKeycloak();
  }, []);

  if (!authenticated) {
    return <div>Carregando...</div>;
  }

  return (
    <div>
      Logado...
    </div>
  );
}

export default App;
