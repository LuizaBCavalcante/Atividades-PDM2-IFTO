package com.example.tarefa3;

import java.io.Serializable;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Student{

    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("idade")
    @Expose
    private Integer idade;
    @SerializedName("notas")
    @Expose
    private List<Double> notas;
    @SerializedName("presenca")
    @Expose
    private List<Boolean> presenca;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public void setNotas(List<Double> notas) {
        this.notas = notas;
    }

    public List<Boolean> getPresenca() {
        return presenca;
    }

    public void setPresenca(List<Boolean> presenca) {
        this.presenca = presenca;
    }
    public double getMedia(){
        if(notas == null || notas.isEmpty()){
            return 0;
        }
        double soma = 0;
        for(Double n : notas){
            soma += n;
        }
        return soma /notas.size();
    }
    public double getPercentualPresenca(){
        if(presenca == null || presenca.isEmpty()){
            return 0;
        }
        double cont = 0;
        for(Boolean p : presenca){
            if(p) cont++;
        }
        return  (cont/presenca.size())*100;
    }

    public String getStatus(){
        if(getMedia() >= 6.0 && getPercentualPresenca() >=75){
            return "Aprovado";
        }
        return "Reprovado";

    }

    @Override
    public String toString() {
        return "Student{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}