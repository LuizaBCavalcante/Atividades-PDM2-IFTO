package com.example.revisao;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Pessoa {

    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("ano_nascimento")
    @Expose
    private Integer anoNascimento;
    @SerializedName("altura")
    @Expose
    private Double altura;
    @SerializedName("massa")
    @Expose
    private Double massa;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getMassa() {
        return massa;
    }

    public void setMassa(Double massa) {
        this.massa = massa;
    }

    public double calcularIMC(){
       return massa/(altura*altura);
    }
    public String getTipo(double imc){
        if(imc<18.5){
            return "Abaixo do Peso";
        } else if (imc<=24.9){
            return "Peso normal";
        } else if (imc<=29.9){
            return "Sobrepeso";
        } else if (imc<=34.9){
            return "Obesidade Grau I";
        } else if (imc<=39.9){
            return "Obesidade Grau II";
        } else{
            return "Obesidade Grau III (mórbida)";
        }
    }

    public int calcularIdade(){
        int anoAtual = java.time.LocalDate.now().getYear();
        return anoAtual - this.anoNascimento;
    }
}