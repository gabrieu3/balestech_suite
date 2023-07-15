import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import Stack from '@mui/material/Stack';
import GoogleSignInButton from "../src/components/GoogleSignInButton"

export default function SignIn() {
  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      email: data.get("email"),
      password: data.get("password"),
    });
  };

  return (
    <Box
    
    sx={{
      width: '100%',
      height: '100%',
    }}>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6">Balestech Suite</Typography>
        </Toolbar>
      </AppBar>

      <Container component="main" maxWidth="sm">
          <Box
              sx={{
                boxShadow: 3,
                backgroundColor: '#FFF',
                borderRadius: 2,
                px: 4,
                py: 6,
                marginTop: 8,
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
              }}
          >
              <Typography component="h1" variant="h5">
                  Balestech Sign In
              </Typography>
              <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
                  <TextField
                    margin="normal"
                    required
                    fullWidth
                    id="email"
                    label="Email Address"
                    name="email"
                    autoComplete="email"
                    autoFocus
                  />
                  <TextField
                    margin="normal"
                    required
                    fullWidth
                    name="password"
                    label="Password"
                    type="password"
                    id="password"
                    autoComplete="current-password"
                  />

                  <FormControlLabel
                    control={<Checkbox value="remember" color="primary" />}
                    label="Remember me"
                  />

                  <Box sx={{ width: '100%' }}>

                    <Stack spacing={2} direction="row" useFlexGap>
                      <Button type="submit" fullWidth variant="contained" sx={{ mt: 2, mb: 2}}>Sign In</Button>
                      <GoogleSignInButton />
                    </Stack>
                      
                  </Box>
                  
                  <Grid container>
                    <Grid item xs>
                      <Link href="#" variant="body2">
                        Forgot password?
                      </Link>
                    </Grid>
                    <Grid item>
                      <Link href="#" variant="body2">
                        {"Don't have an account? Sign Up"}
                      </Link>
                    </Grid>
                  </Grid>
              </Box>
          </Box>
      </Container>
    </Box>
  );
}