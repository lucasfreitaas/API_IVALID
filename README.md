🤖 API de Sincronização de Produtos: Oracle 🔄 Firestore
Esta API RESTful, desenvolvida em Spring Boot, tem como objetivo principal orquestrar a extração, transformação e carga (ETL) de dados de produtos de um sistema legado (Oracle) para um banco de dados NoSQL moderno (Firebase Cloud Firestore).

A aplicação é projetada para ser o gatilho de sincronização de estoque, preços e status entre as duas plataformas.

🌟 Tecnologias Utilizadas

Linguagem: Java 17+
Framework: Spring Boot 3.x
Banco de Dados de Origem (SQL): Oracle Database
Banco de Dados de Destino (NoSQL): Firebase Cloud Firestore
Persistência Oracle: Spring Data JPA / Hibernate (com nativeQuery para JOINs complexos)
Integração Firebase: Firebase Admin SDK for Java
Build Tool: Maven

📂 Estrutura do Projeto (Pacotes)
A organização segue o padrão de arquitetura em camadas, com separação clara de responsabilidades:

.
└── com.example.demo
    ├── config              # Configurações de Conexão (FirebaseConfig, Oracle)
    ├── controller          # TransferenciaController (Endpoints da API)
    ├── service             # TransferenciaService (Lógica de Negócio, Mapeamento, Cálculos)
    └── data_access         # Camada de Acesso a Dados
        ├── oracle          # Acesso ao Oracle (Repository, Model/Entity)
        └── firebase        # Acesso ao Firebase (DTO, Repository Impl)

🛠️ Configuração e Inicialização
Para rodar o projeto localmente, siga os passos abaixo:

1. Configuração do Oracle
Abra o arquivo src/main/resources/application.properties e preencha as credenciais do seu banco de dados Oracle de homologação:

Properties
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.datasource.url=jdbc:oracle:thin:@<SEU_HOST>:<SUA_PORTA>:<SEU_SERVICE_NAME>
spring.datasource.username=<SEU_USUARIO>
spring.datasource.password=<SUA_SENHA>
spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect
spring.jpa.hibernate.ddl-auto=none

2. Configuração do Firebase
3. 
O projeto utiliza um arquivo de credenciais que NÃO ESTÁ NO GITHUB por motivos de segurança (.gitignore).
No Console do Firebase, baixe o arquivo JSON da Conta de Serviço (Admin SDK).
Salve-o na pasta: src/main/resources/.
Nome do arquivo: firebase-service-account.json
(A classe FirebaseConfig.java irá carregar este arquivo automaticamente.)

3. Build e Execução
Utilize o Maven para construir e executar a aplicação:

# 1. Limpa e Compila o projeto
mvn clean install
# 2. Executa a aplicação Spring Boot
mvn spring-boot:run

🚀 Endpoints da API
A sincronização é disparada por um único endpoint via requisição POST.MétodoEndpointDescriçãoPOST/api/v1/transferencia/produtosInicia o processo de sincronização: busca produtos não sincronizados 
no Oracle, mapeia-os, salva no Firestore e marca-os como sincronizados no Oracle.

🔄 Lógica de Sincronização
A API segue as seguintes regras de negócio (implementadas no TransferenciaService):
Busca Otimizada: A consulta no Oracle (ProdutoOracleRepository) usa JOINs nas tabelas PRODUTOS, ESTOQUES e IMAGENS_PRODUTOS.
Filtro de Controle: A consulta só retorna produtos onde o campo SINCRONIZADO_API é igual a 'N'.
Cálculos: Os campos newPrice e percentualDesc são definidos como 0 (lógica inicial). O campo dateVenc é calculado como um timestamp UNIX com base no daysValidity do Oracle.
Marcação: Após o sucesso na escrita no Firestore, o campo SINCRONIZADO_API é atualizado para 'S' no Oracle, garantindo que o produto não seja enviado novamente.

OBS: Projeto em desenvolvimento, novas funções ainda serão implementadas.
