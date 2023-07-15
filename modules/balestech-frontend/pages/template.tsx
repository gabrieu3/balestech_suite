import React from 'react';
import { AppBar, Toolbar, Typography, Container, Grid, Paper } from '@mui/material';

const PageTemplate: React.FC = () => {
  return (
    <div>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6">Balestech Suite</Typography>
        </Toolbar>
      </AppBar>

      <Container maxWidth="lg" style={{ marginTop: '20px' }}>
        <Grid container spacing={3}>
          <Grid item xs={12}>
            
          </Grid>
        </Grid>
      </Container>
    </div>
  );
};

export default PageTemplate;