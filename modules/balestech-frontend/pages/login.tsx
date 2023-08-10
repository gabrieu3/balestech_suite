import { useEffect } from 'react';
import Keycloak from 'keycloak-js';

let keycloak;

if (typeof window !== 'undefined') {
  keycloak = new Keycloak({
    realm: 'balestech',
    url: 'http://localhost:8080/auth',
    clientId: 'balestech_login',
  });
}

export default function LoginPage() {

  useEffect(() => {
    if (keycloak) {
      const initKeycloak = async () => {
        console.log("initKeycloak");

        try {
          await keycloak.init({onLoad: 'login-required', redirectUri: "http://localhost:3000/home"});
        } catch (error) {
          console.error('Erro ao inicializar o Keycloak:', error);
        }
        console.log("fimKeycloak");
      };
  
      initKeycloak();
    }
  }, []); 
  
  return (<title> Login | Balestech Suite </title>);
}