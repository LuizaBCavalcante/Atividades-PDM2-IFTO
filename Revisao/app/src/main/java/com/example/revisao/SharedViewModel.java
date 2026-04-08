package com.example.revisao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SharedViewModel extends ViewModel {
    private Repositorio repositorio;
    private final MutableLiveData<List<Pessoa>> listaPessoas;
    private final MutableLiveData<String> mensagem;

    public SharedViewModel(){
        listaPessoas = new MutableLiveData<>();
        mensagem = new MutableLiveData<>();
        repositorio = new Repositorio();
    }
    public Repositorio getRepositorio() {
        return repositorio;
    }
    public void setRepositorio(Repositorio repositorio) {
        this.repositorio = repositorio;
    }
    public LiveData<List<Pessoa>> getListaPessoas() {
        return listaPessoas;
    }
    public LiveData<String> getMensagem() {
        return mensagem;
    }
    public void carregarDados(){
        repositorio.obterDados(new Repositorio.Callback() {
            @Override
            public void sucesso(List<Pessoa> pessoas) {
                listaPessoas.postValue(pessoas);
                mensagem.postValue("Dados baixados com sucesso!");
            }
            @Override
            public void erro(String msgErro) {
                mensagem.postValue(msgErro);
            }

    });
    }
    public double getMediaIMC() {
        List<Pessoa> lista = listaPessoas.getValue();
        if (lista == null || lista.isEmpty()) return 0.0;

        double soma = 0;
        for (Pessoa p : lista) {
            soma += p.calcularIMC();
        }
        return soma / lista.size();
    }

    public int[] getMaiorMenorIdade() {
        List<Pessoa> lista = listaPessoas.getValue();
        if (lista == null || lista.isEmpty()) return new int[]{0, 0};

        int maior = Integer.MIN_VALUE;
        int menor = Integer.MAX_VALUE;

        for (Pessoa p : lista) {
            int idade = p.calcularIdade();
            if (idade > maior) maior = idade;
            if (idade < menor) menor = idade;
        }
        return new int[]{maior, menor};
    }


}
