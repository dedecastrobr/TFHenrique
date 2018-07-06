## Escola Técnica Santo Inácio
### Trabalho Final de Banco de Dados e Linguagem de Programação
### Aluno: Henrique Rosa Carvalho
### Turma: 421

## Sumário

> Foi desenvolvido um banco de dados para uma Consultora de produtos da Natura, com o objetivo de facilitar seus registros e consultas a pedidos, produtos e clientes. Para isto, desenvolvi um sistema de menus contendo as opções Clientes, Produtos, Pedidos e Estoque.


### Modelo de Dados:

> Dentro dos menus "Clientes", "Produtos" e "Pedidos", contém 3 subopções: "Cadastrar", "Pesquisar" e "Listar". E na subopção "Pesquisar", de cada um dos 3 menus citados, o usuário pode escolher se quer realizar a pesquisa por código ou por nome. Já dentro do menu "Estoque", há duas opções: "Atualizar Estoque" e "Relatório de Estoque".
  Link: https://github.com/dedecastrobr/TFHenrique/blob/HenriqueCarvalho/VendasNatura/src/natura/Natura.java


### Modelo de Classes:

> Construí 6 classes: "Cliente", com seus atributos e o método createCliente() para inserir seus dados no banco, "Produto", com seus atributos e o método createProduto() para inserir seus dados no banco, "Pedido" da mesma forma, "DBConnection", que realiza a conexão com banco de dados local e possui métodos para deletar e atualizar dados dos clientes, deletar e atualizar dados dos produtos, bem como a quantidade dos mesmos no estoque. A classe "Menu", que lê as opções digitadas pelo usuário, "Natura" que contém os menus e submenus da aplicação, bem como a instância das outras classes para realizar as operações desejadas.
 Links: https://github.com/dedecastrobr/TFHenrique/blob/HenriqueCarvalho/VendasNatura/src/natura/Cliente.java; 
	https://github.com/dedecastrobr/TFHenrique/blob/HenriqueCarvalho/VendasNatura/src/natura/Produto.java; 
	https://github.com/dedecastrobr/TFHenrique/blob/HenriqueCarvalho/VendasNatura/src/natura/Pedido.java; 
	https://github.com/dedecastrobr/TFHenrique/blob/HenriqueCarvalho/VendasNatura/src/natura/DBConnection.java; 
	https://github.com/dedecastrobr/TFHenrique/blob/HenriqueCarvalho/VendasNatura/src/natura/Natura.java; 
	https://github.com/dedecastrobr/TFHenrique/blob/HenriqueCarvalho/VendasNatura/src/natura/Menu.java.


### Conclusão:

> Aprendi muito na prática os conceitos da Linguagem de Programação Java, no que se refere à sua utilização para conexão com o banco de dados. Tive pontos fortes e fracos durante o desenvolvimento do trabalho. Dos pontos fracos, cito as resoluções dos métodos de deletar clientes e produtos, onde encontrei muita dificuldade, porém consegui solucioná-los, e na atualização dos dados dos mesmos (não consegui solucionar a atualização dos produtos). Meu trabalho poderia ter sido diferente no que se refere à manipulação de dados dos Pedidos, a qual não consegui desenvolver completamente.

