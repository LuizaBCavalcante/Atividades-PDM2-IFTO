package com.example.tarefa4;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<List<Student>> listaEstudantes = new MutableLiveData<>();
    private final Repositorio repositorio = new Repositorio();
    private MutableLiveData<String> mensagem = new MutableLiveData<>();

    public LiveData<List<Student>> getListaEstudantes(){
        return listaEstudantes;
    }

    public void carregarDados(){
        repositorio.obterDados(new Repositorio.Callback() {
            @Override
            public void sucesso(List<Student> students) {
                listaEstudantes.postValue(students);
            }
            @Override
            public void erro(String msg) {
                mensagem.postValue(msg);
            }
        });
    }

    public String obterMediaGeral(){
        List<Student> alunos = listaEstudantes.getValue();
        if(alunos == null || alunos.isEmpty()) return "Sem dados";

        float somaGeral = 0;
        for(Student aluno : alunos){
            somaGeral += aluno.getMedia();
        }
        float mediaGeral = somaGeral/alunos.size();
        return  String.format("%.2f", mediaGeral);
    }

}
