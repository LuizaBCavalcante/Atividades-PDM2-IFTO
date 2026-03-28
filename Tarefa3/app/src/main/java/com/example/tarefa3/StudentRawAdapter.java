package com.example.tarefa3;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarefa3.databinding.ItemStudentCruBinding;

import java.util.ArrayList;
import java.util.List;

public class StudentRawAdapter extends RecyclerView.Adapter<com.example.tarefa3.StudentRawAdapter.ViewHolder> {
                private List<Student> students = new ArrayList<>();

                @NonNull
                @Override
                public com.example.tarefa3.StudentRawAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                    ItemStudentCruBinding binding = ItemStudentCruBinding.inflate(
                            LayoutInflater.from(parent.getContext()), parent, false);
                    return new com.example.tarefa3.StudentRawAdapter.ViewHolder(binding);
                }

                @Override
                public void onBindViewHolder(@NonNull com.example.tarefa3.StudentRawAdapter.ViewHolder holder, int position) {
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
                    ItemStudentCruBinding binding;
                    public ViewHolder(ItemStudentCruBinding binding) {
                        super(binding.getRoot());
            this.binding = binding;
        }
    }
}
