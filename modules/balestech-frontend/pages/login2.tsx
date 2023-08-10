import { useEffect } from 'react';
import { Helmet } from 'react-helmet-async';
// @mui
import { styled } from '@mui/material/styles';
import { Link, Container, Typography, Divider, Stack, Button } from '@mui/material';
import Box from "@mui/material/Box";
// hooks
import useResponsive from '../hooks/useResponsive';

// components
import Iconify from '../components/iconify';
// sections
/*import { LoginForm } from '../sections/auth/login';*/
//
import Logo from '../components/logo';
import GoogleSignInButton from "../components/GoogleSignInButton";

import Keycloak from 'keycloak-js';

const keycloak = new Keycloak({
  realm: 'Balestech',
  url: 'http://localhost:8080/auth',
  clientId: 'balestech_login',
});


// ----------------------------------------------------------------------

const StyledRoot = styled('div')(({ theme }) => ({
  [theme.breakpoints.up('md')]: {
    display: 'flex',
  },
}));

const StyledContent = styled('div')(({ theme }) => ({
  maxWidth: 480,
  margin: 'auto',
  minHeight: '100vh',
  display: 'flex',
  justifyContent: 'center',
  flexDirection: 'column',
  padding: theme.spacing(0, 0),

}));


// ----------------------------------------------------------------------

export default function LoginPage() {
  const mdUp = useResponsive('up', 'md');

  useEffect(() => {
      const initKeycloak = async () => {
        console.log("initKeycloak");
        try {
          await keycloak.init({
            onLoad: 'login-required',
          });
  
          // Autenticação bem-sucedida, o usuário está logado no Keycloak.
          // Você pode redirecionar para a tela principal do seu aplicativo ou fazer outras ações.
  
        } catch (error) {
          console.error('Erro ao inicializar o Keycloak:', error);
        }
      };
  
      initKeycloak();
  }, []); // O array vazio indica que o useEffect será executado apenas uma vez após a montagem do componente
  
  
  return (
    <>
      <Helmet>
        <title> Login | Balestech Suite </title>
      </Helmet>

      <StyledRoot>
      
      
        <Container maxWidth="sm">
          <StyledContent>
          <Box
              sx={{
                boxShadow: 20,
                borderRadius: 2,
                px: 4,
                py: 6,
              }}
          >
            <Typography align='center' variant="h4" gutterBottom >
              Sign in to  <Logo/>alestech Suite  
            </Typography>
            

            <Typography variant="body2" sx={{ mb: 5 }}>
              Don’t have an account? {''}
              <Link variant="subtitle2">Get started</Link>
            </Typography>

            <GoogleSignInButton />

            <Divider sx={{ my: 3 }}>
              <Typography variant="body2" sx={{ color: 'text.secondary' }}>
                OR
              </Typography>
            </Divider>

            {/*<LoginForm />*/}
            </Box>
          </StyledContent>
        </Container>
        
      </StyledRoot>
    </>
  );
}






