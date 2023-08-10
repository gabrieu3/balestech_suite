//
import { HelmetProvider } from 'react-helmet-async';
// theme
import ThemeProvider from './theme';
//
import { CssBaseline } from '@mui/material';
//
import * as React from 'react'
import type { AppProps } from 'next/app'

function MyApp({ Component, pageProps }: AppProps) {
  return (
       <HelmetProvider>
        <ThemeProvider>
            <CssBaseline />
            <Component {...pageProps} />
        </ThemeProvider>
       </HelmetProvider>
  )
}

export default MyApp
