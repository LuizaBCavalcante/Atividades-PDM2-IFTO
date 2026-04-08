package com.example.revisao;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conexao {
    public InputStream obterRespostaHTTP(String end) {
        HttpURLConnection conexao = null;
        try {
            URL url = new URL(end);
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setConnectTimeout(50000);
            conexao.setReadTimeout(50000);
            conexao.setRequestMethod("GET");
            conexao.connect();
            return new BufferedInputStream(conexao.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}