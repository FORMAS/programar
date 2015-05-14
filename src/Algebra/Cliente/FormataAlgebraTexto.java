package Algebra.Cliente;

//Referenced classes of package Algebra.Cliente:
//ParametroAlgebraTexto

public class FormataAlgebraTexto
{

	public FormataAlgebraTexto(String parametro)
	{
		espaco = 32;
		parentese_abre = 40;
		parentese_fecha = 41;
		identificador = -1;
		quebralinha = 13;
		fimexpressao = false;
		fimtodasexpressoes = false;
		fimresultado = false;
		expressao = new String();
		schema = new String();
		resultado = new String();
		counter = -1;
		parenteses_counter = 0;
		msgerro = new String("");
		predicado = new String[100];
		atributo = new String[100][100][100];
		renomear_atributo = new String[100][100][100];
		renomear_relacao = new String[100][100];
		relacao = new String[100][100];
		condicoes_de_join = new String[100][100];
		group_by = new String[100];
		operadorbinario = new int[100][100];
		cont_atributo = 0;
		cont_renomear_atributo = 0;
		cont_renomear_relacao = 0;
		cont_relacao = 0;
		cont_operador_binario = 0;
		pode_ter_recursao = true;
		parametroalgebratexto = new ParametroAlgebraTexto[100];
		expressao = parametro;
		if(expressao.length() == 0)
			return;
		while(!fimtodasexpressoes) 
		{
			fimexpressao = false;
			verifica_expressao();
			if(!fimtodasexpressoes)
				setparametroalgebratexto("\n", "normal");
		}
	}

	public void verifica_expressao()
	{
		int valchar = 0;
		for(pode_ter_recursao = true; !fimexpressao; pode_ter_recursao = false)
		{
			valchar = getchar();
			if(fimexpressao)
				return;
			if(verificaoperadoresunarios(valchar))
				operadorunario(valchar);
			else
				if(verificaletra(valchar) | verificanumeros(valchar))
					relacao(valchar);
				else
					if(verificaparenteseabre(valchar))
					{
						setparametroalgebratexto("(", "normal");
						parenteses_counter++;
					} else
						if(valchar == 922)
							agregacao("", valchar);
						else
							if(verificaespaco(valchar))
							{
								setparametroalgebratexto(" ", "normal");
							} else
							{
								parametroerro(valchar);
								return;
							}
		}

	}

	public void setparametroalgebratexto(String texto, String tipo)
	{
		setidentificador();
		parametroalgebratexto[getidentificador()] = new ParametroAlgebraTexto();
		parametroalgebratexto[getidentificador()].settexto(texto);
		parametroalgebratexto[getidentificador()].settipo(tipo);
	}

	public ParametroAlgebraTexto getparametro(int i)
	{
		return parametroalgebratexto[i];
	}

	public int gettamanhoparametro()
	{
		int i;
		for(i = 0; i < parametroalgebratexto.length; i++)
			if(parametroalgebratexto[i] == null)
				return i;

		return i;
	}

	public void parametroerro(int valchar)
	{
		String parametroerro = new String();
		while(!fimexpressao) 
		{
			parametroerro = parametroerro + (new Character((char)valchar)).toString();
			valchar = getchar();
		}
		setparametroalgebratexto(parametroerro, "erro");
	}

	public void operadorunario(int valchar)
	{
		switch(valchar)
		{
		case 910: 
			setparametroalgebratexto((new Character('\u038E')).toString(), "operador");
			selecao();
			break;

		case 911: 
			setparametroalgebratexto((new Character('\u038F')).toString(), "operador");
			projecao();
			break;

		case 920: 
			setparametroalgebratexto((new Character('\u0398')).toString(), "operador");
			renomear();
			break;
		}
	}

	public void operadorbinario(int valchar)
	{
		cont_relacao++;
		switch(valchar)
		{
		case 912: 
			setparametroalgebratexto((new Character('\u0390')).toString(), "operador");
			break;

		case 913: 
			setparametroalgebratexto((new Character('\u0391')).toString(), "operador");
			break;

		case 914: 
			setparametroalgebratexto((new Character('\u0392')).toString(), "operador");
			break;

		case 915: 
			setparametroalgebratexto((new Character('\u0393')).toString(), "operador");
			break;

		case 916: 
			setparametroalgebratexto((new Character('\u0394')).toString(), "operador");
			join();
			break;

		case 917: 
			setparametroalgebratexto((new Character('\u0395')).toString(), "operador");
			outerjointotal();
			break;

		case 918: 
			setparametroalgebratexto((new Character('\u0396')).toString(), "operador");
			outerjoinesquerda();
			break;

		case 919: 
			setparametroalgebratexto((new Character('\u0397')).toString(), "operador");
			outerjoindireita();
			break;

		case 921: 
			setparametroalgebratexto((new Character('\u0399')).toString(), "operador");
			break;

		case 923: 
			setparametroalgebratexto((new Character('\u039B')).toString(), "operador");
			break;
		}
	}

	public void join()
	{
		verificacondicoes("Join");
	}

	public void outerjointotal()
	{
		verificacondicoes("Outer Join Total");
	}

	public void outerjoinesquerda()
	{
		verificacondicoes("Outer Join Esquerda");
	}

	public void outerjoindireita()
	{
		verificacondicoes("Outer Join Direita");
	}

