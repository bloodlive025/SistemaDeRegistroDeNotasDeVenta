curl -X POST http://localhost:8080/usuario \
-H "Content-Type: application/json" \
-d '{"login":"bob","nombre":"Bob","clave":"bob"}'

curl -X POST http://localhost:8080/usuario \
-H "Content-Type: application/json" \
-d '{"login":"user2","nombre":"Usuario Dos","clave":"pass123"}'

curl -X POST http://localhost:8080/usuario \
-H "Content-Type: application/json" \
-d '{"login":"user3","nombre":"Usuario Tres","clave":"pass123"}'

curl -X POST http://localhost:8080/usuario \
-H "Content-Type: application/json" \
-d '{"login":"user4","nombre":"Usuario Cuatro","clave":"pass123"}'

curl -X POST http://localhost:8080/usuario \
-H "Content-Type: application/json" \
-d '{"login":"user5","nombre":"Usuario Cinco","clave":"pass123"}'
