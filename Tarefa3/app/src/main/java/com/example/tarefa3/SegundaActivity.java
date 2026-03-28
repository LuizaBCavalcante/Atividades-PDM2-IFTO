package com.example.tarefa3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tarefa3.databinding.ActivitySegundaBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SegundaActivity extends AppCompatActivity {

    private ActivitySegundaBinding binding;
    private StudentAdapter adapter;
    private SegundaViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_segunda);
        viewModel = new ViewModelProvider(this).get(SegundaViewModel.class);
        configurarRecyclerView();

        ArrayList<Student> listaEstudantes = getIntent().getParcelableArrayListExtra("alunos");

        if(listaEstudantes != null){
            viewModel.setListaEstudantes(listaEstudantes);
            adapter.atualizaAdapter((viewModel.getListaEstudantes().getValue()));
            binding.txtMediaGeral.setText(viewModel.obterMediaGeral());
        } else{
            binding.txtMediaGeral.setText("Sem dados");
        }
    }
    private void configurarRecyclerView() {
        adapter = new StudentAdapter();
        binding.rvStatus.setLayoutManager(new LinearLayoutManager(this));
        binding.rvStatus.setAdapter(adapter);
    }

}