	public void verificacondicoes(String origem)
	{
		int valchar = 0;
		int parenteses = 0;
		int local_parenteses_abre = 0;
		int posicao = 0;
		boolean sair = false;
		String verifica_condicoes_aux = new String();
		while(!sair) 
		{
			valchar = getchar();
			if(fimexpressao)
			{
				if(verifica_condicoes_aux.length() != 0)
					setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
				return;
			}
			if(verificaespaco(valchar))
				while(verificaespaco(valchar)) 
				{
					verifica_condicoes_aux = verifica_condicoes_aux + expressao.charAt(counter);
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
						return;
					}
				}
			if(verificaparenteseabre(valchar) | verificaespaco(valchar) | verificaletra(valchar) | verificanumeros(valchar))
			{
				while(verificaparenteseabre(valchar) | verificaespaco(valchar)) 
				{
					if(verificaparenteseabre(valchar))
						parenteses++;
					verifica_condicoes_aux = verifica_condicoes_aux + expressao.charAt(counter);
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
						return;
					}
				}
				if(verificaletra(valchar) | verificanumeros(valchar))
				{
					while(verificaletra(valchar) | verificanumeros(valchar)) 
					{
						verifica_condicoes_aux = verifica_condicoes_aux + expressao.charAt(counter);
						valchar = getchar();
						if(fimexpressao)
						{
							setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
							return;
						}
					}
				} else
				{
					if(verifica_condicoes_aux.length() != 0)
						setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
					parametroerro(valchar);
					return;
				}
			} else
			{
				if(verifica_condicoes_aux.length() != 0)
					setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
				parametroerro(valchar);
				return;
			}
			while(verificaespaco(valchar)) 
			{
				verifica_condicoes_aux = verifica_condicoes_aux + expressao.charAt(counter);
				valchar = getchar();
				if(fimexpressao)
				{
					setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
					return;
				}
			}
			if(verificacomparacao(valchar))
			{
				verifica_condicoes_aux = verifica_condicoes_aux + expressao.charAt(counter);
				switch(valchar)
				{
				case 60: // '<'
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
						return;
					}
					if((valchar == 61) | (valchar == 62))
						verifica_condicoes_aux = verifica_condicoes_aux + expressao.charAt(counter);
					else
						if(verificaespaco(valchar) | verificaletra(valchar) | verificanumeros(valchar))
						{
							counter--;
						} else
						{
							if(verifica_condicoes_aux.length() != 0)
								setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
							parametroerro(valchar);
							return;
						}
					break;

				case 62: // '>'
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
						return;
					}
					if(valchar == 61)
						verifica_condicoes_aux = verifica_condicoes_aux + expressao.charAt(counter);
					else
						if(verificaespaco(valchar) | verificaletra(valchar) | verificanumeros(valchar))
						{
							counter--;
						} else
						{
							if(verifica_condicoes_aux.length() != 0)
								setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
							parametroerro(valchar);
							return;
						}
					break;
				}
			} else
			{
				if(verifica_condicoes_aux.length() != 0)
					setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
				parametroerro(valchar);
				return;
			}
			valchar = getchar();
			if(fimexpressao)
			{
				setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
				return;
			}
			while(verificaespaco(valchar)) 
			{
				verifica_condicoes_aux = verifica_condicoes_aux + expressao.charAt(counter);
				valchar = getchar();
				if(fimexpressao)
				{
					setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
					return;
				}
			}
			if(verificaletra(valchar) | verificanumeros(valchar))
			{
				while(verificaletra(valchar) | verificanumeros(valchar)) 
				{
					verifica_condicoes_aux = verifica_condicoes_aux + expressao.charAt(counter);
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
						return;
					}
				}
				counter--;
			} else
			{
				if(verifica_condicoes_aux.length() != 0)
					setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
				parametroerro(valchar);
				return;
			}
			valchar = getchar();
			if(fimexpressao)
			{
				setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
				return;
			}
			while(verificaparentesefecha(valchar) | verificaespaco(valchar)) 
			{
				verifica_condicoes_aux = verifica_condicoes_aux + expressao.charAt(counter);
				if(verificaparentesefecha(valchar))
					parenteses--;
				valchar = getchar();
				if(fimexpressao)
				{
					setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
					return;
				}
			}
			posicao = counter - 1;
			if((valchar == 65) | (valchar == 97))
			{
				valchar = getchar();
				if(fimexpressao)
				{
					verifica_condicoes_aux = verifica_condicoes_aux + "A";
					setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
					return;
				}
				if((valchar == 110) | (valchar == 78))
				{
					valchar = getchar();
					if(fimexpressao)
					{
						verifica_condicoes_aux = verifica_condicoes_aux + "AN";
						setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
						return;
					}
					if((valchar == 100) | (valchar == 68))
					{
						valchar = getchar();
						if(fimexpressao)
						{
							verifica_condicoes_aux = verifica_condicoes_aux + "AND";
							setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
							return;
						}
						if(verificaespaco(valchar))
						{
							verifica_condicoes_aux = verifica_condicoes_aux + "AND ";
						} else
						{
							if(verificaletra(valchar) | verificanumeros(valchar))
							{
								counter -= 4;
								return;
							}
							if(verifica_condicoes_aux.length() != 0)
								setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
							parametroerro(valchar);
							return;
						}
					} else
					{
						setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
						counter -= 3;
						return;
					}
				} else
				{
					setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
					counter -= 2;
					return;
				}
			} else
			{
				setparametroalgebratexto(verifica_condicoes_aux, "sobrescrito");
				counter--;
				sair = true;
			}
		}
	}

	public void renomear()
	{
		int valchar = 0;
		int local_parenteses_abre = 0;
		boolean sair = false;
		String relacao_aux = "";
		String renomear_aux = "";
		boolean renomear_relacao = false;
		valchar = getchar();
		if(fimexpressao)
			return;
		while(verificaespaco(valchar)) 
		{
			renomear_aux = renomear_aux + expressao.charAt(counter);
			valchar = getchar();
			if(fimexpressao)
			{
				setparametroalgebratexto(renomear_aux, "normal");
				return;
			}
		}
		if(renomear_aux.length() != 0)
			setparametroalgebratexto(renomear_aux, "normal");
		if((!verificaletra(valchar)) & (!verificanumeros(valchar)) & (!verificaparenteseabre(valchar)))
		{
			parametroerro(valchar);
			return;
		}
		if(verificaletra(valchar) | verificanumeros(valchar))
		{
			while(verificaletra(valchar) | verificanumeros(valchar)) 
			{
				relacao_aux = relacao_aux + expressao.charAt(counter);
				renomear_relacao = true;
				valchar = getchar();
				if(fimexpressao)
				{
					setparametroalgebratexto(relacao_aux, "renomear_relacao");
					return;
				}
			}
			setparametroalgebratexto(relacao_aux, "renomear_relacao");
		}
		renomear_aux = "";
		while(verificaespaco(valchar)) 
		{
			renomear_aux = renomear_aux + expressao.charAt(counter);
			valchar = getchar();
			if(fimexpressao)
			{
				setparametroalgebratexto(renomear_aux, "normal");
				return;
			}
		}
		if(renomear_aux.length() != 0)
			setparametroalgebratexto(renomear_aux, "normal");
		renomear_aux = "";
		if(verificaparenteseabre(valchar))
		{
			local_parenteses_abre = counter;
			while(!sair) 
			{
				valchar = getchar();
				if(fimexpressao)
				{
					counter = local_parenteses_abre - 1;
					fimexpressao = false;
					return;
				}
				if(verificaoperadoresbinarios(valchar) | verificaoperadoresunarios(valchar))
				{
					counter = local_parenteses_abre - 1;
					fimexpressao = false;
					return;
				}
				if(verificaparentesefecha(valchar))
				{
					valchar = getchar();
					if(fimexpressao)
					{
						counter = local_parenteses_abre - 1;
						fimexpressao = false;
						return;
					}
					while(verificaespaco(valchar)) 
					{
						valchar = getchar();
						if(fimexpressao)
						{
							counter = local_parenteses_abre - 1;
							fimexpressao = false;
							return;
						}
					}
					if(verificaparenteseabre(valchar))
					{
						counter = local_parenteses_abre;
						sair = true;
					} else
					{
						counter = local_parenteses_abre - 1;
						fimexpressao = false;
						return;
					}
				} else
					if(valchar == 44)
					{
						counter = local_parenteses_abre;
						sair = true;
					} else
						if(!(verificaletra(valchar) | verificanumeros(valchar)))
						{
							counter = local_parenteses_abre - 1;
							fimexpressao = false;
							return;
						}
			}
		} else
		{
			parametroerro(valchar);
			return;
		}
		renomear_aux = renomear_aux + "(";
		for(sair = false; !sair;)
		{
			valchar = getchar();
			if(fimexpressao)
			{
				setparametroalgebratexto(renomear_aux, "sobrescrito");
				return;
			}
			while(verificaespaco(valchar)) 
			{
				renomear_aux = renomear_aux + expressao.charAt(counter);
				valchar = getchar();
				if(fimexpressao)
				{
					setparametroalgebratexto(renomear_aux, "sobrescrito");
					return;
				}
			}
			if(verificaletra(valchar) | verificanumeros(valchar))
			{
				while(verificaletra(valchar) | verificanumeros(valchar)) 
				{
					renomear_aux = renomear_aux + expressao.charAt(counter);
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(renomear_aux, "sobrescrito");
						return;
					}
				}
				while(verificaespaco(valchar)) 
				{
					renomear_aux = renomear_aux + expressao.charAt(counter);
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(renomear_aux, "sobrescrito");
						return;
					}
				}
				if(valchar == 44)
					renomear_aux = renomear_aux + expressao.charAt(counter);
				else
					if(verificaparentesefecha(valchar))
					{
						renomear_aux = renomear_aux + expressao.charAt(counter);
						setparametroalgebratexto(renomear_aux, "sobrescrito");
						sair = true;
					} else
					{
						setparametroalgebratexto(renomear_aux, "sobrescrito");
						parametroerro(valchar);
						return;
					}
			} else
			{
				setparametroalgebratexto(renomear_aux, "sobrescrito");
				parametroerro(valchar);
				return;
			}
		}

	}

	public void projecao()
	{
		int valchar = 0;
		boolean sair = false;
		String projecao_aux = "";
		valchar = getchar();
		if(fimexpressao)
			return;
		while(verificaespaco(valchar)) 
		{
			projecao_aux = projecao_aux + expressao.charAt(counter);
			valchar = getchar();
			if(fimexpressao)
			{
				setparametroalgebratexto(projecao_aux, "sobrescrito");
				return;
			}
		}
		while(!sair) 
			if(verificanumeros(valchar) | verificaletra(valchar))
			{
				while(verificanumeros(valchar) | verificaletra(valchar)) 
				{
					projecao_aux = projecao_aux + expressao.charAt(counter);
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(projecao_aux, "sobrescrito");
						return;
					}
				}
				while(verificaespaco(valchar)) 
				{
					projecao_aux = projecao_aux + expressao.charAt(counter);
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(projecao_aux, "sobrescrito");
						return;
					}
				}
				if(verificaparenteseabre(valchar))
				{
					counter--;
					setparametroalgebratexto(projecao_aux, "sobrescrito");
					return;
				}
				if(valchar == 44)
				{
					projecao_aux = projecao_aux + expressao.charAt(counter);
					sair = false;
				} else
				{
					setparametroalgebratexto(projecao_aux, "sobrescrito");
					parametroerro(valchar);
					return;
				}
				valchar = getchar();
				if(fimexpressao)
				{
					setparametroalgebratexto(projecao_aux, "sobrescrito");
					return;
				}
				while(verificaespaco(valchar)) 
				{
					projecao_aux = projecao_aux + expressao.charAt(counter);
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(projecao_aux, "sobrescrito");
						return;
					}
				}
			} else
			{
				setparametroalgebratexto(projecao_aux, "sobrescrito");
				parametroerro(valchar);
				return;
			}
	}

	public void selecao()
	{
		int valchar = 0;
		int parenteses = 0;
		int local_parenteses_abre = 0;
		int posicao = 0;
		int qtd_char = 0;
		boolean sair = false;
		String selecao_aux = new String();
		while(!sair) 
		{
			valchar = getchar();
			if(fimexpressao)
			{
				if(selecao_aux.length() != 0)
					setparametroalgebratexto(selecao_aux, "sobrescrito");
				return;
			}
			if(verificaespaco(valchar))
				while(verificaespaco(valchar)) 
				{
					selecao_aux = selecao_aux + expressao.charAt(counter);
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(selecao_aux, "sobrescrito");
						return;
					}
				}
			if(verificaparenteseabre(valchar))
			{
				local_parenteses_abre = counter;
				while(!sair) 
				{
					valchar = getchar();
					if(fimexpressao)
					{
						counter = local_parenteses_abre - 1;
						fimexpressao = false;
						if(selecao_aux.length() != 0)
							setparametroalgebratexto(selecao_aux, "sobrescrito");
						return;
					}
					if(verificaoperadoresbinarios(valchar) | verificaoperadoresunarios(valchar))
					{
						counter = local_parenteses_abre - 1;
						if(selecao_aux.length() != 0)
							setparametroalgebratexto(selecao_aux, "sobrescrito");
						return;
					}
					if(verificaparentesefecha(valchar))
					{
						valchar = getchar();
						if(fimexpressao)
						{
							counter = local_parenteses_abre - 1;
							fimexpressao = false;
							if(selecao_aux.length() != 0)
								setparametroalgebratexto(selecao_aux, "sobrescrito");
							return;
						}
						while(verificaespaco(valchar)) 
						{
							valchar = getchar();
							if(fimexpressao)
							{
								counter = local_parenteses_abre - 1;
								fimexpressao = false;
								if(selecao_aux.length() != 0)
									setparametroalgebratexto(selecao_aux, "sobrescrito");
								return;
							}
						}
						if(verificaparenteseabre(valchar))
						{
							counter = local_parenteses_abre - 1;
							sair = true;
						} else
						{
							counter = local_parenteses_abre - 1;
							if(selecao_aux.length() != 0)
								setparametroalgebratexto(selecao_aux, "sobrescrito");
							return;
						}
					}
					if(verificacomparacao(valchar))
					{
						counter = local_parenteses_abre - 1;
						sair = true;
					}
				}
			} else
			{
				counter--;
			}
			sair = false;
			valchar = getchar();
			if(fimexpressao)
			{
				if(selecao_aux.length() != 0)
					setparametroalgebratexto(selecao_aux, "sobrescrito");
				return;
			}
			if(verificaparenteseabre(valchar) | verificaespaco(valchar) | verificaletra(valchar) | verificanumeros(valchar))
			{
				while(verificaparenteseabre(valchar) | verificaespaco(valchar)) 
				{
					if(verificaparenteseabre(valchar))
						parenteses++;
					selecao_aux = selecao_aux + expressao.charAt(counter);
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(selecao_aux, "sobrescrito");
						return;
					}
				}
				if(verificaletra(valchar) | verificanumeros(valchar))
				{
					while(verificaletra(valchar) | verificanumeros(valchar)) 
					{
						selecao_aux = selecao_aux + expressao.charAt(counter);
						valchar = getchar();
						if(fimexpressao)
						{
							setparametroalgebratexto(selecao_aux, "sobrescrito");
							return;
						}
					}
				} else
				{
					if(selecao_aux.length() != 0)
						setparametroalgebratexto(selecao_aux, "sobrescrito");
					parametroerro(valchar);
					return;
				}
			} else
			{
				if(selecao_aux.length() != 0)
					setparametroalgebratexto(selecao_aux, "sobrescrito");
				parametroerro(valchar);
				return;
			}
			while(verificaespaco(valchar)) 
			{
				selecao_aux = selecao_aux + expressao.charAt(counter);
				valchar = getchar();
				if(fimexpressao)
				{
					setparametroalgebratexto(selecao_aux, "sobrescrito");
					return;
				}
			}
			if(verificacomparacao(valchar))
			{
				selecao_aux = selecao_aux + expressao.charAt(counter);
				switch(valchar)
				{
				case 60: // '<'
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(selecao_aux, "sobrescrito");
						return;
					}
					if((valchar == 61) | (valchar == 62))
						selecao_aux = selecao_aux + expressao.charAt(counter);
					else
						if(verificaespaco(valchar) | verificaletra(valchar) | verificanumeros(valchar))
						{
							counter--;
						} else
						{
							setparametroalgebratexto(selecao_aux, "sobrescrito");
							parametroerro(valchar);
							return;
						}
					break;

				case 62: // '>'
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(selecao_aux, "sobrescrito");
						return;
					}
					if(valchar == 61)
						selecao_aux = selecao_aux + expressao.charAt(counter);
					else
						if(verificaespaco(valchar) | verificaletra(valchar) | verificanumeros(valchar))
						{
							counter--;
						} else
						{
							setparametroalgebratexto(selecao_aux, "sobrescrito");
							parametroerro(valchar);
							return;
						}
					break;
				}
			} else
			{
				setparametroalgebratexto(selecao_aux, "sobrescrito");
				parametroerro(valchar);
				return;
			}
			valchar = getchar();
			if(fimexpressao)
			{
				setparametroalgebratexto(selecao_aux, "sobrescrito");
				return;
			}
			while(verificaespaco(valchar)) 
			{
				selecao_aux = selecao_aux + expressao.charAt(counter);
				valchar = getchar();
				if(fimexpressao)
				{
					setparametroalgebratexto(selecao_aux, "sobrescrito");
					return;
				}
			}
			if(valchar == 34)
			{
				selecao_aux = selecao_aux + expressao.charAt(counter);
				for(sair = false; !sair;)
				{
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(selecao_aux, "sobrescrito");
						return;
					}
					if(verificaletra(valchar) | verificanumeros(valchar) | verificaespaco(valchar))
						selecao_aux = selecao_aux + expressao.charAt(counter);
					else
						if(valchar == 34)
						{
							selecao_aux = selecao_aux + expressao.charAt(counter);
							sair = true;
						} else
						{
							setparametroalgebratexto(selecao_aux, "sobrescrito");
							parametroerro(valchar);
							return;
						}
				}

				sair = false;
			} else
				if(verificaletra(valchar) | verificanumeros(valchar))
				{
					while(verificaletra(valchar) | verificanumeros(valchar)) 
					{
						selecao_aux = selecao_aux + expressao.charAt(counter);
						valchar = getchar();
						if(fimexpressao)
						{
							setparametroalgebratexto(selecao_aux, "sobrescrito");
							return;
						}
					}
					counter--;
				} else
				{
					setparametroalgebratexto(selecao_aux, "sobrescrito");
					parametroerro(valchar);
					return;
				}
			valchar = getchar();
			if(fimexpressao)
			{
				setparametroalgebratexto(selecao_aux, "sobrescrito");
				return;
			}
			while(verificaparentesefecha(valchar) | verificaespaco(valchar)) 
			{
				if(verificaparentesefecha(valchar))
					parenteses--;
				selecao_aux = selecao_aux + expressao.charAt(counter);
				valchar = getchar();
				if(fimexpressao)
				{
					setparametroalgebratexto(selecao_aux, "sobrescrito");
					return;
				}
			}
			posicao = counter - 1;
			if((valchar == 65) | (valchar == 97))
			{
				valchar = getchar();
				if(fimexpressao)
				{
					selecao_aux = selecao_aux + "A";
					setparametroalgebratexto(selecao_aux, "sobrescrito");
					return;
				}
				if((valchar == 110) | (valchar == 78))
				{
					valchar = getchar();
					if(fimexpressao)
					{
						selecao_aux = selecao_aux + "AN";
						setparametroalgebratexto(selecao_aux, "sobrescrito");
						return;
					}
					if((valchar == 100) | (valchar == 68))
					{
						valchar = getchar();
						if(fimexpressao)
						{
							selecao_aux = selecao_aux + "AND";
							setparametroalgebratexto(selecao_aux, "sobrescrito");
							return;
						}
						if(verificaespaco(valchar))
						{
							selecao_aux = selecao_aux + "AND ";
						} else
						{
							selecao_aux = selecao_aux + "AND";
							setparametroalgebratexto(selecao_aux, "sobrescrito");
							parametroerro(valchar);
							return;
						}
					} else
					{
						selecao_aux = selecao_aux + "AN";
						setparametroalgebratexto(selecao_aux, "sobrescrito");
						parametroerro(valchar);
						return;
					}
				} else
				{
					selecao_aux = selecao_aux + "A";
					setparametroalgebratexto(selecao_aux, "sobrescrito");
					parametroerro(valchar);
					return;
				}
			} else
				if((valchar == 111) | (valchar == 79))
				{
					valchar = getchar();
					if(fimexpressao)
					{
						selecao_aux = selecao_aux + "O";
						setparametroalgebratexto(selecao_aux, "sobrescrito");
						return;
					}
					if((valchar == 114) | (valchar == 82))
					{
						valchar = getchar();
						if(fimexpressao)
						{
							selecao_aux = selecao_aux + "OR";
							setparametroalgebratexto(selecao_aux, "sobrescrito");
							return;
						}
						if(verificaespaco(valchar))
						{
							selecao_aux = selecao_aux + "OR ";
						} else
						{
							selecao_aux = selecao_aux + "OR";
							setparametroalgebratexto(selecao_aux, "sobrescrito");
							parametroerro(valchar);
							return;
						}
					} else
					{
						selecao_aux = selecao_aux + "O";
						setparametroalgebratexto(selecao_aux, "sobrescrito");
						parametroerro(valchar);
						return;
					}
				} else
					if(verificaparenteseabre(valchar))
					{
						setparametroalgebratexto(selecao_aux, "sobrescrito");
						counter--;
						return;
					} else
					{
						setparametroalgebratexto(selecao_aux, "sobrescrito");
						parametroerro(valchar);
						return;
					}
		}
	}

	public void relacao(int valchar)
	{
		String relacao_aux = "";
		while(verificaletra(valchar) | verificanumeros(valchar)) 
		{
			relacao_aux = relacao_aux + expressao.charAt(counter);
			valchar = getchar();
			if(fimexpressao)
			{
				setparametroalgebratexto(relacao_aux, "normal");
				return;
			}
		}
		if(verificaespaco(valchar))
			while(verificaespaco(valchar)) 
			{
				relacao_aux = relacao_aux + expressao.charAt(counter);
				valchar = getchar();
				if(fimexpressao)
				{
					setparametroalgebratexto(relacao_aux, "normal");
					return;
				}
			}
		if((valchar == 922) | (valchar == 44))
		{
			agregacao(relacao_aux, valchar);
			return;
		}
		if(verificaparenteseabre(valchar) | (valchar == 945) && pode_ter_recursao)
		{
			recursao(relacao_aux, valchar);
			return;
		}
		if(verificaparentesefecha(valchar) | verificaespaco(valchar))
			while(verificaparentesefecha(valchar) | verificaespaco(valchar)) 
			{
				relacao_aux = relacao_aux + expressao.charAt(counter);
				if(verificaparentesefecha(valchar))
					parenteses_counter--;
				valchar = getchar();
				if(fimexpressao)
				{
					setparametroalgebratexto(relacao_aux, "normal");
					return;
				}
			}
		setparametroalgebratexto(relacao_aux, "normal");
		if(verificaoperadoresbinarios(valchar))
		{
			operadorbinario(valchar);
		} else
		{
			parametroerro(valchar);
			return;
		}
	}

	public void recursao(String recursao_aux, int valchar)
	{
		boolean sair = false;
		if(recursao_aux.trim().equals("resultado") | recursao_aux.trim().equals("RESULTADO"))
			setparametroalgebratexto(recursao_aux, "operador");
		else
			setparametroalgebratexto(recursao_aux, "normal");
		recursao_aux = "";
		if(verificaparenteseabre(valchar))
		{
			recursao_aux = recursao_aux + expressao.charAt(counter);
			valchar = getchar();
			if(fimexpressao)
			{
				setparametroalgebratexto(recursao_aux, "normal");
				return;
			}
			while(!sair) 
			{
				while(verificaespaco(valchar)) 
				{
					recursao_aux = recursao_aux + expressao.charAt(counter);
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(recursao_aux, "normal");
						return;
					}
				}
				if(verificaletra(valchar) | verificanumeros(valchar))
				{
					while(verificaletra(valchar) | verificanumeros(valchar)) 
					{
						recursao_aux = recursao_aux + expressao.charAt(counter);
						valchar = getchar();
						if(fimexpressao)
						{
							setparametroalgebratexto(recursao_aux, "normal");
							return;
						}
					}
				} else
				{
					setparametroalgebratexto(recursao_aux, "normal");
					parametroerro(valchar);
					return;
				}
				if(valchar == 44)
				{
					recursao_aux = recursao_aux + expressao.charAt(counter);
					sair = false;
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(recursao_aux, "normal");
						return;
					}
				} else
					if(verificaparentesefecha(valchar))
					{
						recursao_aux = recursao_aux + expressao.charAt(counter);
						sair = true;
					} else
					{
						setparametroalgebratexto(recursao_aux, "normal");
						parametroerro(valchar);
						return;
					}
			}
			valchar = getchar();
			if(fimexpressao)
			{
				setparametroalgebratexto(recursao_aux, "normal");
				return;
			}
			while(verificaespaco(valchar)) 
			{
				recursao_aux = recursao_aux + expressao.charAt(counter);
				valchar = getchar();
				if(fimexpressao)
				{
					setparametroalgebratexto(recursao_aux, "normal");
					return;
				}
			}
			if(valchar == 945)
			{
				setparametroalgebratexto(recursao_aux, "normal");
				setparametroalgebratexto((new Character('\u03B1')).toString(), "operador");
				return;
			} else
			{
				setparametroalgebratexto(recursao_aux, "normal");
				parametroerro(valchar);
				return;
			}
		}
		if(valchar == 945)
		{
			setparametroalgebratexto(recursao_aux, "normal");
			setparametroalgebratexto((new Character('\u03B1')).toString(), "operador");
			return;
		} else
		{
			setparametroalgebratexto(recursao_aux, "normal");
			parametroerro(valchar);
			return;
		}
	}

	public void agregacao(String groupby_aux, int valchar)
	{
		boolean sair = false;
		int val_aux = 0;
		String campo_aux = "";
		String agregacao_aux = "";
		while(valchar == 44) 
		{
			groupby_aux = groupby_aux + expressao.charAt(counter);
			valchar = getchar();
			if(fimexpressao)
			{
				setparametroalgebratexto(groupby_aux, "normal");
				return;
			}
			while(verificaespaco(valchar)) 
			{
				groupby_aux = groupby_aux + expressao.charAt(counter);
				valchar = getchar();
				if(fimexpressao)
				{
					setparametroalgebratexto(groupby_aux, "normal");
					return;
				}
			}
			while(verificaletra(valchar) | verificanumeros(valchar)) 
			{
				groupby_aux = groupby_aux + expressao.charAt(counter);
				valchar = getchar();
				if(fimexpressao)
				{
					setparametroalgebratexto(groupby_aux, "normal");
					return;
				}
			}
			while(verificaespaco(valchar)) 
			{
				groupby_aux = groupby_aux + expressao.charAt(counter);
				valchar = getchar();
				if(fimexpressao)
				{
					setparametroalgebratexto(groupby_aux, "normal");
					return;
				}
			}
		}
		if(groupby_aux.length() != 0)
			setparametroalgebratexto(groupby_aux, "normal");
		if(valchar == 922)
		{
			setparametroalgebratexto((new Character('\u039A')).toString(), "operador");
			while(!sair) 
			{
				valchar = getchar();
				if(fimexpressao)
				{
					if(agregacao_aux.length() != 0)
						setparametroalgebratexto(agregacao_aux, "normal");
					return;
				}
				while(verificaespaco(valchar)) 
				{
					agregacao_aux = agregacao_aux + expressao.charAt(counter);
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(agregacao_aux, "normal");
						return;
					}
				}
				if(verificaletra(valchar))
				{
					val_aux = verificaSUM(valchar, agregacao_aux);
					if(val_aux == -1)
						return;
					if(val_aux == 0)
					{
						val_aux = verificaAVERAGE(valchar, agregacao_aux);
						if(val_aux == -1)
							return;
						if(val_aux == 0)
						{
							val_aux = verificaMAXIMUM(valchar, agregacao_aux);
							if(val_aux == -1)
								return;
							if(val_aux == 0)
							{
								val_aux = verificaMINIMUM(valchar, agregacao_aux);
								if(val_aux == -1)
									return;
								if(val_aux == 0)
								{
									val_aux = verificaCOUNT(valchar, agregacao_aux);
									if(val_aux == -1)
										return;
									if(val_aux == 0)
									{
										if(agregacao_aux.length() != 0)
											setparametroalgebratexto(agregacao_aux, "normal");
										parametroerro(valchar);
										return;
									}
									agregacao_aux = agregacao_aux + "COUNT ";
								} else
								{
									agregacao_aux = agregacao_aux + "MINIMUM ";
								}
							} else
							{
								agregacao_aux = agregacao_aux + "MAXIMUM ";
							}
						} else
						{
							agregacao_aux = agregacao_aux + "AVERAGE ";
						}
					} else
					{
						agregacao_aux = agregacao_aux + "SUM ";
					}
				} else
				{
					if(agregacao_aux.length() != 0)
						setparametroalgebratexto(agregacao_aux, "normal");
					parametroerro(valchar);
					return;
				}
				valchar = getchar();
				if(fimexpressao)
				{
					setparametroalgebratexto(agregacao_aux, "normal");
					return;
				}
				while(verificaespaco(valchar)) 
				{
					agregacao_aux = agregacao_aux + expressao.charAt(counter);
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(agregacao_aux, "normal");
						return;
					}
				}
				if(verificaletra(valchar) | verificanumeros(valchar))
				{
					while(verificaletra(valchar) | verificanumeros(valchar)) 
					{
						campo_aux = campo_aux + expressao.charAt(counter);
						valchar = getchar();
						if(fimexpressao)
						{
							setparametroalgebratexto(agregacao_aux, "normal");
							setparametroalgebratexto(campo_aux, "sobrescrito");
							return;
						}
					}
					setparametroalgebratexto(agregacao_aux, "normal");
					setparametroalgebratexto(campo_aux, "sobrescrito");
					agregacao_aux = "";
					campo_aux = "";
				} else
				{
					setparametroalgebratexto(agregacao_aux, "normal");
					parametroerro(valchar);
					return;
				}
				while(verificaespaco(valchar)) 
				{
					agregacao_aux = agregacao_aux + expressao.charAt(counter);
					valchar = getchar();
					if(fimexpressao)
					{
						setparametroalgebratexto(agregacao_aux, "normal");
						return;
					}
				}
				if(verificaparenteseabre(valchar))
				{
					counter--;
					sair = true;
					if(agregacao_aux.length() != 0)
						setparametroalgebratexto(agregacao_aux, "normal");
				} else
					if(valchar == 44)
					{
						agregacao_aux = agregacao_aux + expressao.charAt(counter);
						sair = false;
					} else
					{
						setparametroalgebratexto(agregacao_aux, "normal");
						parametroerro(valchar);
						return;
					}
			}
		} else
		{
			parametroerro(valchar);
			return;
		}
	}

	public int verificaSUM(int valchar, String agregacao_aux)
	{
		if((valchar == 83) | (valchar == 115))
		{
			valchar = getchar();
			if(fimexpressao)
			{
				agregacao_aux = agregacao_aux + "S";
				setparametroalgebratexto(agregacao_aux, "normal");
				return -1;
			}
			if((valchar == 85) | (valchar == 117))
			{
				valchar = getchar();
				if(fimexpressao)
				{
					agregacao_aux = agregacao_aux + "SU";
					setparametroalgebratexto(agregacao_aux, "normal");
					return -1;
				}
				if((valchar == 77) | (valchar == 109))
				{
					valchar = getchar();
					if(fimexpressao)
					{
						agregacao_aux = agregacao_aux + "SUM";
						setparametroalgebratexto(agregacao_aux, "normal");
						return -1;
					}
					if(verificaespaco(valchar))
					{
						return 1;
					} else
					{
						counter -= 3;
						return 0;
					}
				} else
				{
					counter -= 2;
					return 0;
				}
			} else
			{
				counter--;
				return 0;
			}
		} else
		{
			return 0;
		}
	}

	public int verificaAVERAGE(int valchar, String agregacao_aux)
	{
		if((valchar == 65) | (valchar == 97))
		{
			valchar = getchar();
			if(fimexpressao)
			{
				agregacao_aux = agregacao_aux + "A";
				setparametroalgebratexto(agregacao_aux, "normal");
				return -1;
			}
			if((valchar == 86) | (valchar == 118))
			{
				valchar = getchar();
				if(fimexpressao)
				{
					agregacao_aux = agregacao_aux + "AV";
					setparametroalgebratexto(agregacao_aux, "normal");
					return -1;
				}
				if((valchar == 69) | (valchar == 101))
				{
					valchar = getchar();
					if(fimexpressao)
					{
						agregacao_aux = agregacao_aux + "AVE";
						setparametroalgebratexto(agregacao_aux, "normal");
						return -1;
					}
					if((valchar == 82) | (valchar == 114))
					{
						valchar = getchar();
						if(fimexpressao)
						{
							agregacao_aux = agregacao_aux + "AVER";
							setparametroalgebratexto(agregacao_aux, "normal");
							return -1;
						}
						if((valchar == 65) | (valchar == 97))
						{
							valchar = getchar();
							if(fimexpressao)
							{
								agregacao_aux = agregacao_aux + "AVERA";
								setparametroalgebratexto(agregacao_aux, "normal");
								return -1;
							}
							if((valchar == 71) | (valchar == 103))
							{
								valchar = getchar();
								if(fimexpressao)
								{
									agregacao_aux = agregacao_aux + "AVERAG";
									setparametroalgebratexto(agregacao_aux, "normal");
									return -1;
								}
								if((valchar == 69) | (valchar == 101))
								{
									valchar = getchar();
									if(fimexpressao)
									{
										agregacao_aux = agregacao_aux + "AVERAGE";
										setparametroalgebratexto(agregacao_aux, "normal");
										return -1;
									}
									if(verificaespaco(valchar))
									{
										return 1;
									} else
									{
										counter -= 7;
										return 0;
									}
								} else
								{
									counter -= 6;
									return 0;
								}
							} else
							{
								counter -= 5;
								return 0;
							}
						} else
						{
							counter -= 4;
							return 0;
						}
					} else
					{
						counter -= 3;
						return 0;
					}
				} else
				{
					counter -= 2;
					return 0;
				}
			} else
			{
				counter--;
				return 0;
			}
		} else
		{
			return 0;
		}
	}

	public int verificaMAXIMUM(int valchar, String agregacao_aux)
	{
		if((valchar == 77) | (valchar == 109))
		{
			valchar = getchar();
			if(fimexpressao)
			{
				agregacao_aux = agregacao_aux + "M";
				setparametroalgebratexto(agregacao_aux, "normal");
				return -1;
			}
			if((valchar == 65) | (valchar == 97))
			{
				valchar = getchar();
				if(fimexpressao)
				{
					agregacao_aux = agregacao_aux + "MA";
					setparametroalgebratexto(agregacao_aux, "normal");
					return -1;
				}
				if((valchar == 88) | (valchar == 120))
				{
					valchar = getchar();
					if(fimexpressao)
					{
						agregacao_aux = agregacao_aux + "MAX";
						setparametroalgebratexto(agregacao_aux, "normal");
						return -1;
					}
					if((valchar == 73) | (valchar == 105))
					{
						valchar = getchar();
						if(fimexpressao)
						{
							agregacao_aux = agregacao_aux + "MAXI";
							setparametroalgebratexto(agregacao_aux, "normal");
							return -1;
						}
						if((valchar == 77) | (valchar == 109))
						{
							valchar = getchar();
							if(fimexpressao)
							{
								agregacao_aux = agregacao_aux + "MAXIM";
								setparametroalgebratexto(agregacao_aux, "normal");
								return -1;
							}
							if((valchar == 85) | (valchar == 117))
							{
								valchar = getchar();
								if(fimexpressao)
								{
									agregacao_aux = agregacao_aux + "MAXIMU";
									setparametroalgebratexto(agregacao_aux, "normal");
									return -1;
								}
								if((valchar == 77) | (valchar == 109))
								{
									valchar = getchar();
									if(fimexpressao)
									{
										agregacao_aux = agregacao_aux + "MAXIMUM";
										setparametroalgebratexto(agregacao_aux, "normal");
										return -1;
									}
									if(verificaespaco(valchar))
									{
										return 1;
									} else
									{
										counter -= 7;
										return 0;
									}
								} else
								{
									counter -= 6;
									return 0;
								}
							} else
							{
								counter -= 5;
								return 0;
							}
						} else
						{
							counter -= 4;
							return 0;
						}
					} else
					{
						counter -= 3;
						return 0;
					}
				} else
				{
					counter -= 2;
					return 0;
				}
			} else
			{
				counter--;
				return 0;
			}
		} else
		{
			return 0;
		}
	}

	public int verificaMINIMUM(int valchar, String agregacao_aux)
	{
		if((valchar == 77) | (valchar == 109))
		{
			valchar = getchar();
			if(fimexpressao)
			{
				agregacao_aux = agregacao_aux + "M";
				setparametroalgebratexto(agregacao_aux, "normal");
				return -1;
			}
			if((valchar == 73) | (valchar == 105))
			{
				valchar = getchar();
				if(fimexpressao)
				{
					agregacao_aux = agregacao_aux + "MI";
					setparametroalgebratexto(agregacao_aux, "normal");
					return -1;
				}
				if((valchar == 78) | (valchar == 110))
				{
					valchar = getchar();
					if(fimexpressao)
					{
						agregacao_aux = agregacao_aux + "MIN";
						setparametroalgebratexto(agregacao_aux, "normal");
						return -1;
					}
					if((valchar == 73) | (valchar == 105))
					{
						valchar = getchar();
						if(fimexpressao)
						{
							agregacao_aux = agregacao_aux + "MINI";
							setparametroalgebratexto(agregacao_aux, "normal");
							return -1;
						}
						if((valchar == 77) | (valchar == 109))
						{
							valchar = getchar();
							if(fimexpressao)
							{
								agregacao_aux = agregacao_aux + "MINIM";
								setparametroalgebratexto(agregacao_aux, "normal");
								return -1;
							}
							if((valchar == 85) | (valchar == 117))
							{
								valchar = getchar();
								if(fimexpressao)
								{
									agregacao_aux = agregacao_aux + "MINIMU";
									setparametroalgebratexto(agregacao_aux, "normal");
									return -1;
								}
								if((valchar == 77) | (valchar == 109))
								{
									valchar = getchar();
									if(fimexpressao)
									{
										agregacao_aux = agregacao_aux + "MINIMUM";
										setparametroalgebratexto(agregacao_aux, "normal");
										return -1;
									}
									if(verificaespaco(valchar))
									{
										return 1;
									} else
									{
										counter -= 7;
										return 0;
									}
								} else
								{
									counter -= 6;
									return 0;
								}
							} else
							{
								counter -= 5;
								return 0;
							}
						} else
						{
							counter -= 4;
							return 0;
						}
					} else
					{
						counter -= 3;
						return 0;
					}
				} else
				{
					counter -= 2;
					return 0;
				}
			} else
			{
				counter--;
				return 0;
			}
		} else
		{
			return 0;
		}
	}

	public int verificaCOUNT(int valchar, String agregacao_aux)
	{
		if((valchar == 67) | (valchar == 99))
		{
			valchar = getchar();
			if(fimexpressao)
			{
				agregacao_aux = agregacao_aux + "C";
				setparametroalgebratexto(agregacao_aux, "normal");
				return -1;
			}
			if((valchar == 79) | (valchar == 111))
			{
				valchar = getchar();
				if(fimexpressao)
				{
					agregacao_aux = agregacao_aux + "CO";
					setparametroalgebratexto(agregacao_aux, "normal");
					return -1;
				}
				if((valchar == 85) | (valchar == 117))
				{
					valchar = getchar();
					if(fimexpressao)
					{
						agregacao_aux = agregacao_aux + "COU";
						setparametroalgebratexto(agregacao_aux, "normal");
						return -1;
					}
					if((valchar == 78) | (valchar == 110))
					{
						valchar = getchar();
						if(fimexpressao)
						{
							agregacao_aux = agregacao_aux + "COUN";
							setparametroalgebratexto(agregacao_aux, "normal");
							return -1;
						}
						if((valchar == 84) | (valchar == 116))
						{
							valchar = getchar();
							if(fimexpressao)
							{
								agregacao_aux = agregacao_aux + "COUNT";
								setparametroalgebratexto(agregacao_aux, "normal");
								return -1;
							}
							if(verificaespaco(valchar))
							{
								return 1;
							} else
							{
								counter -= 5;
								return 0;
							}
						} else
						{
							counter -= 4;
							return 0;
						}
					} else
					{
						counter -= 3;
						return 0;
					}
				} else
				{
					counter -= 2;
					return 0;
				}
			} else
			{
				counter--;
				return 0;
			}
		} else
		{
			return 0;
		}
	}

	public int getchar()
	{
		int valchar = 0;
		counter++;
		if(counter == expressao.length())
		{
			fimexpressao = true;
			fimtodasexpressoes = true;
			return 0;
		}
		valchar = expressao.charAt(counter);
		if(valchar == quebralinha)
		{
			counter++;
			fimexpressao = true;
			fimtodasexpressoes = false;
			return 0;
		} else
		{
			fimexpressao = false;
			fimtodasexpressoes = false;
			return valchar;
		}
	}

	public boolean verificaletra(int valletra)
	{
		for(int counter = 0; counter < letra_maiuscula.length; counter++)
			if(valletra == letra_maiuscula[counter])
				return true;

		for(int counter = 0; counter < letra_minuscula.length; counter++)
			if(valletra == letra_minuscula[counter])
				return true;

		return valletra == 95;
	}

	public boolean verificanumeros(int valletra)
	{
		for(int counter = 0; counter < numeros.length; counter++)
			if(valletra == numeros[counter])
				return true;

		return false;
	}

	public boolean verificasinais(int valletra)
	{
		for(int counter = 0; counter < sinais.length; counter++)
			if(valletra == sinais[counter])
				return true;

		return false;
	}

	public boolean verificacomparacao(int valletra)
	{
		for(int counter = 0; counter < comparacao.length; counter++)
			if(valletra == comparacao[counter])
				return true;

		return false;
	}

	public boolean verificaoperadoresbinarios(int valletra)
	{
		for(int counter = 0; counter < operadores_binarios.length; counter++)
			if(valletra == operadores_binarios[counter])
				return true;

		return false;
	}

	public boolean verificaoperadoresunarios(int valletra)
	{
		for(int counter = 0; counter < operadores_unarios.length; counter++)
			if(valletra == operadores_unarios[counter])
				return true;

		return false;
	}

	public boolean verificapontuacao(int valletra)
	{
		for(int counter = 0; counter < pontuacao.length; counter++)
			if(valletra == pontuacao[counter])
				return true;

		return false;
	}

	public boolean verificaespaco(int valletra)
	{
		return valletra == espaco;
	}

	public boolean verificaparenteseabre(int valletra)
	{
		return valletra == parentese_abre;
	}

	public boolean verificaparentesefecha(int valletra)
	{
		return valletra == parentese_fecha;
	}

	public int getidentificador()
	{
		return identificador;
	}

	public void setidentificador()
	{
		identificador++;
	}

	public int setpredicado(int valchar, int contador)
	{
		if(contador == 0)
		{
			if(predicado[getidentificador()] == null)
				predicado[getidentificador()] = (new Character((char)valchar)).toString();
			else
				predicado[getidentificador()] = predicado[getidentificador()] + " AND " + (new Character((char)valchar)).toString();
		} else
		{
			predicado[getidentificador()] = predicado[getidentificador()] + (new Character((char)valchar)).toString();
		}
		return ++contador;
	}

	public void setatributo(int valchar)
	{
		if(valchar == 44)
		{
			cont_atributo++;
		} else
		{
			if(atributo[getidentificador()][cont_relacao][cont_atributo] == null)
				atributo[getidentificador()][cont_relacao][cont_atributo] = "";
			atributo[getidentificador()][cont_relacao][cont_atributo] = atributo[getidentificador()][cont_relacao][cont_atributo] + (new Character((char)valchar)).toString();
		}
	}

	public void setgroupby(String groupby_aux)
	{
		if(group_by[getidentificador()] != null)
			group_by[getidentificador()] = group_by[getidentificador()] + ", " + groupby_aux;
		else
			group_by[getidentificador()] = groupby_aux;
	}

	public void setatributoSQL(String atributo_aux)
	{
		cont_atributo++;
		atributo[getidentificador()][cont_relacao][cont_atributo] = atributo_aux;
	}

	public void setrenomearatributo(int valchar)
	{
		if(valchar == 44)
		{
			cont_renomear_atributo++;
		} else
		{
			if(renomear_atributo[getidentificador()][cont_relacao][cont_renomear_atributo] == null)
				renomear_atributo[getidentificador()][cont_relacao][cont_renomear_atributo] = "";
			renomear_atributo[getidentificador()][cont_relacao][cont_renomear_atributo] = renomear_atributo[getidentificador()][cont_relacao][cont_renomear_atributo] + (new Character((char)valchar)).toString();
		}
	}

	int letra_minuscula[] = {
			97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 
			107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 
			117, 118, 119, 120, 121, 122
	};
	int letra_maiuscula[] = {
			65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
			75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
			85, 86, 87, 88, 89, 90
	};
	int numeros[] = {
			48, 49, 50, 51, 52, 53, 54, 55, 56, 57
	};
	int sinais[] = {
			37, 43, 45, 62, 60, 61
	};
	int comparacao[] = {
			62, 60, 61
	};
	int operadores_binarios[] = {
			912, 913, 914, 915, 916, 917, 918, 919, 921, 923
	};
	int operadores_unarios[] = {
			910, 911, 920
	};
	int pontuacao[] = {
			34, 39, 44, 46
	};
	int espaco;
	int parentese_abre;
	int parentese_fecha;
	int identificador;
	int quebralinha;
	boolean fimexpressao;
	boolean fimtodasexpressoes;
	boolean fimresultado;
	String expressao;
	String schema;
	String resultado;
	int counter;
	int parenteses_counter;
	String msgerro;
	String predicado[];
	String atributo[][][];
	String renomear_atributo[][][];
	String renomear_relacao[][];
	String relacao[][];
	String condicoes_de_join[][];
	String group_by[];
	int operadorbinario[][];
	int cont_atributo;
	int cont_renomear_atributo;
	int cont_renomear_relacao;
	int cont_relacao;
	int cont_operador_binario;
	boolean pode_ter_recursao;
	ParametroAlgebraTexto parametroalgebratexto[];
}
