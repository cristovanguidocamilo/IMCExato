package com.cristovancamilo.imcexato;

import android.content.Context;
import android.content.SharedPreferences;

public class CadastroPreferencias {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String NOME_ARQUIVO = "cadastro.preferencias";
    private final String CHAVE_NOME = "nome";
    private final String CHAVE_SEXO = "sexo";
    private final String CHAVE_NASCIMENTO = "nascimento";

    public CadastroPreferencias(Context c) {
        this.context = c;
        preferences = context.getSharedPreferences(NOME_ARQUIVO, 0);
        editor = preferences.edit();
    }

    public void salvarPessoa(String nome, String sexo, String nascimento ) {

        editor.putString(CHAVE_NOME, nome);
        editor.putString(CHAVE_SEXO, sexo);
        editor.putString(CHAVE_NASCIMENTO, nascimento);
        editor.commit();

    }

    public String recuperarNome() {
        return preferences.getString(CHAVE_NOME, "");
    }

    public String recuperarSexo() {
        return  preferences.getString(CHAVE_SEXO, "");
    }

    public String recuperarNascimento() {
        return  preferences.getString(CHAVE_NASCIMENTO, "");
    }

}
