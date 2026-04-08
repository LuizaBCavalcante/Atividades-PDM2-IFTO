package com.example.revisao;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Repositorio {
    private static final String URL = "https://raw.githubusercontent.com/LuizaBCavalcante/revisao/refs/heads/main/db.json";
    private ExecutorService executor;
    private Handler mainHandler;
    public Repositorio() {
        this.executor = Executors.newSingleThreadExecutor();
        this.mainHandler = new Handler(Looper.getMainLooper());
    }
    public interface Callback {
        void sucesso(List<Pessoa> lista);
        void erro(String mensagem);
    }
    private void sucesso(Callback c, List<Pessoa> lista) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                c.sucesso(lista);
            }
        });
    }
    private void erro(Callback c, String mensagem) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                c.erro(mensagem);
            }
        });
    }
    public void obterDados(Callback c) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Conexao conexao = new Conexao();
                    InputStream inputStream = conexao.obterRespostaHTTP(URL);
                    Conversao conversao = new Conversao();
                    String jsonString = conversao.converter(inputStream);
                    if (jsonString == null || jsonString.isEmpty()) {
                        erro(c, "Erro - Não existe informações disponíveis");
                        return;
                    }
                    Gson gson = new Gson();
                    Example resposta = gson.fromJson(jsonString, Example.class);
                    List<Pessoa> listaPessoas= new ArrayList<>();
                    if (resposta != null && resposta.getPessoas() != null) {
                           listaPessoas.addAll(resposta.getPessoas());
                    }
                    sucesso(c, listaPessoas);
                } catch (Exception e) {
                    e.printStackTrace();
                    erro(c, "Erro - Não foi possível realizar o download" + e.getMessage());
                }
            }
        });
    }//
}//class
