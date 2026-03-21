package com.example.tarefa3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_segunda);

        String json = getIntent().getStringExtra("lista_estudantes");
        List<Student> lista = converterParaLista(json);

        configurarRecyclerView(lista);

        exibirMediaGeral(lista);
    }

    private List<Student> converterParaLista(String json) {
        if (json != null && !json.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Student>>() {}.getType();
            return gson.fromJson(json, type);
        }
        return new ArrayList<>();
    }

    private void configurarRecyclerView(List<Student> lista) {
        adapter = new StudentAdapter();
        binding.rvStatus.setLayoutManager(new LinearLayoutManager(this));
        binding.rvStatus.setAdapter(adapter);
        adapter.atualizaAdapter(lista);
    }

    private void exibirMediaGeral(List<Student> lista) {
        if (lista == null || lista.isEmpty()) {
            binding.txtMediaGeral.setText("Média Geral: 0.0");
            return;
        }

        double somaMedias = 0;
        for (Student s : lista) {
            somaMedias += s.getMedia();
        }
        double mediaGeral = somaMedias / lista.size();

        binding.txtMediaGeral.setText(String.format("Média Geral da Turma: %.2f", mediaGeral));
    }
}