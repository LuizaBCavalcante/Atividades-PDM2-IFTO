package com.example.tarefa3;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SegundaViewModel extends ViewModel {
    private MutableLiveData<List<Student>> listaEstudantes = new MutableLiveData<>();

    public void setListaEstudantes(List<Student> listaEstudantes) {
        this.listaEstudantes.setValue(listaEstudantes);
    }

    public MutableLiveData<List<Student>> getListaEstudantes() {
        return listaEstudantes;
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