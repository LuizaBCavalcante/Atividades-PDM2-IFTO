package com.example.revisao;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.revisao.databinding.ActivityMediaImcBinding;

public class MediaImcActivity extends AppCompatActivity {
    private ActivityMediaImcBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMediaImcBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        double media = getIntent().getDoubleExtra("MEDIA_VALOR", 0);

        binding.textMediaImc.setText(String.format("A média do IMC é: %.2f", media));

    }
}