package com.example.tarefa3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tarefa3.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private StudentRawAdapter adapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        configurarRecyclerView();

        viewModel.getListaEstudantes().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {

                adapter.atualizaAdapter(students);
            }
        });

        viewModel.getMensagem().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnIrPagina2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirSegundaTela();
            }
        });
        Toast.makeText(MainActivity.this, "Dados ainda não carregados!", Toast.LENGTH_SHORT).show();
        viewModel.carregarDados();
    }

    private void configurarRecyclerView(){
        adapter = new StudentRawAdapter();
        binding.rvEstudantes.setLayoutManager(new LinearLayoutManager(this));
        binding.rvEstudantes.setAdapter(adapter);
    }

private void abrirSegundaTela() {
    List<Student> listaAtual = viewModel.getListaEstudantes().getValue();

    if (listaAtual != null && !listaAtual.isEmpty()) {
        Intent intent = new Intent(this, SegundaActivity.class);
        intent.putParcelableArrayListExtra("alunos", new ArrayList<>(listaAtual));
        startActivity(intent);
    }
}

}
