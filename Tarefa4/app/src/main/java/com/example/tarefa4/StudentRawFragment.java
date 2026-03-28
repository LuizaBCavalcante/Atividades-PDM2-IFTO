package com.example.tarefa4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tarefa4.databinding.FragmentRawBinding;

import java.util.List;


public class StudentRawFragment extends Fragment {
    private SharedViewModel viewModel;
    private StudentRawAdapter adapter;
    private FragmentRawBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_raw, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        configurarRecyclerView();

        if (viewModel.getListaEstudantes().getValue() == null) {
            viewModel.carregarDados();
        }

        viewModel.getListaEstudantes().observe(getViewLifecycleOwner(), new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                if (students != null) {
                    adapter.atualizaAdapter(students);
                }
            }
        });
    }
    private void configurarRecyclerView() {
        adapter = new StudentRawAdapter();
        binding.rvApiAlunos.setAdapter(adapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
