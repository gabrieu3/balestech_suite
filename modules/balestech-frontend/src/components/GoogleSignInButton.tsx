import React from 'react';
import { Button } from '@mui/material';
import { Google } from '@mui/icons-material';
import { useState, useEffect } from 'react';
import { googleLogout, useGoogleLogin, GoogleOAuthProvider } from '@react-oauth/google';
import axios from 'axios';

const GoogleSignInButton: React.FC = () => {
  return (
    <GoogleOAuthProvider clientId="892826942253-585bp4c27os8r6n9mpk2vtfad5n0od1a.apps.googleusercontent.com">
      <BalestechLogin />
    </GoogleOAuthProvider>
  );
}

function BalestechLogin(){
  const [user, setUser] = useState(null);
  const [profile, setProfile] = useState(null);

  const login = useGoogleLogin({
    onSuccess: (codeResponse) => setUser(codeResponse),
    onError: (error) => console.log('Login Failed:', error),
  });

  useEffect(() => {
    if (user) {
      axios
        .get(`https://www.googleapis.com/oauth2/v1/userinfo?access_token=${user.access_token}`, {
          headers: {
            Authorization: `Bearer ${user.access_token}`,
            Accept: 'application/json',
          },
        })
        .then((res) => {
          setProfile(res.data);
          console.log(res);
        })
        .catch((err) => console.log(err));
        console.log(user);
    }
  }, [user]);

  // log out function to log the user out of google and set the profile array to null
  const logOut = () => {
    googleLogout();
    setProfile(null);
  };

  return (
      /*<div>
        {profile ? (
          <div>
            <img src={profile.picture} alt="user image" />
            <h3>User Logged in</h3>
            <p>Name: {profile.name}</p>
            <p>Email Address: {profile.email}</p>
            <br />
            <br />
            <button onClick={logOut}>Log out</button>
          </div>
        ) : (*/
          <Button
            type="submit"
            fullWidth
            variant="contained"
            startIcon={<Google />}
            sx={{ mt: 2, mb: 2, width:'100%', backgroundColor: 'blueviolet'}}
            onClick={() => {
              login();
            }}>Sign In with Google</Button>
        /*)}
      </div>*/
  );
}

export default GoogleSignInButton;
