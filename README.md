## Uma ferramenta para o ensino da Álgebra Relacional

O armazenamento dos dados é um componente essencial da vida na sociedade moderna. Diariamente, diversas atividades que envolvem alguma interação com um banco de dados ocorrem, tais como um depósito ou saque em uma agencia bancaria, uma reserva em um hotel ou compra de um bilhete de voo, acesso ao catálogo de uma biblioteca virtual ou ainda uma compra online. Essas interações são exemplos do que se pode chamar de aplicações de banco de dados [ELMASRI, NAVATHE, 2010].

O desenvolvimento de um Banco de Dados requer estudos aprofundados na modelagem deste banco, mapeamento, consulta aos dados de um SGBD, a linguagem SQL (Structured Query Language) e o desenvolvimento de aplicações que fazem uso destes dados. [ELMASRI, 2011].


A modelagem dos dados permite que o Banco seja projetado através da definição entidades, relacionamentos e dos atributos. Esta etapa de modelagem é independente do SGBD utilizado, ou seja, pode-se utilizar posteriormente tanto o MySQL, PostgreSQL, Oracle, DB2, SQL Server, Sybase. Diante da modelagem, pode-se mapear este projeto conceitual para um SGBD especifico, criando as tabelas, os atributos, as chaves primárias e estrangeiras, etc. Após a criação física do banco de dados, os dados podem ser inseridos e consultados. 

Atualmente, a principal linguagem de consulta é a SQL [SILBERSCHATZ, 2006]. Através da SQL é possível fazer consultas aos dados armazenados nos SGBD (Sistema Gerenciador de Banco de Dados) e interagir com as aplicações desenvolvidas em diversas linguagens de programação, tanto para Web quanto para Desktop. Porém, o entendimento da linguagem SQL não é trivial, visto se tratar de uma linguagem declarativa. Diversos autores propõem o estudo da Álgebra Relacional e do Cálculo Relacional de Domínio e de Tupla [ELMASRI, 2011] como uma etapa para o entendimento da SQL.

