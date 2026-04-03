package com.example.tarefa4;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Conexao {
    public InputStream obterRespostaHTTP(String endURL){
        HttpURLConnection conexao = null;
        try {
            URL url = new URL(endURL);
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setConnectTimeout(10000);
            conexao.setReadTimeout(20000);
            conexao.setRequestMethod("GET");
            conexao.connect();
            return new BufferedInputStream(conexao.getInputStream());
        }catch(MalformedURLException e){
            e.printStackTrace();

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;

    }
}