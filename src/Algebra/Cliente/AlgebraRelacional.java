package Algebra.Cliente;

public class AlgebraRelacional
{

    public AlgebraRelacional()
    {
        nome = null;
        predicado = new String[100];
        atributo = new String[100][100];
        renomear_atributo = new String[100][100];
        renomear_relacao = new String[100];
        relacao = new String[100];
        condicoes_de_join = new String[100];
        group_by = new String[100];
        operadorbinario = new int[100];
        SQL = new String();
        cont_atributo = 0;
        cont_renomear_atributo = 0;
        cont_renomear_relacao = 0;
        cont_relacao = 0;
        cont_operador_binario = 0;
        resultado = false;
    }

    public void setnome(String nome_aux)
    {
        nome = nome_aux.trim();
        if(nome.trim().equals("resultado") | nome.trim().equals("RESULTADO"))
            resultado = true;
    }

    public int setpredicado(int valchar, int contador)
    {
        if(contador == 0)
        {
            if(predicado[getcont_relacao()] == null)
                predicado[getcont_relacao()] = (new Character((char)valchar)).toString();
            else
                predicado[getcont_relacao()] = predicado[getcont_relacao()] + " AND " + (new Character((char)valchar)).toString();
        } else
        {
            predicado[getcont_relacao()] = predicado[getcont_relacao()] + (new Character((char)valchar)).toString();
        }
        return ++contador;
    }

    public void setatributo(int valchar)
    {
        if(valchar == 44)
        {
            setcont_atributo();
        } else
        {
            if(atributo[getcont_relacao()][getcont_atributo()] == null)
                atributo[getcont_relacao()][getcont_atributo()] = "";
            atributo[cont_relacao][cont_atributo] = atributo[cont_relacao][cont_atributo] + (new Character((char)valchar)).toString();
        }
    }

    public void setgroupby(String groupby_aux)
    {
        if(group_by[getcont_relacao()] != null)
            group_by[getcont_relacao()] = group_by[getcont_relacao()] + ", " + groupby_aux;
        else
            group_by[getcont_relacao()] = groupby_aux;
    }

    public void setatributoSQL(String atributo_aux)
    {
        setcont_atributo();
        atributo[getcont_relacao()][getcont_atributo()] = atributo_aux;
    }

    public void setrenomearatributo(int valchar)
    {
        if(valchar == 44)
        {
            setcont_renomear_atributo();
        } else
        {
            if(renomear_atributo[getcont_relacao()][getcont_renomear_atributo()] == null)
                renomear_atributo[getcont_relacao()][getcont_renomear_atributo()] = "";
            renomear_atributo[getcont_relacao()][getcont_renomear_atributo()] = renomear_atributo[getcont_relacao()][getcont_renomear_atributo()] + (new Character((char)valchar)).toString();
        }
    }

    public void setrenomearrelacao(String relacao_aux)
    {
        renomear_relacao[getcont_relacao()] = relacao_aux;
    }

    public void setrelacao(String relacao_aux)
    {
        relacao[getcont_relacao()] = relacao_aux;
        setcont_relacao();
    }

    public void setcondicoesdejoin(int valchar, boolean colocaand)
    {
        if(!colocaand)
        {
            if(condicoes_de_join[getcont_operador_binario()] == null)
                condicoes_de_join[getcont_operador_binario()] = "";
            condicoes_de_join[getcont_operador_binario()] = condicoes_de_join[getcont_operador_binario()] + (new Character((char)valchar)).toString();
        } else
        {
            condicoes_de_join[getcont_operador_binario()] = condicoes_de_join[getcont_operador_binario()] + "AND ";
        }
    }

    public void setoperaodorbinario(int valchar)
    {
        setcont_operador_binario();
        operadorbinario[getcont_operador_binario()] = valchar;
    }

    public boolean isresultado()
    {
        return resultado;
    }

    public void setisresultado()
    {
        resultado = true;
    }

    public void setcont_atributo()
    {
        cont_atributo++;
    }

    public void setcont_renomear_atributo()
    {
        cont_renomear_atributo++;
    }

    public void setcont_renomear_relacao()
    {
        cont_renomear_relacao++;
    }

    public void setcont_relacao()
    {
        cont_relacao++;
    }

    public void setcont_operador_binario()
    {
        cont_operador_binario++;
    }

    public int getcont_atributo()
    {
        return cont_atributo;
    }

    public int getcont_renomear_atributo()
    {
        return cont_renomear_atributo;
    }

