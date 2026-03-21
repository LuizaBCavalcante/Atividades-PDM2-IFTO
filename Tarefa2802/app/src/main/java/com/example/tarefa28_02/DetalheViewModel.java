package com.example.tarefa28_02;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DetalheViewModel extends ViewModel {
    private MutableLiveData<Agenda> contato;
    private MutableLiveData<String> email;
    private Repositorio repositorio;
    private MutableLiveData<String> msg;

    public DetalheViewModel() {
        contato = new MutableLiveData<>();
        email = new MutableLiveData<>();
        repositorio = new Repositorio();
        msg = new MutableLiveData<>();
    }

    public LiveData<Agenda> getContato() {
        return contato;
    }

    public LiveData<String> getEmail() {
        return email;
    }
    public LiveData<String> getMsg(){
        return msg;
    }

    public void carregarDados(int id) {
        repositorio.obterDados(new Repositorio.Callback() {
            @Override
            public void sucesso(RespostaAPI resposta) {
                if(resposta.getAgenda() != null){
                    for(Agenda agenda : resposta.getAgenda()){
                        if(agenda.getId().equals(id)){
                            contato.postValue(agenda);
                            break;
                        }
                    }
                }
                if(resposta.getAdicionais() != null){
                    for(Adicional adicional : resposta.getAdicionais()){
                        if(adicional.getId().equals(id)){
                            email.postValue(adicional.getEmail());
                            break;
                        }
                    }
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

