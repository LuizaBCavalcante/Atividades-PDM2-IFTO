package com.example.tarefa28_02;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.tarefa28_02.databinding.ActivityDetalheBinding;

public class DetalheActivity extends AppCompatActivity {
    private ActivityDetalheBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalhe);

        Intent intent = getIntent();
        String nome = intent.getStringExtra("NOME");
        String tel = intent.getStringExtra("TEL");
        String email = intent.getStringExtra("EMAIL");

        binding.textViewNomeDetalhe.setText(nome);
        binding.textViewTelefoneDetalhe.setText(tel);
        binding.textViewEmailDetalhe.setText(email);

    }
}