A professora e pesquisadora [Daniela Barreiro Claro](http://formas.ufba.br/dclaro/), da [Universidade Federal da Bahia](https://ufba.br), tem ministrado a disciplina de Banco de Dados desde 2007 para os alunos dos cursos de [Sistemas de Informação](http://wiki.dcc.ufba.br/BSI) e [Ciência da Computação](http://wiki.dcc.ufba.br/CCC) do [Departamento de Ciência da Computação](http://wiki.dcc.ufba.br/DCC) (DCC). No entanto, tendo observado a dificuldade dos alunos de graduação na abstração dos conceitos de Álgebra Relacional e Cálculo Relacional, ela propôs o desenvolvimento de uma ferramenta a fim de que os alunos pudessem escrever em Álgebra Relacional e a ferramenta traduzisse a consulta para SQL e retornasse os dados da consulta.

Assim, a primeira versão do PROGRAMAR foi desenvolvida pelo aluno de graduação Eric Simão. A ferramenta PROGRAMAR como principal objetivo auxiliar no ensino da Álgebra Relacional e do Cálculo Relacional, facilitando o aprendizado do aluno na manipulação das consultas relacionais. Através da ProgramAR, o aluno pode escrever suas expressões em Álgebra Relacional e verificar o resultado das consultas diretamente no banco de dados modelado.

Atualmente, a ferramenta ProgramAR utiliza os operadores da Álgebra Relacional descritos em Elsmasri e Navathe, 2003, com o intuito de atrelar a teoria à prática do ensino visto que este livro é adotado na disciplina Banco de Dados do DCC.

A ferramenta ProgramAR efetua os seguintes passos para a execução de uma consulta com uma expressão em Álgebra Relacional escrita pelo Recebe a expressão em Álgebra Relacional escrita pelo aluno;

Realiza uma análise sintática na expressão, para identificar possíveis erros. Caso algum erro seja identificado, é informado ao aluno o tipo de erro e a posição do erro na expressão;

Caso não tenha sido encontrados erros na análise sintática, a ferramenta faz a conversão da expressão em Álgebra Relacional para SQL; O SQL, gerado na etapa anterior, é executado no banco de dados e o resultado da consulta é enviado ao ProgramAR.

No ano de 2013, foi realizado pelo estudante [Arley Prates](https://github.com/arleyprates) a publicação de um artigo sobre a ferramenta PogramAR no Simpósio Brasileiro de Sistemas de Informação. Para acesso ao artigo [clique aqui](https://www.researchgate.net/profile/Renato_Cerceau/publication/255682815_Conferencia_Eletronica_de_Dados_Cadastrais_Governamentais_por_Criterios_Qualitativos/links/00463520268a21f6a4000000.pdf).

## Tutoriais

#### Instalção do MySQL e Configuração do Banco de Dados

1. Fazer o download do MySQL Server 5.1 no endereço: http://dev.mysql.com/downloads/mysql/5.1.html
2. Fazer o download das ferramentas de trabalho do MySQL no endereço: http://dev.mysql.com/downloads/gui-tools/5.0.html
3. Instalar e Configurar o MySQL Server 5.1
4. Instalar e Configurar o MySQL Tools 5.0

#### Configurando o Banco de Dados
1. Executar a ferramenta MySQL Query Browser
2. Será solicitado as informações de conexão com o servidor MySQL. Preencha os parâmetros e clique em "Ok". Caso o MySQL Server tenha sido instalado na própria máquina, o usuário do banco seja o root e não tenha sido cadastrado senha para este usuário a sua tela de conexão deverá estar preenchida assim: Caso tenha sido cadastrado senha, em "Password" digite a senha cadastrada. Independente da senha, não é necessário colocar um Default Schema.
3. Após a conexão, a tela do MySQL Query Browser será aberta. No lado direito da tela aparecerá uma janela de schemas cadastrados
4. Clique com o botão direito na Janela de Schemas e aparecerá a opção "Create New Schema". Clique nesta opção e informe o nome do Schema desejado. Para este documento foi escolhido o nome COMPANY.
5. Dê um duplo-clique sobre o Schema criado para que o script, a seguir, seja executado sobre este Schema.
6. Após a seleção do Schema deverá ser executado o script de criação do banco de dados e a inserção dos registros. Para isso, deverá ser feito a importação do script ProgramAR-DB.sql, que está em anexo aos procedimentos de instalação da ferramenta. Clique em "Arquivo", depois em "Open Script". Aparecerá uma tela para a seleção do arquivo ProgramAR-DB.sql. Selecione o arquivo e clique em "Abrir". O script deverá aparecer na tela. Clique em "Execute" para que o script seja executado.
7. Após a execução do script, as tabelas deverão estar criadas e os registros devidamente inseridos.

#### Execução da Ferramenta e Criação de Conexão com o Banco

1. Realize um duplo-clique sobre o arquivo.jar da ferramenta para a execução da mesma. Será solicitado um local para salvar a base de dados. Caso esteja utilizando Windows Vista ou superior, o local escolhido não deverá ser o diretório "C:\".
2. Após escolher o local, clique em "Ok" para abrir a ferramenta.
3. Dentro da ferramenta, clique em "Opções" e depois em "Criar Conexão com o Banco de Dados".
4. A tela de criação de conexão abrirá. Preencha os parâmetros de conexão, informados anteriormente para a criação da conexão. Caso tenha sido informada uma senha anteriormente, a mesma deve ser informada novamente aqui.
5. Clique em OK. Caso os parâmetros tenham sido preenchidos corretamente, será visualizada uma mensagem de "Conexão criada com sucesso!".
6. A nova conexão criada aparecerá na tela de Consulta ao Banco de Dados.

Obs.: Outras conexões podem ser criadas da mesma forma e ficarão disponíveis para serem utilizadas.
Após a execução de todos os passos, a ferramenta ProgramAR está pronta para ser utilizada.

#### Exemplos de consultas

Abaixo seguem 5 roteiros para serem seguidos e realizados utilizando a ferramenta ProgramAR - Programa de Álgebra Relacional.

Execute o script ProgramAR-db.sql, um script com a configuração de um esquema para consulta de dados.

1. Recuperar os nomes de todos os empregados do departamento 5 que trabalhem mais de dez horas por semana no projeto 'Produto X'.
2. Liste os nomes de todos os empregados que tenham um dependente com o mesmo primeiro nome que o deles.
3. Encontre os nomes de todos os empregados que são diretamente supervisionados por 'Franklin Wong'.
4. Para cada projeto, liste o nome do projeto e o total de horas por semana (de todos os empregados) gastas no projeto.
5. Recupere o nome do Projeto e os nomes de todos os empregados que trabalhem em pelo menos um projeto.

## Download stable release

* [ProgramAR-Linux32.jar](ProgramAR_-_Linux32.jar)
* [ProgramAR-Windows32.jar](ProgramAR_-_Windows32.jar)
* [ProgramAR-Linux64.jar (Versão lab. UFBA 154)](ProgramAR_-_Linux64.jar)
* [ProgramAR-Windows64.jar](ProgramAR_-_Windows64.jar)

#### Dump do Banco de Dados

* [ProgramAR-db.sql](programAR-db.sql)

## Avaliação

Pensando em melhorar a utilização da ferramenta elaboramos uma ficha de avaliação. Após a utilização nos envie seu feedback. [Clique aqui](https://forms.gle/AeZkvRKLRMwpHTf86) para realizar sua avaliação!

## Quero contribuir

Entre em contato pelo site do [FORMAS](https://github.com/FORMAS/programar).
