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

import com.example.tarefa28_02.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private AgendaAdapter adapter;
    private Repositorio repositorio;
    private List<Adicional> listaAdicionais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        repositorio = new Repositorio();

        adapter = new AgendaAdapter(this, new ArrayList<Agenda>());
        binding.listViewDados.setAdapter(adapter);

        carregarDados();

        binding.listViewDados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Agenda selecionado = adapter.getItem(position);
                if (selecionado != null) {
                    abrirDetalhes(selecionado);
                }
            }
        });
    }

    private void carregarDados() {
        Toast.makeText(this, "Buscando dados...", Toast.LENGTH_SHORT).show();

        repositorio.obterDados(new Repositorio.Callback() {
            @Override
            public void sucesso(RespostaAPI resposta) {
                listaAdicionais = resposta.getAdicionais();

                adapter.clear();
                adapter.addAll(resposta.getAgenda());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void erro(String mensagem) {
                Toast.makeText(MainActivity.this, "Erro: " + mensagem, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void abrirDetalhes(Agenda agenda) {
        String emailEncontrado = "E-mail não disponível";

        if (listaAdicionais != null) {
            for (int i = 0; i < listaAdicionais.size(); i++) {
                Adicional a = listaAdicionais.get(i);
                if (a.getId().equals(agenda.getId())) {
                    emailEncontrado = a.getEmail();
                    break;
                }
            }
        }

        Intent intent = new Intent(this, DetalheActivity.class);
        intent.putExtra("NOME", agenda.getNome());
        intent.putExtra("TEL", agenda.getTelefone());
        intent.putExtra("EMAIL", emailEncontrado);
        startActivity(intent);
    }
}