package com.example.revisao;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revisao.databinding.ItemIdadeBinding;

import java.util.ArrayList;
import java.util.List;

public class IdadeAdapter extends RecyclerView.Adapter<IdadeAdapter.ViewHolder>{
    private List<Pessoa> pessoas = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemIdadeBinding binding = ItemIdadeBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IdadeAdapter.ViewHolder holder, int position) {
        Pessoa pessoa = pessoas.get(position);
        holder.binding.textNome.setText("Nome: "+ pessoa.getNome());
        holder.binding.textIdade.setText("Idade: "+ pessoa.calcularIdade() + " anos");

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
        ItemIdadeBinding binding;
        public  ViewHolder(ItemIdadeBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
