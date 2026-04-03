package com.example.tarefa5;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissao{
    public static boolean validaPermissao(String[] permissoes, Activity activity, int requestCode){
        List<String> listaPermissoes = new ArrayList<>();

        for(String p : permissoes){
            boolean temPermissao = ContextCompat.checkSelfPermission(activity, p) == PackageManager.PERMISSION_GRANTED;
            if(!temPermissao){
                listaPermissoes.add(p);
            }
        }
        if(listaPermissoes.isEmpty()){
            return true;
        }

        String[] novasPermissoes = new String[listaPermissoes.size()];
        listaPermissoes.toArray(novasPermissoes);
        ActivityCompat.requestPermissions(activity, novasPermissoes, requestCode);
        return false;
    }
}
