package com.example.tarefa3;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Student implements Parcelable {

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

    protected Student(Parcel in) {
        nome = in.readString();
        if (in.readByte() == 0) {
            idade = null;
        } else {
            idade = in.readInt();
        }
        notas = new ArrayList<Double>();
        in.readList(notas, Double.class.getClassLoader());

        presenca = new ArrayList<Boolean>();
        in.readList(presenca, Boolean.class.getClassLoader());
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        if (idade == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idade);
        }
        dest.writeList(notas);
        dest.writeList(presenca);
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}