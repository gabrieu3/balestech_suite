const { spawnSync } = require('child_process');

const args = [
  'node_modules/next/dist/bin/next',
  'dev',
  '-p',
  '3000',
  '--tls',
  '--https-cert',
  './certificates/localhost.pem',
  '--https-key',
  './certificates/localhost-key.pem',
];

const result = spawnSync(process.execPath, args, { stdio: 'inherit' });

process.exit(result.status);