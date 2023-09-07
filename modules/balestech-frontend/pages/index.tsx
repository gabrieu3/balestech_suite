// pages/index.tsx (ou qualquer outra página que você deseja proteger)

import { useRouter } from 'next/router';
import { useEffect } from 'react';
import {isUserLoggedIn} from '../src/auth2/Authentication'

const HomePage: React.FC = () => {
  const router = useRouter();
 
  useEffect(() => {
    const checkLoggedIn = async () => {
      // Verifica se o usuário está logado
      const isLoggedIn: boolean = await isUserLoggedIn(); // Substitua por sua lógica de verificação de login

      if (isLoggedIn) {
        // Redireciona para a página inicial
        router.push('/home');
      } else {
        // Redireciona para a página de login
        router.push('/login');
      }
    };

    checkLoggedIn();
  }, []);

  return <div>Página em processo de redirecionamento...</div>;
};

export default HomePage;
