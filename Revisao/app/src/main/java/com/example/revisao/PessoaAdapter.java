package com.example.revisao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revisao.databinding.FragmentItem1Binding;
import com.example.revisao.databinding.ItemPessoaBinding;

import java.util.ArrayList;
import java.util.List;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.ViewHolder> {
    private List<Pessoa> pessoas = new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPessoaBinding binding = ItemPessoaBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pessoa pessoa = pessoas.get(position);
        holder.binding.textNome.setText("Nome: "+ pessoa.getNome());
        holder.binding.textAno.setText("Ano de Nascimento: "+ pessoa.getAnoNascimento());
        holder.binding.textAltura.setText("Altura: "+ pessoa.getAltura());
        holder.binding.textMassa.setText("Massa: "+ pessoa.getMassa());


    }

    @Override
    public int getItemCount() {
        return pessoas.size();
    }
    public void atualizaAdapter(List<Pessoa> pessoasNovo){
        pessoas.clear();
        if(pessoasNovo != null){
            pessoas.addAll(pessoasNovo);
            notifyDataSetChanged();
        }

    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
       ItemPessoaBinding binding;
        public ViewHolder(ItemPessoaBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
