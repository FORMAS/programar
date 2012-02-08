package Algebra.Cliente;

import java.sql.*;

// Referenced classes of package Algebra.Cliente:
//            AlgebraRelacional, CriaConexao

public class ExecutaAlgebra
{

    public ExecutaAlgebra(String parametro, String sch)
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
        pode_ter_recursao = true;
        linha = 1;
        posicao_inicial_linha = 0;
        algebrarelacional = new AlgebraRelacional[100];
        expressao = parametro;
        schema = sch;
        if(expressao.length() == 0)
        {
            msgerro = "Campo express\343o deve ser preenchido.";
            return;
        }
        while(!fimtodasexpressoes) 
        {
            fimexpressao = false;
            verifica_expressao();
            if(msgerro.length() != 0)
                return;
        }
        impressao();
        gerasql();
        System.out.println(resultado);
    }

    public void gerasql()
    {
        int posicao_algebra_resultado = -1;
        boolean sair = false;
        for(int j = 0; j < algebrarelacional.length; j++)
            if(algebrarelacional[j] != null)
                algebrarelacional[j].getsql();

        posicao_algebra_resultado = getposicaoresultado();
        resultado = algebrarelacional[posicao_algebra_resultado].getsql();
        for(; !sair; sair = substituirelacao());
    }

    public boolean substituirelacao()
    {
        int valchar = 0;
        int contador = 0;
        int posicao_inicial = 0;
        int posicao_final = 0;
        String relacao_aux = "";
        String resultado_aux = "";
        for(; valchar != 35; valchar = resultado.charAt(contador))
            if(++contador == resultado.length())
                return true;

        posicao_inicial = contador;
        valchar = 0;
        contador++;
        for(; valchar != 35; valchar = resultado.charAt(contador))
        {
            relacao_aux = relacao_aux + resultado.charAt(contador);
            if(++contador == resultado.length())
                return true;
        }

        posicao_final = contador;
        relacao_aux = verifica_relacao_nome_algebra(relacao_aux);
        for(int i = 0; i < posicao_inicial; i++)
            resultado_aux = resultado_aux + resultado.charAt(i);

        resultado_aux = resultado_aux + relacao_aux;
        for(int i = posicao_final + 1; i < resultado.length(); i++)
            resultado_aux = resultado_aux + resultado.charAt(i);

        resultado = resultado_aux;
        return false;
    }

    public String verifica_relacao_nome_algebra(String relacao_aux)
    {
        for(int i = 0; i < algebrarelacional.length; i++)
            if(algebrarelacional[i] != null && algebrarelacional[i].getnome().trim().equals(relacao_aux))
            {
                relacao_aux = "(" + algebrarelacional[i].getsql() + ") AS " + algebrarelacional[i].nome.toUpperCase();
                return relacao_aux;
            }

        return relacao_aux.toUpperCase();
    }

    public int getposicaoresultado()
    {
        int count;
        for(count = 0; count < algebrarelacional.length; count++)
            if(algebrarelacional[count] != null)
            {
                if(algebrarelacional[count].isresultado())
                    return count;
            } else
            {
                return --count;
            }

        return count;
    }

    public void impressao()
    {
        for(int j = 0; j < algebrarelacional.length; j++)
            if(algebrarelacional[j] != null)
            {
                algebrarelacional[j].impressao();
                System.out.println("");
            }

    }

    public void verifica_expressao()
    {
        int valchar = 0;
        pode_ter_recursao = true;
        setidentificador();
        while(!fimexpressao) 
        {
            valchar = getchar();
            if(fimexpressao)
            {
                msgerro = "Express\343o incompleta\nErro: Verifica_Expressao-001\nLinha: " + linha;
                return;
            }
            if(verificaoperadoresunarios(valchar))
                operadorunario(valchar);
            else
            if(verificaletra(valchar) | verificanumeros(valchar))
                relacao(valchar);
            else
            if(verificaparenteseabre(valchar))
                parenteses_counter++;
            else
            if(valchar == 922)
                agregacao("", valchar);
            else
            if(!verificaespaco(valchar))
            {
                setmsgerro("Caracter n\343o permitido neste local\nErro: Verifica_Expressao-001");
                return;
            }
            if(msgerro.length() != 0)
                return;
            pode_ter_recursao = false;
        }
    }

    public void operadorunario(int valchar)
    {
        switch(valchar)
        {
        case 910: 
            selecao();
            break;

        case 911: 
            projecao();
            break;

        case 920: 
            renomear();
            break;
        }
    }

    public void operadorbinario(int valchar)
    {
        switch(valchar)
        {
        case 912: 
            setoperaodorbinario(valchar);
            break;

        case 915: 
            setoperaodorbinario(valchar);
            break;

        case 916: 
            setoperaodorbinario(valchar);
            join();
            break;

        case 917: 
            setoperaodorbinario(valchar);
            outerjointotal();
            break;

        case 918: 
            setoperaodorbinario(valchar);
            outerjoinesquerda();
            break;

        case 919: 
            setoperaodorbinario(valchar);
            outerjoindireita();
            break;

        case 923: 
            setoperaodorbinario(valchar);
            break;
        }
    }

    public String operadorbinarioSQL(int valchar)
    {
        String retorno = "";
        switch(valchar)
        {
        case 915: 
            retorno = "JOIN ";
            break;

        case 916: 
            retorno = "JOIN ";
            break;

        case 917: 
            retorno = "LEFT OUTER JOIN ";
            break;

        case 918: 
            retorno = "LEFT OUTER JOIN ";
            break;

        case 919: 
            retorno = "RIGHT OUTER JOIN ";
            break;

        case 923: 
            retorno = "NATURAL JOIN ";
            break;
        }
        return retorno;
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
        for(boolean sair = false; !sair;)
        {
            valchar = getchar();
            if(fimexpressao)
            {
                msgerro = "Express\343o incompleta\nErro: " + origem + "-001" + "\n" + "Linha: " + linha;
                return;
            }
            if(verificaespaco(valchar))
                while(verificaespaco(valchar)) 
                {
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: " + origem + "-002" + "\n" + "Linha: " + linha;
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
                        msgerro = "Express\343o incompleta\nErro: " + origem + "-003" + "\n" + "Linha: " + linha;
                        return;
                    }
                    if(verificaoperadoresbinarios(valchar) | verificaoperadoresunarios(valchar))
                    {
                        msgerro = "Condi\347\365es do " + origem + " n\343o foram encontradas." + "\n" + "Erro: " + origem + "-004" + "\n" + "Linha: " + linha;
                        return;
                    }
                    if(verificaparentesefecha(valchar))
                    {
                        valchar = getchar();
                        if(fimexpressao)
                        {
                            msgerro = "Express\343o incompleta\nErro: " + origem + "-005" + "\n" + "Linha: " + linha;
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
                        if(verificaparenteseabre(valchar) | verificaletra(valchar) | verificanumeros(valchar))
                        {
                            counter = local_parenteses_abre - 1;
                            sair = true;
                        } else
                        {
                            setmsgerro("Caracter n\343o permitido neste local\nErro: " + origem + "-006");
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
                msgerro = "Express\343o incompleta\nErro: " + origem + "-007" + "\n" + "Linha: " + linha;
                return;
            }
            if(verificaparenteseabre(valchar) | verificaespaco(valchar) | verificaletra(valchar) | verificanumeros(valchar))
            {
                while(verificaparenteseabre(valchar) | verificaespaco(valchar)) 
                {
                    setcondicoesdejoin(valchar, false);
                    if(verificaparenteseabre(valchar))
                        parenteses++;
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: " + origem + "-008" + "\n" + "Linha: " + linha;
                        return;
                    }
                }
                if(verificaletra(valchar) | verificanumeros(valchar))
                {
                    while(verificaletra(valchar) | verificanumeros(valchar)) 
                    {
                        setcondicoesdejoin(valchar, false);
                        valchar = getchar();
                        if(fimexpressao)
                        {
                            msgerro = "Express\343o incompleta\nErro: " + origem + "-009" + "\n" + "Linha: " + linha;
                            return;
                        }
                    }
                } else
                {
                    setmsgerro("Caracter n\343o permitido neste local\nErro: " + origem + "-010");
                    return;
                }
            } else
            {
                setmsgerro("Caracter n\343o permitido neste local\nErro: " + origem + "-011");
                return;
            }
            while(verificaespaco(valchar)) 
            {
                setcondicoesdejoin(valchar, false);
                valchar = getchar();
                if(fimexpressao)
                {
                    msgerro = "Express\343o incompleta\nErro: " + origem + "-012" + "\n" + "Linha: " + linha;
                    return;
                }
            }
            if(verificacomparacao(valchar))
            {
                setcondicoesdejoin(valchar, false);
                switch(valchar)
                {
                case 60: // '<'
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: " + origem + "-013" + "\n" + "Linha: " + linha;
                        return;
                    }
                    if((valchar == 61) | (valchar == 62))
                        setcondicoesdejoin(valchar, false);
                    else
                    if(verificaespaco(valchar) | verificaletra(valchar) | verificanumeros(valchar))
                    {
                        counter--;
                    } else
                    {
                        setmsgerro("Caracter n\343o permitido neste local\nErro: " + origem + "-014");
                        return;
                    }
                    break;

                case 62: // '>'
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: " + origem + "-015" + "\n" + "Linha: " + linha;
                        return;
                    }
                    if(valchar == 61)
                        setcondicoesdejoin(valchar, false);
                    else
                    if(verificaespaco(valchar) | verificaletra(valchar) | verificanumeros(valchar))
                    {
                        counter--;
                    } else
                    {
                        setmsgerro("Caracter n\343o permitido neste local\nErro: " + origem + "-016");
                        return;
                    }
                    break;
                }
            } else
            {
                setmsgerro("Caracter n\343o permitido neste local\nErro: " + origem + "-017");
                return;
            }
            valchar = getchar();
            if(fimexpressao)
            {
                msgerro = "Express\343o incompleta\nErro: " + origem + "-018" + "\n" + "Linha: " + linha;
                return;
            }
            while(verificaespaco(valchar)) 
            {
                setcondicoesdejoin(valchar, false);
                valchar = getchar();
                if(fimexpressao)
                {
                    msgerro = "Express\343o incompleta\nErro: " + origem + "-019" + "\n" + "Linha: " + linha;
                    return;
                }
            }
            if(verificaletra(valchar) | verificanumeros(valchar))
            {
                while(verificaletra(valchar) | verificanumeros(valchar)) 
                {
                    setcondicoesdejoin(valchar, false);
                    valchar = getchar();
                    if(fimexpressao)
                        msgerro = "Express\343o incompleta\nErro: " + origem + "-020" + "\n" + "Linha: " + linha;
                }
                counter--;
            } else
            {
                setmsgerro("Caracter n\343o permitido neste local\nErro: " + origem + "-021");
                return;
            }
            valchar = getchar();
            if(fimexpressao)
            {
                msgerro = "Express\343o incompleta\nErro: " + origem + "-022" + "\n" + "Linha: " + linha;
                return;
            }
            while(verificaparentesefecha(valchar) | verificaespaco(valchar)) 
            {
                setcondicoesdejoin(valchar, false);
                if(verificaparentesefecha(valchar))
                    parenteses--;
                valchar = getchar();
                if(fimexpressao)
                {
                    msgerro = "Express\343o incompleta\nErro: " + origem + "-023" + "\n" + "Linha: " + linha;
                    return;
                }
                if(parenteses < 0)
                {
                    msgerro = "Uso incorreto dos parenteses\nErro: " + origem + "-024" + "\n" + "Linha: " + linha;
                    return;
                }
            }
            posicao = counter - 1;
            if((valchar == 65) | (valchar == 97))
            {
                valchar = getchar();
                if(fimexpressao)
                    if(parenteses == 0)
                    {
                        counter--;
                        return;
                    } else
                    {
                        msgerro = "Uso incorreto dos parenteses\nErro: " + origem + "-025" + "\n" + "Linha: " + linha;
                        return;
                    }
                if((valchar == 110) | (valchar == 78))
                {
                    valchar = getchar();
                    if(fimexpressao)
                        if(parenteses == 0)
                        {
                            counter -= 2;
                            return;
                        } else
                        {
                            msgerro = "Uso incorreto dos parenteses\nErro: " + origem + "-026" + "\n" + "Linha: " + linha;
                            return;
                        }
                    if((valchar == 100) | (valchar == 68))
                    {
                        valchar = getchar();
                        if(fimexpressao)
                        {
                            msgerro = "Express\343o incompleta\nErro: " + origem + "-027" + "\n" + "Linha: " + linha;
                            return;
                        }
                        if(verificaespaco(valchar))
                        {
                            if(!verificaespaco(expressao.charAt(posicao)))
                            {
                                msgerro = "Antes e depois do AND deve ter pelo menos um espa\347o em branco.\nErro: " + origem + "-028" + "\n" + "Linha: " + linha;
                                return;
                            }
                            setcondicoesdejoin(valchar, true);
                        } else
                        if(verificaletra(valchar) | verificanumeros(valchar))
                        {
                            if(parenteses == 0)
                            {
                                counter -= 4;
                                return;
                            } else
                            {
                                msgerro = "Uso incorreto dos parenteses\nErro: " + origem + "-029" + "\n" + "Linha: " + linha;
                                return;
                            }
                        } else
                        {
                            setmsgerro("Caracter n\343o permitido neste local\nErro: " + origem + "-030");
                            return;
                        }
                    } else
                    if(parenteses == 0)
                    {
                        counter -= 3;
                        return;
                    } else
                    {
                        msgerro = "Uso incorreto dos parenteses\nErro: " + origem + "-031" + "\n" + "Linha: " + linha;
                        return;
                    }
                } else
                if(parenteses == 0)
                {
                    counter -= 2;
                    return;
                } else
                {
                    msgerro = "Uso incorreto dos parenteses\nErro: " + origem + "-032" + "\n" + "Linha: " + linha;
                    return;
                }
            } else
            if(parenteses == 0)
            {
                counter--;
                sair = true;
            } else
            {
                msgerro = "Uso incorreto dos parenteses\nErro: " + origem + "-033" + "\n" + "Linha: " + linha;
                return;
            }
        }

    }

    public void renomear()
    {
        int valchar = 0;
        int local_parenteses_abre = 0;
        boolean sair = false;
        String relacao_aux = "";
        boolean renomear_relacao = false;
        valchar = getchar();
        if(fimexpressao)
        {
            msgerro = "Express\343o incompleta\nErro: Renomear-001\nLinha: " + linha;
            return;
        }
        while(verificaespaco(valchar)) 
        {
            valchar = getchar();
            if(fimexpressao)
            {
                msgerro = "Express\343o incompleta\nErro: Renomear-002\nLinha: " + linha;
                return;
            }
        }
        if((!verificaletra(valchar)) & (!verificanumeros(valchar)) & (!verificaparenteseabre(valchar)))
        {
            setmsgerro("Caracter n\343o permitido neste local\nErro: Renomear-003");
            return;
        }
        while(verificaletra(valchar) | verificanumeros(valchar)) 
        {
            relacao_aux = relacao_aux + expressao.charAt(counter);
            renomear_relacao = true;
            valchar = getchar();
            if(fimexpressao)
            {
                msgerro = "Express\343o incompleta\nErro: Renomear-004\nLinha: " + linha;
                return;
            }
        }
        if(renomear_relacao)
            setrenomearrelacao(relacao_aux);
        while(verificaespaco(valchar)) 
        {
            valchar = getchar();
            if(fimexpressao)
            {
                msgerro = "Express\343o incompleta\nErro: Renomear-002\nLinha: " + linha;
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
                    msgerro = "Express\343o incompleta\nErro: Renomear-005\nLinha: " + linha;
                    return;
                }
                if(verificaoperadoresbinarios(valchar) | verificaoperadoresunarios(valchar))
                    if(renomear_relacao)
                    {
                        counter = local_parenteses_abre - 1;
                        return;
                    } else
                    {
                        msgerro = "No operador Renomear, n\343o foram informados o novo nome da Rela\347\343o\nnem o novo nome dos atributos. Pelo menos um deve ser informado\nErro: Renomear-006\nLinha: " + linha;
                        return;
                    }
                if(verificaparentesefecha(valchar))
                {
                    valchar = getchar();
                    if(fimexpressao)
                        if(renomear_relacao)
                        {
                            counter = local_parenteses_abre - 1;
                            fimexpressao = false;
                            return;
                        } else
                        {
                            msgerro = "No operador Renomear, n\343o foram informados o novo nome da Rela\347\343o\nnem o novo nome dos atributos. Pelo menos um deve ser informado\nErro: Renomear-007\nLinha: " + linha;
                            return;
                        }
                    while(verificaespaco(valchar)) 
                    {
                        valchar = getchar();
                        if(fimexpressao)
                            if(renomear_relacao)
                            {
                                counter = local_parenteses_abre - 1;
                                fimexpressao = false;
                                return;
                            } else
                            {
                                msgerro = "No operador Renomear, n\343o foram informados o novo nome da Rela\347\343o\nnem o novo nome dos atributos. Pelo menos um deve ser informado\nErro: Renomear-008\nLinha: " + linha;
                                return;
                            }
                    }
                    if(verificaparenteseabre(valchar))
                    {
                        counter = local_parenteses_abre;
                        sair = true;
                    } else
                    if(renomear_relacao)
                    {
                        counter = local_parenteses_abre - 1;
                        return;
                    } else
                    {
                        msgerro = "No operador Renomear, n\343o foram informados o novo nome da Rela\347\343o\nnem o novo nome dos atributos. Pelo menos um deve ser informado\nErro: Renomear-009\nLinha: " + linha;
                        return;
                    }
                } else
                {
                    if(valchar == 44)
                    {
                        counter = local_parenteses_abre;
                        sair = true;
                    }
                    if(!(verificaletra(valchar) | verificanumeros(valchar)))
                    {
                        setmsgerro("Caracter n\343o permitido neste local\nErro: Renomear-010");
                        return;
                    }
                }
            }
        } else
        {
            setmsgerro("Caracter n\343o permitido neste local\nErro: Renomear-011");
            return;
        }
        algebrarelacional[getidentificador()].inicio_renomear_atributo();
        for(sair = false; !sair;)
        {
            valchar = getchar();
            if(fimexpressao)
            {
                msgerro = "Express\343o incompleta\nErro: Renomear-012\nLinha: " + linha;
                return;
            }
            while(verificaespaco(valchar)) 
            {
                valchar = getchar();
                if(fimexpressao)
                {
                    msgerro = "Express\343o incompleta\nErro: Renomear-013\nLinha: " + linha;
                    return;
                }
            }
            if(verificaletra(valchar) | verificanumeros(valchar))
            {
                while(verificaletra(valchar) | verificanumeros(valchar)) 
                {
                    setrenomearatributo(valchar);
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: Renomear-014\nLinha: " + linha;
                        return;
                    }
                }
                while(verificaespaco(valchar)) 
                {
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: Renomear-015\nLinha: " + linha;
                        return;
                    }
                }
                if(valchar == 44)
                    setrenomearatributo(valchar);
                else
                if(verificaparentesefecha(valchar))
                {
                    sair = true;
                } else
                {
                    setmsgerro("Caracter n\343o permitido neste local\nErro: Renomear-016");
                    return;
                }
            } else
            {
                setmsgerro("Caracter n\343o permitido neste local\nErro: Renomear-017");
                return;
            }
        }

    }

    public void projecao()
    {
        int valchar = 0;
        boolean sair = false;
        valchar = getchar();
        if(fimexpressao)
        {
            msgerro = "Express\343o incompleta\nErro: Proje\347\343o-001\nLinha: " + linha;
            return;
        }
        while(verificaespaco(valchar)) 
        {
            setatributo(valchar);
            valchar = getchar();
            if(fimexpressao)
            {
                msgerro = "Express\343o incompleta\nErro: Proje\347\343o-002\nLinha: " + linha;
                return;
            }
        }
        while(!sair) 
            if(verificanumeros(valchar) | verificaletra(valchar))
            {
                while(verificanumeros(valchar) | verificaletra(valchar)) 
                {
                    setatributo(valchar);
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: Proje\347\343o-003\nLinha: " + linha;
                        return;
                    }
                }
                while(verificaespaco(valchar)) 
                {
                    setatributo(valchar);
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: Proje\347\343o-004\nLinha: " + linha;
                        return;
                    }
                }
                if(verificaparenteseabre(valchar))
                {
                    counter--;
                    return;
                }
                if(valchar == 44)
                {
                    setatributo(valchar);
                    sair = false;
                } else
                {
                    setmsgerro("Caracter n\343o permitido neste local\nErro: Proje\347\343o-005");
                    return;
                }
                valchar = getchar();
                if(fimexpressao)
                {
                    msgerro = "Express\343o incompleta\nErro: Proje\347\343o-006\nLinha: " + linha;
                    return;
                }
                while(verificaespaco(valchar)) 
                {
                    setatributo(valchar);
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: Proje\347\343o-007\nLinha: " + linha;
                        return;
                    }
                }
            } else
            {
                setmsgerro("Caracter n\343o permitido neste local\nErro: Proje\347\343o-008");
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
        for(boolean sair = false; !sair;)
        {
            valchar = getchar();
            if(fimexpressao)
            {
                msgerro = "Express\343o incompleta\nErro: Sele\347\343o-001\nLinha: " + linha;
                return;
            }
            if(verificaespaco(valchar))
                while(verificaespaco(valchar)) 
                {
                    qtd_char = setpredicado(valchar, qtd_char);
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: Sele\347\343o-002\nLinha: " + linha;
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
                        msgerro = "Express\343o incompleta\nErro: Sele\347\343o-003\nLinha: " + linha;
                        return;
                    }
                    if(verificaoperadoresbinarios(valchar) | verificaoperadoresunarios(valchar))
                    {
                        counter = local_parenteses_abre - 1;
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
                            counter = local_parenteses_abre - 1;
                            sair = true;
                        } else
                        {
                            counter = local_parenteses_abre - 1;
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
                msgerro = "Express\343o incompleta\nErro: Sele\347\343o-004\nLinha: " + linha;
                return;
            }
            if(verificaparenteseabre(valchar) | verificaespaco(valchar) | verificaletra(valchar) | verificanumeros(valchar))
            {
                while(verificaparenteseabre(valchar) | verificaespaco(valchar)) 
                {
                    qtd_char = setpredicado(valchar, qtd_char);
                    if(verificaparenteseabre(valchar))
                        parenteses++;
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: Sele\347\343o-005\nLinha: " + linha;
                        return;
                    }
                }
                if(verificaletra(valchar) | verificanumeros(valchar))
                {
                    while(verificaletra(valchar) | verificanumeros(valchar)) 
                    {
                        qtd_char = setpredicado(valchar, qtd_char);
                        valchar = getchar();
                        if(fimexpressao)
                        {
                            msgerro = "Express\343o incompleta\nErro: Sele\347\343o-006\nLinha: " + linha;
                            return;
                        }
                    }
                } else
                {
                    setmsgerro("Caracter n\343o permitido neste local\nErro: Sele\347\343o-007");
                    return;
                }
            } else
            {
                setmsgerro("Caracter n\343o permitido neste local\nErro: Sele\347\343o-008");
                return;
            }
            while(verificaespaco(valchar)) 
            {
                qtd_char = setpredicado(valchar, qtd_char);
                valchar = getchar();
                if(fimexpressao)
                {
                    msgerro = "Express\343o incompleta\nErro: Sele\347\343o-009\nLinha: " + linha;
                    return;
                }
            }
            if(verificacomparacao(valchar))
            {
                qtd_char = setpredicado(valchar, qtd_char);
                switch(valchar)
                {
                case 60: // '<'
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: Sele\347\343o-010\nLinha: " + linha;
                        return;
                    }
                    if((valchar == 61) | (valchar == 62))
                        qtd_char = setpredicado(valchar, qtd_char);
                    else
                    if(verificaespaco(valchar) | verificaletra(valchar) | verificanumeros(valchar))
                    {
                        counter--;
                    } else
                    {
                        setmsgerro("Caracter n\343o permitido neste local\nErro: Sele\347\343o-011");
                        return;
                    }
                    break;

                case 62: // '>'
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: Sele\347\343o-012\nLinha: " + linha;
                        return;
                    }
                    if(valchar == 61)
                        qtd_char = setpredicado(valchar, qtd_char);
                    else
                    if(verificaespaco(valchar) | verificaletra(valchar) | verificanumeros(valchar))
                    {
                        counter--;
                    } else
                    {
                        setmsgerro("Caracter n\343o permitido neste local\nErro: Sele\347\343o-013");
                        return;
                    }
                    break;
                }
            } else
            {
                setmsgerro("Caracter n\343o permitido neste local\nErro: Sele\347\343o-014");
                return;
            }
            valchar = getchar();
            if(fimexpressao)
            {
                msgerro = "Express\343o incompleta\nErro: Sele\347\343o-015\nLinha: " + linha;
                return;
            }
            while(verificaespaco(valchar)) 
            {
                qtd_char = setpredicado(valchar, qtd_char);
                valchar = getchar();
                if(fimexpressao)
                {
                    msgerro = "Express\343o incompleta\nErro: Sele\347\343o-016\nLinha: " + linha;
                    return;
                }
            }
            if(valchar == 34)
            {
                qtd_char = setpredicado(valchar, qtd_char);
                for(sair = false; !sair;)
                {
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: Sele\347\343o-017\nLinha: " + linha;
                        return;
                    }
                    if(verificaletra(valchar) | verificanumeros(valchar) | verificaespaco(valchar))
                        qtd_char = setpredicado(valchar, qtd_char);
                    else
                    if(valchar == 34)
                    {
                        qtd_char = setpredicado(valchar, qtd_char);
                        sair = true;
                    } else
                    {
                        setmsgerro("Caracter n\343o permitido neste local\nErro: Sele\347\343o-018");
                        return;
                    }
                }

                sair = false;
            } else
            if(verificaletra(valchar) | verificanumeros(valchar))
            {
                while(verificaletra(valchar) | verificanumeros(valchar)) 
                {
                    qtd_char = setpredicado(valchar, qtd_char);
                    valchar = getchar();
                    if(fimexpressao)
                        msgerro = "Express\343o incompleta\nErro: Sele\347\343o-019\nLinha: " + linha;
                }
                counter--;
            } else
            {
                setmsgerro("Caracter n\343o permitido neste local\nErro: Sele\347\343o-020");
                return;
            }
            valchar = getchar();
            if(fimexpressao)
            {
                msgerro = "Express\343o incompleta\nErro: Sele\347\343o-021\nLinha: " + linha;
                return;
            }
            while(verificaparentesefecha(valchar) | verificaespaco(valchar)) 
            {
                qtd_char = setpredicado(valchar, qtd_char);
                if(verificaparentesefecha(valchar))
                    parenteses--;
                valchar = getchar();
                if(fimexpressao)
                {
                    msgerro = "Express\343o incompleta\nErro: Sele\347\343o-022\nLinha: " + linha;
                    return;
                }
                if(parenteses < 0)
                {
                    msgerro = "Uso incorreto dos parenteses\nErro: Sele\347\343o-023\nLinha: " + linha;
                    return;
                }
            }
            posicao = counter - 1;
            if((valchar == 65) | (valchar == 97))
            {
                qtd_char = setpredicado(valchar, qtd_char);
                valchar = getchar();
                if(fimexpressao)
                {
                    msgerro = "Express\343o incompleta\nErro: Sele\347\343o-024\nLinha: " + linha;
                    return;
                }
                if((valchar == 110) | (valchar == 78))
                {
                    qtd_char = setpredicado(valchar, qtd_char);
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: Sele\347\343o-025\nLinha: " + linha;
                        return;
                    }
                    if((valchar == 100) | (valchar == 68))
                    {
                        qtd_char = setpredicado(valchar, qtd_char);
                        valchar = getchar();
                        if(fimexpressao)
                        {
                            msgerro = "Express\343o incompleta\nErro: Sele\347\343o-026\nLinha: " + linha;
                            return;
                        }
                        qtd_char = setpredicado(valchar, qtd_char);
                        if((!verificaespaco(valchar)) | (!verificaespaco(expressao.charAt(posicao))))
                        {
                            msgerro = "Antes e depois do AND deve ter pelo menos um espa\347o em branco.\nErro: Sele\347\343o-027\nLinha: " + linha;
                            return;
                        }
                    } else
                    {
                        setmsgerro("Caracter n\343o permitido neste local\nErro: Sele\347\343o-028");
                        return;
                    }
                } else
                {
                    setmsgerro("Caracter n\343o permitido neste local\nErro: Sele\347\343o-029");
                    return;
                }
            } else
            if((valchar == 111) | (valchar == 79))
            {
                qtd_char = setpredicado(valchar, qtd_char);
                valchar = getchar();
                if(fimexpressao)
                {
                    msgerro = "Express\343o incompleta\nErro: Sele\347\343o-030\nLinha: " + linha;
                    return;
                }
                if((valchar == 114) | (valchar == 82))
                {
                    qtd_char = setpredicado(valchar, qtd_char);
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: Sele\347\343o-031\nLinha: " + linha;
                        return;
                    }
                    qtd_char = setpredicado(valchar, qtd_char);
                    if((!verificaespaco(valchar)) | (!verificaespaco(expressao.charAt(posicao))))
                    {
                        msgerro = "Antes e depois do OR deve ter pelo menos um espa\347o em branco.\nErro: Sele\347\343o-032\nLinha: " + linha;
                        return;
                    }
                } else
                {
                    setmsgerro("Caracter n\343o permitido neste local\nErro: Sele\347\343o-033");
                    return;
                }
            } else
            if(verificaparenteseabre(valchar))
            {
                if(parenteses == 0)
                {
                    counter--;
                    sair = true;
                } else
                {
                    msgerro = "Uso incorreto dos parenteses\nErro: Sele\347\343o-034\nLinha: " + linha;
                    return;
                }
            } else
            {
                setmsgerro("Caracter n\343o permitido neste local\nErro: Sele\347\343o-035");
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
                if(parenteses_counter == 0)
                {
                    setrelacao(relacao_aux);
                    return;
                } else
                {
                    msgerro = "Uso incorreto dos parenteses\nErro: Rela\347\343o-001\nLinha: " + linha;
                    return;
                }
        }
        for(; verificaespaco(valchar); valchar = getchar());
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
        setrelacao(relacao_aux);
        if(verificaparentesefecha(valchar) | verificaespaco(valchar))
            while(verificaparentesefecha(valchar) | verificaespaco(valchar)) 
            {
                if(verificaparentesefecha(valchar))
                    parenteses_counter--;
                valchar = getchar();
                if(fimexpressao)
                    if(parenteses_counter == 0)
                    {
                        return;
                    } else
                    {
                        msgerro = "Uso incorreto dos parenteses\nErro: Rela\347\343o-002\nLinha: " + linha;
                        return;
                    }
                if(parenteses_counter < 0)
                {
                    msgerro = "Uso incorreto dos parenteses\nErro: Rela\347\343o-003\nLinha: " + linha;
                    return;
                }
            }
        if(verificaoperadoresbinarios(valchar))
        {
            operadorbinario(valchar);
        } else
        {
            setmsgerro("Caracter n\343o permitido neste local\nErro: Rela\347\343o-004");
            return;
        }
    }

    public void recursao(String recursao_aux, int valchar)
    {
        boolean sair = false;
        setnome(recursao_aux);
        if(verificaparenteseabre(valchar))
        {
            algebrarelacional[getidentificador()].inicio_renomear_atributo();
            while(!sair) 
            {
                valchar = getchar();
                if(fimexpressao)
                {
                    msgerro = "Express\343o incompleta\nErro: Recurs\343o-001\nLinha: " + linha;
                    return;
                }
                while(verificaespaco(valchar)) 
                {
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: Recurs\343o-002\nLinha: " + linha;
                        return;
                    }
                }
                if(verificaletra(valchar) | verificanumeros(valchar))
                {
                    while(verificaletra(valchar) | verificanumeros(valchar)) 
                    {
                        setrenomearatributo(valchar);
                        valchar = getchar();
                        if(fimexpressao)
                        {
                            msgerro = "Express\343o incompleta\nErro: Recurs\343o-003\nLinha: " + linha;
                            return;
                        }
                    }
                    while(verificaespaco(valchar)) 
                    {
                        valchar = getchar();
                        if(fimexpressao)
                        {
                            msgerro = "Express\343o incompleta\nErro: Recurs\343o-004\nLinha: " + linha;
                            return;
                        }
                    }
                    if(valchar == 44)
                        setrenomearatributo(valchar);
                    else
                    if(verificaparentesefecha(valchar))
                    {
                        sair = true;
                    } else
                    {
                        setmsgerro("Caracter n\343o permitido neste local\nErro: Recurs\343o-005");
                        return;
                    }
                } else
                {
                    setmsgerro("Caracter n\343o permitido neste local\nErro: Recurs\343o-006");
                    return;
                }
            }
            valchar = getchar();
            if(fimexpressao)
            {
                msgerro = "Express\343o incompleta\nErro: Recurs\343o-007\nLinha: " + linha;
                return;
            }
            while(verificaespaco(valchar)) 
            {
                valchar = getchar();
                if(fimexpressao)
                {
                    msgerro = "Express\343o incompleta\nErro: Recurs\343o-008\nLinha: " + linha;
                    return;
                }
            }
            if(valchar == 945)
            {
                return;
            } else
            {
                setmsgerro("Caracter n\343o permitido neste local\nErro: Recurs\343o-009");
                return;
            }
        }
        if(valchar == 945)
        {
            return;
        } else
        {
            setmsgerro("Caracter n\343o permitido neste local\nErro: Recurs\343o-010");
            return;
        }
    }

    public void agregacao(String groupby_aux, int valchar)
    {
        boolean sair = false;
        int val_aux = 0;
        while(valchar == 44) 
        {
            groupby_aux = groupby_aux + expressao.charAt(counter);
            valchar = getchar();
            if(fimexpressao)
            {
                msgerro = "Express\343o incompleta\nErro: Agrega\347\343o-001\nLinha: " + linha;
                return;
            }
            while(verificaespaco(valchar)) 
            {
                groupby_aux = groupby_aux + expressao.charAt(counter);
                valchar = getchar();
                if(fimexpressao)
                {
                    msgerro = "Express\343o incompleta\nErro: Agrega\347\343o-002\nLinha: " + linha;
                    return;
                }
            }
            while(verificaletra(valchar) | verificanumeros(valchar)) 
            {
                groupby_aux = groupby_aux + expressao.charAt(counter);
                valchar = getchar();
                if(fimexpressao)
                {
                    msgerro = "Express\343o incompleta\nErro: Agrega\347\343o-003\nLinha: " + linha;
                    return;
                }
            }
            while(verificaespaco(valchar)) 
            {
                groupby_aux = groupby_aux + expressao.charAt(counter);
                valchar = getchar();
                if(fimexpressao)
                {
                    msgerro = "Express\343o incompleta\nErro: Agrega\347\343o-004\nLinha: " + linha;
                    return;
                }
            }
        }
        if(groupby_aux.length() != 0)
        {
            setgroupby(groupby_aux);
            for(int i = 0; i < groupby_aux.length(); i++)
                setatributo(groupby_aux.charAt(i));

            setatributo(44);
        }
        if(valchar == 922)
        {
            while(!sair) 
            {
                valchar = getchar();
                if(fimexpressao)
                {
                    msgerro = "Express\343o incompleta\nErro: Agrega\347\343o-005\nLinha: " + linha;
                    return;
                }
                while(verificaespaco(valchar)) 
                {
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: Agrega\347\343o-006\nLinha: " + linha;
                        return;
                    }
                }
                if(verificaletra(valchar))
                {
                    val_aux = verificaSUM(valchar);
                    if(val_aux == -1)
                    {
                        msgerro = "Express\343o incompleta\nErro: Agrega\347\343o-007\nLinha: " + linha;
                        return;
                    }
                    if(val_aux == 0)
                    {
                        val_aux = verificaAVERAGE(valchar);
                        if(val_aux == -1)
                        {
                            msgerro = "Express\343o incompleta\nErro: Agrega\347\343o-008\nLinha: " + linha;
                            return;
                        }
                        if(val_aux == 0)
                        {
                            val_aux = verificaMAXIMUM(valchar);
                            if(val_aux == -1)
                            {
                                msgerro = "Express\343o incompleta\nErro: Agrega\347\343o-009\nLinha: " + linha;
                                return;
                            }
                            if(val_aux == 0)
                            {
                                val_aux = verificaMINIMUM(valchar);
                                if(val_aux == -1)
                                {
                                    msgerro = "Express\343o incompleta\nErro: Agrega\347\343o-010\nLinha: " + linha;
                                    return;
                                }
                                if(val_aux == 0)
                                {
                                    val_aux = verificaCOUNT(valchar);
                                    if(val_aux == -1)
                                    {
                                        msgerro = "Express\343o incompleta\nErro: Agrega\347\343o-011\nLinha: " + linha;
                                        return;
                                    }
                                    if(val_aux == 0)
                                    {
                                        msgerro = "Nenhuma fun\347\343o foi encontrada\nErro: Agrega\347\343o-012\nLinha: " + linha;
                                        return;
                                    }
                                }
                            }
                        }
                    }
                } else
                {
                    setmsgerro("Caracter n\343o permitido neste local\nErro: Agrega\347\343o-013");
                    return;
                }
                valchar = getchar();
                if(fimexpressao)
                {
                    msgerro = "Express\343o incompleta\nErro: Agrega\347\343o-014\nLinha: " + linha;
                    return;
                }
                while(verificaespaco(valchar)) 
                {
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: Agrega\347\343o-015\nLinha: " + linha;
                        return;
                    }
                }
                if(verificaletra(valchar) | verificanumeros(valchar))
                {
                    setatributo(parentese_abre);
                    while(verificaletra(valchar) | verificanumeros(valchar)) 
                    {
                        setatributo(valchar);
                        valchar = getchar();
                        if(fimexpressao)
                        {
                            msgerro = "Express\343o incompleta\nErro: Agrega\347\343o-016\nLinha: " + linha;
                            return;
                        }
                    }
                    setatributo(parentese_fecha);
                } else
                {
                    setmsgerro("Caracter n\343o permitido neste local\nErro: Agrega\347\343o-017");
                    return;
                }
                while(verificaespaco(valchar)) 
                {
                    valchar = getchar();
                    if(fimexpressao)
                    {
                        msgerro = "Express\343o incompleta\nErro: Agrega\347\343o-018\nLinha: " + linha;
                        return;
                    }
                }
                if(verificaparenteseabre(valchar))
                {
                    counter--;
                    sair = true;
                } else
                if(valchar == 44)
                {
                    setatributo(valchar);
                    sair = false;
                } else
                {
                    setmsgerro("Caracter n\343o permitido neste local\nErro: Agrega\347\343o-019");
                    return;
                }
            }
        } else
        {
            setmsgerro("Caracter n\343o permitido neste local\nErro: Agrega\347\343o-020");
            return;
        }
    }

    public int verificaSUM(int valchar)
    {
        if((valchar == 83) | (valchar == 115))
        {
            valchar = getchar();
            if(fimexpressao)
                return -1;
            if((valchar == 85) | (valchar == 117))
            {
                valchar = getchar();
                if(fimexpressao)
                    return -1;
                if((valchar == 77) | (valchar == 109))
                {
                    valchar = getchar();
                    if(fimexpressao)
                        return -1;
                    if(verificaespaco(valchar))
                    {
                        setatributo(83);
                        setatributo(85);
                        setatributo(77);
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

    public int verificaAVERAGE(int valchar)
    {
        if((valchar == 65) | (valchar == 97))
        {
            valchar = getchar();
            if(fimexpressao)
                return -1;
            if((valchar == 86) | (valchar == 118))
            {
                valchar = getchar();
                if(fimexpressao)
                    return -1;
                if((valchar == 69) | (valchar == 101))
                {
                    valchar = getchar();
                    if(fimexpressao)
                        return -1;
                    if((valchar == 82) | (valchar == 114))
                    {
                        valchar = getchar();
                        if(fimexpressao)
                            return -1;
                        if((valchar == 65) | (valchar == 97))
                        {
                            valchar = getchar();
                            if(fimexpressao)
                                return -1;
                            if((valchar == 71) | (valchar == 103))
                            {
                                valchar = getchar();
                                if(fimexpressao)
                                    return -1;
                                if((valchar == 69) | (valchar == 101))
                                {
                                    valchar = getchar();
                                    if(fimexpressao)
                                        return -1;
                                    if(verificaespaco(valchar))
                                    {
                                        setatributo(65);
                                        setatributo(86);
                                        setatributo(71);
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

    public int verificaMAXIMUM(int valchar)
    {
        if((valchar == 77) | (valchar == 109))
        {
            valchar = getchar();
            if(fimexpressao)
                return -1;
            if((valchar == 65) | (valchar == 97))
            {
                valchar = getchar();
                if(fimexpressao)
                    return -1;
                if((valchar == 88) | (valchar == 120))
                {
                    valchar = getchar();
                    if(fimexpressao)
                        return -1;
                    if((valchar == 73) | (valchar == 105))
                    {
                        valchar = getchar();
                        if(fimexpressao)
                            return -1;
                        if((valchar == 77) | (valchar == 109))
                        {
                            valchar = getchar();
                            if(fimexpressao)
                                return -1;
                            if((valchar == 85) | (valchar == 117))
                            {
                                valchar = getchar();
                                if(fimexpressao)
                                    return -1;
                                if((valchar == 77) | (valchar == 109))
                                {
                                    valchar = getchar();
                                    if(fimexpressao)
                                        return -1;
                                    if(verificaespaco(valchar))
                                    {
                                        setatributo(77);
                                        setatributo(65);
                                        setatributo(88);
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

    public int verificaMINIMUM(int valchar)
    {
        if((valchar == 77) | (valchar == 109))
        {
            valchar = getchar();
            if(fimexpressao)
                return -1;
            if((valchar == 73) | (valchar == 105))
            {
                valchar = getchar();
                if(fimexpressao)
                    return -1;
                if((valchar == 78) | (valchar == 110))
                {
                    valchar = getchar();
                    if(fimexpressao)
                        return -1;
                    if((valchar == 73) | (valchar == 105))
                    {
                        valchar = getchar();
                        if(fimexpressao)
                            return -1;
                        if((valchar == 77) | (valchar == 109))
                        {
                            valchar = getchar();
                            if(fimexpressao)
                                return -1;
                            if((valchar == 85) | (valchar == 117))
                            {
                                valchar = getchar();
                                if(fimexpressao)
                                    return -1;
                                if((valchar == 77) | (valchar == 109))
                                {
                                    valchar = getchar();
                                    if(fimexpressao)
                                        return -1;
                                    if(verificaespaco(valchar))
                                    {
                                        setatributo(77);
                                        setatributo(73);
                                        setatributo(78);
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

    public int verificaCOUNT(int valchar)
    {
        if((valchar == 67) | (valchar == 99))
        {
            valchar = getchar();
            if(fimexpressao)
                return -1;
            if((valchar == 79) | (valchar == 111))
            {
                valchar = getchar();
                if(fimexpressao)
                    return -1;
                if((valchar == 85) | (valchar == 117))
                {
                    valchar = getchar();
                    if(fimexpressao)
                        return -1;
                    if((valchar == 78) | (valchar == 110))
                    {
                        valchar = getchar();
                        if(fimexpressao)
                            return -1;
                        if((valchar == 84) | (valchar == 116))
                        {
                            valchar = getchar();
                            if(fimexpressao)
                                return -1;
                            if(verificaespaco(valchar))
                            {
                                setatributo(67);
                                setatributo(79);
                                setatributo(85);
                                setatributo(78);
                                setatributo(84);
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
            linha++;
            counter++;
            posicao_inicial_linha = counter;
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

    public void setmsgerro(String msgerro_aux)
    {
        msgerro = msgerro_aux + "\n" + "Posi\347\343o: " + (counter - posicao_inicial_linha) + "\n" + "Linha: " + linha;
    }

    public void retiraquebralinha()
    {
        String aux = new String("");
        for(int counter = 0; counter < expressao.length(); counter++)
            if(quebralinha != expressao.charAt(counter))
                aux = aux + expressao.charAt(counter);

        expressao = aux;
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
        criaalgebra();
    }

    public void criaalgebra()
    {
        algebrarelacional[getidentificador()] = new AlgebraRelacional();
        algebrarelacional[getidentificador()].setcontadores();
    }

    public int setpredicado(int valchar, int contador)
    {
        contador = algebrarelacional[getidentificador()].setpredicado(valchar, contador);
        return contador;
    }

    public void setatributo(int valchar)
    {
        algebrarelacional[getidentificador()].setatributo(valchar);
    }

    public void setgroupby(String groupby_aux)
    {
        algebrarelacional[getidentificador()].setgroupby(groupby_aux);
    }

    public void setatributoSQL(String atributo_aux)
    {
        algebrarelacional[getidentificador()].setatributoSQL(atributo_aux);
    }

    public void setrenomearatributo(int valchar)
    {
        algebrarelacional[getidentificador()].setrenomearatributo(valchar);
    }

    public void setrenomearrelacao(String relacao_aux)
    {
        algebrarelacional[getidentificador()].setrenomearrelacao(relacao_aux);
    }

    public void setrelacao(String relacao_aux)
    {
        algebrarelacional[getidentificador()].setrelacao(relacao_aux);
    }

    public void setcondicoesdejoin(int valchar, boolean colocaand)
    {
        algebrarelacional[getidentificador()].setcondicoesdejoin(valchar, colocaand);
    }

    public void setoperaodorbinario(int valchar)
    {
        algebrarelacional[getidentificador()].setoperaodorbinario(valchar);
    }

    public void setnome(String nome_aux)
    {
        algebrarelacional[getidentificador()].setnome(nome_aux);
    }

    public void selecionaatributos(String relacao)
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            CriaConexao criaconexao = new CriaConexao();
            conn = criaconexao.getConnection(schema);
            stmt = conn.createStatement();
            String Query = "SELECT * FROM " + relacao;
            rs = stmt.executeQuery(Query);
            ResultSetMetaData rsmd = rs.getMetaData();
            for(int i = 1; i <= rsmd.getColumnCount(); i++)
                setatributoSQL(rsmd.getColumnName(i));

        }
        catch(Exception e)
        {
            msgerro = "Erro na sele\347\343o dos atributos da tabela: " + relacao + "\n" + "Erro: Seleciona Atributos-001";
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
    boolean pode_ter_recursao;
    int linha;
    int posicao_inicial_linha;
    AlgebraRelacional algebrarelacional[];
}
