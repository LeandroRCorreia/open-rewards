import http from 'k6/http';
import { check, sleep } from 'k6';
// A nossa fábrica infinita de UUIDs
import { uuidv4 } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';

export const options = {
  stages: [
    { duration: '10s', target: 200 }, // Sobe pra 200 usuários metralhando
    { duration: '30s', target: 200 }, // Mantém o tiroteio por 30s
    { duration: '10s', target: 0 },   // Desce a poeira
  ],
};

export default function () {
  const url = 'http://localhost:8080/v1/wallets/deposit';

  // O seu JSON exato, mas com a chave sendo gerada dinamicamente a cada milissegundo!
  const payload = JSON.stringify({
    walletId: 6,
    amount: 1000.00,
    description: "Pix para investir em AUPO11 (Teste de Carga k6)",
    idempotencyKey: uuidv4()
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const res = http.post(url, payload, params);

  // Valida se o Spring Boot aguentou o tranco e devolveu sucesso
  check(res, {
    'Deposit successful (200/201)': (r) => r.status === 200 || r.status === 201,
  });

  sleep(0.1);
}