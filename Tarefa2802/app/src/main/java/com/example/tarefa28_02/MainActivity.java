package com.example.tarefa28_02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tarefa28_02.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private AgendaAdapter adapter;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        adapter = new AgendaAdapter(this, new ArrayList<Agenda>());
        binding.listViewDados.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getLista().observe(this, new Observer<List<Agenda>>() {
            @Override
            public void onChanged(List<Agenda> agendas) {
                if (agendas != null) {
                    adapter.clear();
                    adapter.addAll(agendas);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        viewModel.getMsg().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                }
            }
        });
        binding.listViewDados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Agenda selecionado = adapter.getItem(position);
                if(selecionado != null){
                    Intent intent = new Intent(MainActivity.this, DetalheActivity.class);
                    intent.putExtra("ID_CONTATO", selecionado.getId());
                    startActivity(intent);
                    }
                }
        });
        viewModel.carregarDados();
    }
}