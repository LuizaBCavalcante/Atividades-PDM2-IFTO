package com.example.tarefa4;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarefa4.databinding.ItemStudentRawBinding;

import java.util.ArrayList;
import java.util.List;

public class StudentRawAdapter extends RecyclerView.Adapter<StudentRawAdapter.ViewHolder> {
    private List<Student> students = new ArrayList<>();

    @NonNull
    @Override
    public StudentRawAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemStudentRawBinding binding = ItemStudentRawBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new StudentRawAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentRawAdapter.ViewHolder holder, int position) {
        holder.binding.setStudent(students.get(position));
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemStudentRawBinding binding;
        public ViewHolder(ItemStudentRawBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}