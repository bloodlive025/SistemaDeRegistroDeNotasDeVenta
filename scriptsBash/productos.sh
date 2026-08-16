TOKEN="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6InZvbGwgbWVkIiwiaWQiOjEsImV4cCI6MTc3OTI2NDk2NH0.Hg2BlwQ4jP5kWsx9vgBFqhz184ESBbANPiLdBC25oi0"

curl -X POST http://localhost:8080/productos \
-H "Authorization: Bearer $TOKEN" \
-H "Content-Type: application/json" \
-d '{"nombre":"Martillo","precio":25.50,"cantidad":15}'

curl -X POST http://localhost:8080/productos \
-H "Authorization: Bearer $TOKEN" \
-H "Content-Type: application/json" \
-d '{"nombre":"Destornillador Plano","precio":12.90,"cantidad":30}'

curl -X POST http://localhost:8080/productos \
-H "Authorization: Bearer $TOKEN" \
-H "Content-Type: application/json" \
-d '{"nombre":"Destornillador Estrella","precio":13.50,"cantidad":25}'

curl -X POST http://localhost:8080/productos \
-H "Authorization: Bearer $TOKEN" \
-H "Content-Type: application/json" \
-d '{"nombre":"Clavos 2 pulgadas","precio":5.50,"cantidad":100}'

curl -X POST http://localhost:8080/productos \
-H "Authorization: Bearer $TOKEN" \
-H "Content-Type: application/json" \
-d '{"nombre":"Tornillos Phillips","precio":7.80,"cantidad":200}'

curl -X POST http://localhost:8080/productos \
-H "Authorization: Bearer $TOKEN" \
-H "Content-Type: application/json" \
-d '{"nombre":"Taladro","precio":180.00,"cantidad":8}'

curl -X POST http://localhost:8080/productos \
-H "Authorization: Bearer $TOKEN" \
-H "Content-Type: application/json" \
-d '{"nombre":"Serrucho","precio":35.00,"cantidad":12}'

curl -X POST http://localhost:8080/productos \
-H "Authorization: Bearer $TOKEN" \
-H "Content-Type: application/json" \
-d '{"nombre":"Llave Inglesa","precio":28.90,"cantidad":18}'

curl -X POST http://localhost:8080/productos \
-H "Authorization: Bearer $TOKEN" \
-H "Content-Type: application/json" \
-d '{"nombre":"Alicate","precio":19.50,"cantidad":22}'

curl -X POST http://localhost:8080/productos \
-H "Authorization: Bearer $TOKEN" \
-H "Content-Type: application/json" \
-d '{"nombre":"Cinta Aislante","precio":4.20,"cantidad":60}'
