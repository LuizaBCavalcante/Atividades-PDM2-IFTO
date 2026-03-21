package com.example.tarefa3;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    private Repositorio repositorio;
    private MutableLiveData<List<Student>> listaEstudantes;
    private MutableLiveData<String> mensagem;


    public MainViewModel() {
        this.repositorio = new Repositorio();
        listaEstudantes = new MutableLiveData<>();
        mensagem = new MutableLiveData<>();

    }

    public MutableLiveData<List<Student>> getListaEstudantes() {
        return listaEstudantes;
    }
    public MutableLiveData<String> getMensagem() {
        return mensagem;
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
    @Override
    protected void onCleared(){
        super.onCleared();
        repositorio.shutdown();
    }
}
