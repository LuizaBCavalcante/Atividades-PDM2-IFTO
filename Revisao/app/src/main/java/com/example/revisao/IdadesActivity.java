package com.example.revisao;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.revisao.databinding.ActivityIdadesBinding;

public class IdadesActivity extends AppCompatActivity {
    private ActivityIdadesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIdadesBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        int maior = getIntent().getIntExtra("MAIOR", 0);
        int menor = getIntent().getIntExtra("MENOR", 0);

        binding.textMaiorIdade.setText("Maior Idade encontrada: " + maior + " anos");
        binding.textMenorIdade.setText("Menor Idade encontrada: " + menor + " anos");
    }
}