package com.example.revisao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.revisao.databinding.FragmentItem2Binding;

import java.util.List;

public class FragmentItem2 extends Fragment {

    private SharedViewModel viewModel;
    private FragmentItem2Binding binding;
    private IdadeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentItem2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        adapter = new IdadeAdapter();
        binding.rvIdade.setAdapter(adapter);
        viewModel.getListaPessoas().observe(getViewLifecycleOwner(), new Observer<List<Pessoa>>() {
            @Override
            public void onChanged(List<Pessoa> pessoas) {
                if(pessoas != null){
                    adapter.atualizaAdapter(pessoas);
                }
            }
        });
        if(viewModel.getListaPessoas().getValue() == null){
            viewModel.carregarDados();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

