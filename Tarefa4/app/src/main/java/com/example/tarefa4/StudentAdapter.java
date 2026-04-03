package com.example.tarefa4;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarefa4.databinding.ItemStatusBinding;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private List<Student> students = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStatusBinding binding = ItemStatusBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student student = students.get(position);
        holder.binding.setStudent(student);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {

        return students.size();
    }
    public void atualizaAdapter(List<Student> novaLista) {
        students.clear();
        students.addAll(novaLista);

        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ItemStatusBinding binding;
        public ViewHolder(ItemStatusBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
