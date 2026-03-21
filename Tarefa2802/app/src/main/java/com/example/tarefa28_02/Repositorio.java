package com.example.tarefa28_02;

import android.os.Handler;
import android.os.Looper;
import com.google.gson.Gson;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Repositorio {
    private static final String URL = "https://raw.githubusercontent.com/LuizaBCavalcante/aula-pdm2-json/refs/heads/main/db.json";
    private ExecutorService executor;
    public Repositorio() {
       executor = Executors.newSingleThreadExecutor();
    }
    public interface Callback{
        void sucesso(RespostaAPI resposta);
        void erro(String mensagem);
    }
    public void obterDados(Callback c){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    Conexao conexao = new Conexao();
                    InputStream inputStream = conexao.obterRespostaHTTP(URL);
                    Conversao conversao = new Conversao();
                    String jsonString = conversao.converter(inputStream);
                    if (jsonString == null || jsonString.isEmpty()) {
                        c.erro("Resposta vazia");
                        return;
                    }
                    Gson gson = new Gson();
                    RespostaAPI resposta = gson.fromJson(jsonString, RespostaAPI.class);
                    c.sucesso(resposta);
                }catch (Exception e) {
                    e.printStackTrace();
                    c.erro("Erro ao baixar: " + e.getMessage());
                }
            }
        });
    }//
    public void shutdown() {
        executor.shutdown();
    }
}//class


