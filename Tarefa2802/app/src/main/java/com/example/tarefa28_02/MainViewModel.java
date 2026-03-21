package com.example.tarefa28_02;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends ViewModel {
    private Repositorio repositorio;
    private MutableLiveData<List<Agenda>> lista;
    private MutableLiveData<String> msg;

    public MainViewModel() {
        repositorio = new Repositorio();
        lista = new MutableLiveData<>();
        msg = new MutableLiveData<>();
    }
    public LiveData<List<Agenda>> getLista(){
        return lista;
    }
    public LiveData<String> getMsg(){
        return msg;
    }

    public void carregarDados() {
        repositorio.obterDados(new Repositorio.Callback() {
            @Override
            public void sucesso(RespostaAPI resposta) {
                if(resposta != null && resposta.getAgenda() != null){
                    lista.postValue(resposta.getAgenda());
                        }
                    }
            @Override
            public void erro(String mensagem) {
                msg.postValue("Falha ao carregar detalhes: " + mensagem);
            }
        });
    } //carregarDados
    @Override
    protected void onCleared() {
        super.onCleared();
        repositorio.shutdown();
    }
} //class
