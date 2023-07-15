//import '../styles/global.css';
import { AppProps } from 'next/app';
//
import { HelmetProvider } from 'react-helmet-async';
// theme
import ThemeProvider from './theme';
//
import { CssBaseline } from '@mui/material';


/*
const MyApp: React.FC<AppProps> = ({ Component, pageProps }) => {
  return <Component {...pageProps} />;
};

export default MyApp;
*/

function MyApp({ Component, pageProps }: AppProps) {
  return (
  <HelmetProvider>
    <ThemeProvider>
      <CssBaseline />
      <Component {...pageProps} />
    </ThemeProvider>
  </HelmetProvider>
  );
}

export default MyApp;



