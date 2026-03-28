package com.example.tarefa4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tarefa4.databinding.FragmentStatusBinding;

import java.util.List;

public class StatusFragment extends Fragment {
    private FragmentStatusBinding binding;
    private SharedViewModel viewModel;
    private StudentAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentStatusBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        configurarRecyclerView();

        viewModel.getListaEstudantes().observe(getViewLifecycleOwner(), new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> lista) {
                if(lista != null){
                    adapter.atualizaAdapter(lista);
                }
            }
        });
        return binding.getRoot();
    }

    private void configurarRecyclerView() {
        adapter = new StudentAdapter();
        binding.rvStatus.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvStatus.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}