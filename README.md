# Agendamento
Sistema web de Agendamentos feito em Java usando os seguintes frameworks, API's e outros:

- JPA e Hibernate para persistencia de dados no BD PostgreSQL. 
- Spring Security na segurança de acesso dos usuários.
- C3PO no Pool de Conexões para acesso ao Banco de Dados.
- JSF e Primefaces no front-end.
- Tomcat 8 como container.

Basicamente o sistema é responsável por gerenciar os agendamentos e serviços de uma empresa. O sistema comporta várias empresas aos mesmo tempo, tendo cada uma delas seus funcionários (que são os usuários finais do sistema). Cada usuário tem a visão apenas dos dados cadastrados para a sua empresa, sendo mantido assim, a proteção dos dados de cada empresa cliente do sistema.

Os agendamentos podem estar ligados, ou não, a serviços que foram cadastrados. O sistema também gerencia os pagamentos recebidos pela empresa pelos serviços prestados. A empresa cliente pode cadastrar seus próprios clientes, sendo eles, fisicos ou juridicos.

O sistema também gera relatórios dos serviços (um certo histórico, caso alguma das partes necessite). O sistema também gera um relatório final para a empresa cliente, contendo informações dos agendamentos e serviços marcados, feitos e finalziados. Além dos pagamentos recebidos. Esse relatório é gerado de forma visual (gráficos) e também de forma escrita, podendo ser exportado em PDF.

Por fim, um acesso de Administrador Geral foi feito para controle e gerenciamento das empresas clientes cadastradas no sistema. Somente esse administrador geral tem acesso à essa página e seus dados. Caso um usuário não permitido tente acessar essa página, o mesmo será bloqeuado através das configurações feitas usando o Spring Security.

O deploy do sistema foi feito num servidor na nuvem da Digital Ocean. O sistema foi testado em um ambiente de testes e logo após colocado em produção.