    public int getcont_renomear_relacao()
    {
        return cont_renomear_relacao;
    }

    public int getcont_relacao()
    {
        return cont_relacao;
    }

    public int getcont_operador_binario()
    {
        return cont_operador_binario;
    }

    public void inicio_renomear_atributo()
    {
        cont_renomear_atributo = cont_atributo;
    }

    public void setcontadores()
    {
        cont_relacao = 0;
        cont_atributo = 0;
        cont_renomear_atributo = 0;
        cont_operador_binario = -1;
    }

    public String getsql()
    {
        if(SQL.length() == 0)
            SQL = gerasql();
        return SQL;
    }

    public String gerasql()
    {
        String resultado = "";
        String atributo_aux = "";
        String predicado_aux = "";
        String groupby_aux = "";
        String relacao_aux = "";
        String condicoes_de_join_aux = "";
        for(int cont_rel = 0; cont_rel < tamanhorelacao(); cont_rel++)
        {
            atributo_aux = getatributos(cont_rel, atributo_aux);
            predicado_aux = getpredicado(cont_rel, predicado_aux);
            groupby_aux = getgroupby(cont_rel, groupby_aux);
            relacao_aux = getrelacao(cont_rel, relacao_aux);
            if(condicoes_de_join_aux.length() != 0)
            {
                relacao_aux = relacao_aux + condicoes_de_join_aux;
                condicoes_de_join_aux = "";
            }
            if(operadorbinario[cont_rel] != 0)
                if((operadorbinario[cont_rel] == 912) | (operadorbinario[cont_rel] == 913) | (operadorbinario[cont_rel] == 914))
                {
                    resultado = resultado + montasql(atributo_aux, predicado_aux, groupby_aux, relacao_aux) + " ";
                    resultado = resultado + operadorbinarioSQL(operadorbinario[cont_rel]) + " ";
                    atributo_aux = "";
                    predicado_aux = "";
                    groupby_aux = "";
                    relacao_aux = "";
                } else
                {
                    condicoes_de_join_aux = getcondicoes_de_join(cont_rel);
                    relacao_aux = relacao_aux + operadorbinarioSQL(operadorbinario[cont_rel]) + " ";
                }
        }

        resultado = resultado + montasql(atributo_aux, predicado_aux, groupby_aux, relacao_aux);
        return resultado;
    }

    public int tamanhorelacao()
    {
        int i;
        for(i = 0; i < relacao.length; i++)
            if(relacao[i] == null)
                return i;

        return ++i;
    }

    public String montasql(String atributo_aux, String predicado_aux, String groupby_aux, String relacao_aux)
    {
        String resultado = "SELECT ";
        if(atributo_aux.length() != 0)
            resultado = resultado + atributo_aux.trim() + " ";
        else
            resultado = resultado + "* ";
        resultado = resultado + "FROM " + relacao_aux.trim() + " ";
        if(predicado_aux.length() != 0)
            resultado = resultado + "WHERE " + predicado_aux.trim() + " ";
        if(groupby_aux.length() != 0)
            resultado = resultado + "GROUP BY " + groupby_aux.trim() + " ";
        return resultado.trim();
    }

    public String getcondicoes_de_join(int cont_rel)
    {
        String condicoes_de_join_aux = "";
        if(condicoes_de_join[cont_rel] != null)
            condicoes_de_join_aux = "ON (" + condicoes_de_join[cont_rel].toUpperCase() + ") ";
        return condicoes_de_join_aux;
    }

    public String getrelacao(int cont_rel, String relacao_aux)
    {
        relacao_aux = relacao_aux + "#" + relacao[cont_rel] + "#" + " ";
        if(renomear_relacao[cont_rel] != null)
            relacao_aux = relacao_aux + "AS " + renomear_relacao[cont_rel].toUpperCase() + " ";
        return relacao_aux;
    }

    public String getatributos(int cont_rel, String atributo_aux)
    {
        int contador_aux = 0;
        String atributo_relacao = "";
        for(int j = 0; j < atributo[cont_rel].length; j++)
            if(atributo[cont_rel][j] != null)
            {
                if(contador_aux == 0)
                    atributo_relacao = atributo_relacao + atributo[cont_rel][j].trim().toUpperCase() + " ";
                else
                    atributo_relacao = atributo_relacao + ", " + atributo[cont_rel][j].trim().toUpperCase() + " ";
                if(renomear_atributo[cont_rel][j] != null)
                    atributo_relacao = atributo_relacao + "AS " + renomear_atributo[cont_rel][j].trim().toUpperCase() + " ";
                contador_aux++;
            }

        if(atributo_relacao.length() != 0)
            if(atributo_aux.length() != 0)
                atributo_aux = atributo_aux + ", " + atributo_relacao;
            else
                atributo_aux = atributo_relacao;
        return atributo_aux;
    }

