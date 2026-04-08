package com.example.revisao;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.revisao.databinding.FragmentItem1Binding;

import java.util.List;

public class FragmentItem1 extends Fragment {

    private SharedViewModel viewModel;
    private PessoaAdapter adapter;
    private FragmentItem1Binding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentItem1Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        NotificationHelper.criarCanal(getContext());
        adapter = new PessoaAdapter();
        binding.rvPessoasApi.setAdapter(adapter);
        viewModel.getListaPessoas().observe(getViewLifecycleOwner(), new Observer<List<Pessoa>>() {
            @Override
            public void onChanged(List<Pessoa> pessoas) {
                if(pessoas != null){
                    adapter.atualizaAdapter(pessoas);
                }
            }
        });
        viewModel.getMensagem().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String msg) {
                if(msg != null){
                    NotificationHelper.criarCanal(getContext());
                    NotificationHelper.gerarNotificacao(getContext(), "Aviso", msg);
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
