# SPG30 (Sistema de Ponto Grupo 30)

SPG30 é o nosso sistema de registro de ponto de funcionários.

O SPG30 é composto por dois micro serviços, um para autenticação do usuário(spg30-user) e outro responsável pelo registro, visualização de registros e geração de relatórios (spg30-backend). 

![Solução](imagens/solucao.png)

Esses dois serviços serão responsáveis por logar o usuário no sistema e por registrar os horários de trabalho. 
Existem 4 tipos de registros de ponto.
* Primeira marcação - É o registro de entrada, é o horário em que se inicia a jornada de trabalho.
* Segunda marcação  - É a saída para o almoço ou no caso dos estagiários é o ponto que registra o final da jornada.
* Terceira marcação - É a volta do almoço. Para evitar questões trabalhistas é preciso que entre a segunda e a terceira marcação exista um intervalo de pelo menos 1 hora. 
* Quarta marcação - É a marcação que registra o final da jornada. 

Além de permitir o registro de pontos a aplicação é responsável pelo acompanhamento dos pontos e pela geração de um relatório de registros de pontos. 

Segue desenho dos processo de solução. 

![Solução](imagens/processo.png)

# Escolha da tecnologia. 

Visando a agilidade tanto na criação da infra quanto do código optamos por utilizar a linguagem Java pois é uma linguagem de conhecimento de todo o time e optamos pelo framework Quarkus. 

Para saber mais sobre o framework acesse o site: https://quarkus.io/ .

# Linguagem Ubíqua 

Aqui nós definiremos os conceitos importantes para entender a nossa aplicação.

| Nome | Descrição |
|------| --------- | 
| Marcar ponto/Registrar ponto | Registrar o horário de entrada/saída do trabalho |
| Usuário | Pessoa que trabalha e precisa registrar o horário que entra e sai do trabalho |
| Visualizar registro | Obter informações sobre um usuário em um determinado dia |
| Gerar relatório | Obter dados  |
| Notificação | Mensagem recebida por sms ou e-mail lembrando que é preciso registrar o ponto |
| Usuário Administrador | Usuário com acessos especiais para poder gerar relatórios com pontos de outros usuários e aprovar alterações de ponto |

# Fase 2 do projeto

Para a fase 2 serão implementadas algumas novas funcionalidades e outras irão sofrer algumas alterações: 
* Edição de registro - A API responsável pelos registros irá possuir um novo endpoint para permitir a atualização dos dados salvos. A edição do ponto deve ser aprovada por um usuário administrador
* Utilização do cognito para gerenciar os acessos dos usuários. Além disso, haverá a criação de um novo tipo de usuário, o administrador, que poderá visualizar todos os registros de ponto, independente de quem o registrou, além disso, ele poderá extrair um relatório gerencial .
* No endpoint de relatório, caso voce seja administrador, poderá acessar um relatório consolidado de todos os registros de pontos feitos em uma determinada data. 
* Será criado um endpoint de notificação responsável por lembrar aos funcionários que eles precisam realizar o registro do seu ponto.