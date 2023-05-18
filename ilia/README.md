# Resultado Desafio Técnico ília

## Descrição
Esta Api foi desenvolvida para solucionar o desafio tecnico da Ilia. Se trata de dois endpoints,
desenvolvidos em Java 17 e Spring, para realizar a entrada de informações de ponto e obter o relatorio
de horas trabalhadas no mês.

Além da aplicação solicitada e dos testes unitários, também foi incluido um exception handler para atender as 
exceções da especificação do problema, além de servir para futuras extensões a outros tratamentos de erro. 
As informações referentes ao ponto ficam armazenadas em um DB h2 e cada execução, o arquivo data.sql popula o banco
com a tabela necessária e alguns pontos de exemplo.

## Como executar
O projeto requer a versão 17 do Java, podendo ser executado via Intellij normalmente. Uma vez subido o servidor,
é necessário fazer as requisições no formato em que foram solicitadas no arquivo. Seguem alguns curls para facilitar
os testes, caso necessário.

## CURLS
### post batida
curl --location 'localhost:8080/v1/batidas' \
--header 'Content-Type: application/json' \
--data '{
"dataHora": "2023-05-22T08:00:00"
}'

### get relatorio
curl --location 'localhost:8080/v1/folhas-de-ponto/2023-05'
