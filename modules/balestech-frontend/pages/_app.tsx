import '../styles/global.css';

function MyApp({ Component, pageProps }) {
  // Pode adicionar um ThemeProvider aqui, se necessário
  return <Component {...pageProps} />;
}

export default MyApp;
