// next.config.js

module.exports = {
    async headers() {
      return [
        {
          source: '/',
          headers: [
            {
              key: 'Content-Security-Policy',
              value: "frame-ancestors 'self' http://localhost:8080",
            },
          ],
        },
      ];
    },
  };
  