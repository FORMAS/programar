### Uma ferramenta para o ensino da Álgebra Relacional

O armazenamento dos dados é um componente essencial da vida na sociedade moderna. Diariamente, diversas atividades que envolvem alguma interação com um banco de dados ocorrem, tais como um depósito ou saque em uma agencia bancaria, uma reserva em um hotel ou compra de um bilhete de voo, acesso ao catálogo de uma biblioteca virtual ou ainda uma compra online. Essas interações são exemplos do que se pode chamar de aplicações de banco de dados [ELMASRI, NAVATHE, 2010].

O desenvolvimento de um Banco de Dados requer estudos aprofundados na modelagem deste banco, mapeamento, consulta aos dados de um SGBD, a linguagem SQL (Structured Query Language) e o desenvolvimento de aplicações que fazem uso destes dados. [ELMASRI, 2011].


A modelagem dos dados permite que o Banco seja projetado através da definição entidades, relacionamentos e dos atributos. Esta etapa de modelagem é independente do SGBD utilizado, ou seja, pode-se utilizar posteriormente tanto o MySQL, PostgreSQL, Oracle, DB2, SQL Server, Sybase. Diante da modelagem, pode-se mapear este projeto conceitual para um SGBD especifico, criando as tabelas, os atributos, as chaves primárias e estrangeiras, etc. Após a criação física do banco de dados, os dados podem ser inseridos e consultados. Atualmente, a principal linguagem de consulta é a SQL [SILBERSCHATZ, 2006]. Através da SQL é possível fazer consultas aos dados armazenados nos SGBD (Sistema Gerenciador de Banco de Dados) e interagir com as aplicações desenvolvidas em diversas linguagens de programação, tanto para Web quanto para Desktop. Porém, o entendimento da linguagem SQL não é trivial, visto se tratar de uma linguagem declarativa. Diversos autores propõem o estudo da Álgebra Relacional e do Cálculo Relacional de Domínio e de Tupla [ELMASRI, 2011] como uma etapa para o entendimento da SQL.

A professora e pesquisadora [Daniela Barreiro Claro](http://formas.ufba.br/dclaro/), da [Universidade Federal da Bahia](https://ufba.br), tem ministrado a disciplina de Banco de Dados desde 2007 para os alunos dos cursos de [Sistemas de Informação](http://wiki.dcc.ufba.br/BSI) e [Ciência da Computação](http://wiki.dcc.ufba.br/CCC) do [Departamento de Ciência da Computação](http://wiki.dcc.ufba.br/DCC) (DCC). No entanto, tendo observado a dificuldade dos alunos de graduação na abstração dos conceitos de Álgebra Relacional e Cálculo Relacional, ela propôs o desenvolvimento de uma ferramenta a fim de que os alunos pudessem escrever em Álgebra Relacional e a ferramenta traduzisse a consulta para SQL e retornasse os dados da consulta.

Assim, a primeira versão do PROGRAMAR foi desenvolvida pelo aluno de graduação Eric Simão. A ferramenta PROGRAMAR como principal objetivo auxiliar no ensino da Álgebra Relacional e do Cálculo Relacional, facilitando o aprendizado do aluno na manipulação das consultas relacionais. Através da ProgramAR, o aluno pode escrever suas expressões em Álgebra Relacional e verificar o resultado das consultas diretamente no banco de dados modelado.

Atualmente, a ferramenta ProgramAR utiliza os operadores da Álgebra Relacional descritos em Elsmasri e Navathe, 2003, com o intuito de atrelar a teoria à prática do ensino visto que este livro é adotado na disciplina Banco de Dados do DCC.

A ferramenta ProgramAR efetua os seguintes passos para a execução de uma consulta com uma expressão em Álgebra Relacional escrita pelo Recebe a expressão em Álgebra Relacional escrita pelo aluno;

Realiza uma análise sintática na expressão, para identificar possíveis erros. Caso algum erro seja identificado, é informado ao aluno o tipo de erro e a posição do erro na expressão;

Caso não tenha sido encontrados erros na análise sintática, a ferramenta faz a conversão da expressão em Álgebra Relacional para SQL; O SQL, gerado na etapa anterior, é executado no banco de dados e o resultado da consulta é enviado ao ProgramAR;
