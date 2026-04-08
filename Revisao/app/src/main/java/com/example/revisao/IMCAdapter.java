package com.example.revisao;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.revisao.databinding.ItemImcBinding;

import java.util.ArrayList;
import java.util.List;


public class IMCAdapter extends RecyclerView.Adapter<IMCAdapter.ViewHolder> {
    private List<Pessoa> pessoas = new ArrayList<>();
    @NonNull
    @Override
    public IMCAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemImcBinding binding = ItemImcBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IMCAdapter.ViewHolder holder, int position) {
        Pessoa pessoa = pessoas.get(position);
        holder.binding.textNome.setText("Nome: " + pessoa.getNome());
        double valor = pessoa.calcularIMC();
        holder.binding.textValorImc.setText(String.format("Valor do IMC: %.2f", valor));
        holder.binding.textClassificacaoImc.setText("Classificação: " + pessoa.getTipo(valor));

    }

    @Override
    public int getItemCount() {
        return pessoas.size();
    }

    public void atualizaAdapter(List<Pessoa> pessoasNovo){
        pessoas.clear();
        if(pessoas != null){
            pessoas.addAll(pessoasNovo);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemImcBinding binding;
        public  ViewHolder(ItemImcBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