    public String getpredicado(int cont_rel, String predicado_aux)
    {
        if(predicado[cont_rel] != null)
            if(predicado_aux.length() != 0)
                predicado_aux = predicado_aux + "AND " + predicado[cont_rel].trim().toUpperCase();
            else
                predicado_aux = predicado[cont_rel].toUpperCase();
        return predicado_aux;
    }

    public String getgroupby(int cont_rel, String groupby_aux)
    {
        if(group_by[cont_rel] != null)
            if(groupby_aux.length() != 0)
                groupby_aux = groupby_aux + ", " + group_by[cont_rel].trim().toUpperCase();
            else
                groupby_aux = group_by[cont_rel].trim().toUpperCase();
        return groupby_aux;
    }

    public String operadorbinarioSQL(int valchar)
    {
        String retorno = "";
        switch(valchar)
        {
        case 912: 
            retorno = "UNION";
            break;

        case 915: 
            retorno = "JOIN";
            break;

        case 916: 
            retorno = "JOIN";
            break;

        case 917: 
            retorno = "LEFT OUTER JOIN";
            break;

        case 918: 
            retorno = "LEFT OUTER JOIN";
            break;

        case 919: 
            retorno = "RIGHT OUTER JOIN";
            break;

        case 923: 
            retorno = "NATURAL JOIN";
            break;
        }
        return retorno;
    }

    public String getnome()
    {
        if(nome == null)
            nome = "";
        return nome;
    }

    public void impressao()
    {
        System.out.print("Nome: ");
        System.out.print(nome);
        System.out.print(" | ");
        System.out.print("Atributo: ");
        for(int j = 0; j < atributo.length; j++)
        {
            for(int j2 = 0; j2 < atributo[j].length; j2++)
                if(atributo[j][j2] != null)
                    System.out.print(j + "." + j2 + "=" + atributo[j][j2] + " ");

        }

        System.out.print(" | ");
        System.out.print("Renomear Atributo: ");
        for(int j = 0; j < renomear_atributo.length; j++)
        {
            for(int j2 = 0; j2 < renomear_atributo[j].length; j2++)
                if(renomear_atributo[j][j2] != null)
                    System.out.print(j + "." + j2 + "=" + renomear_atributo[j][j2] + " ");

        }

        System.out.print(" | ");
        System.out.print("Predicado: ");
        for(int j = 0; j < predicado.length; j++)
            if(predicado[j] != null)
                System.out.print(j + "=" + predicado[j] + " ");

        System.out.print(" | ");
        System.out.print("Rela\347\343o: ");
        for(int j = 0; j < relacao.length; j++)
            if(relacao[j] != null)
                System.out.print(j + "=" + relacao[j] + " ");

        System.out.print(" | ");
        System.out.print("Renomear rela\347\343o: ");
        for(int j = 0; j < renomear_relacao.length; j++)
            if(renomear_relacao[j] != null)
                System.out.print(j + "=" + renomear_relacao[j] + " ");

        System.out.print(" | ");
        System.out.print("Condi\347\365es de join: ");
        for(int j = 0; j < condicoes_de_join.length; j++)
            if(condicoes_de_join[j] != null)
                System.out.print(j + "=" + condicoes_de_join[j] + " ");

        System.out.print(" | ");
        System.out.print("Operador binario: ");
        for(int j = 0; j < operadorbinario.length; j++)
            if(operadorbinario[j] != 0)
                System.out.print(j + "=" + operadorbinario[j] + " ");

        System.out.print(" | ");
        System.out.print("GroupBy: ");
        for(int j = 0; j < group_by.length; j++)
            if(group_by[j] != null)
                System.out.print(j + "=" + group_by[j] + " ");

    }

    String nome;
    String predicado[];
    String atributo[][];
    String renomear_atributo[][];
    String renomear_relacao[];
    String relacao[];
    String condicoes_de_join[];
    String group_by[];
    int operadorbinario[];
    String SQL;
    int cont_atributo;
    int cont_renomear_atributo;
    int cont_renomear_relacao;
    int cont_relacao;
    int cont_operador_binario;
    boolean resultado;
}
