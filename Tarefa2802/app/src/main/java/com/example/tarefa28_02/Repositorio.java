package com.example.tarefa28_02;

import android.os.Handler;
import android.os.Looper;
import com.google.gson.Gson;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Repositorio {
    private static final String URL = "https://raw.githubusercontent.com/LuizaBCavalcante/aula-pdm2-json/refs/heads/main/db.json";
    private ExecutorService executor;
    private Handler mainHandler;
    public Repositorio() {
        this.executor = Executors.newSingleThreadExecutor();
        this.mainHandler = new Handler(Looper.getMainLooper());
    }
    public interface Callback{
        void sucesso(RespostaAPI resposta);
        void erro(String mensagem);
    }
    private void sucesso(Callback c, RespostaAPI resposta){
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                c.sucesso(resposta);
            }
        });
    }
    private void erro(Callback c, String mensagem){
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                c.erro(mensagem);
            }
        });
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
                        erro(c, "Resposta vazia");
                        return;
                    }
                    Gson gson = new Gson();
                    RespostaAPI resposta = gson.fromJson(jsonString, RespostaAPI.class);
                    sucesso(c, resposta);
                }catch (Exception e) {
                    e.printStackTrace();
                    erro(c, "erro ao baixar: " + e.getMessage());
                }
            }
        });
    }//
}//class


