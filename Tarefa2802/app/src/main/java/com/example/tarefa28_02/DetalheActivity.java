package com.example.tarefa28_02;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tarefa28_02.databinding.ActivityDetalheBinding;

public class DetalheActivity extends AppCompatActivity {
    private ActivityDetalheBinding binding;
    private DetalheViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalhe);

        viewModel = new ViewModelProvider(this).get(DetalheViewModel.class);
        int idContato = getIntent().getIntExtra("ID_CONTATO", -1);
        viewModel.getContato().observe(this, new Observer<Agenda>() {
            @Override
            public void onChanged(Agenda agenda) {
                if(agenda != null){
                    binding.textViewNomeDetalhe.setText(agenda.getNome());
                    binding.textViewTelefoneDetalhe.setText(agenda.getTelefone());
                }
            }
        });
        viewModel.getEmail().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String email) {
                if(email != null){
                    binding.textViewEmailDetalhe.setText(email);
                }
            }
        });
        viewModel.getMsg().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                if(error != null){
                    Toast.makeText(DetalheActivity.this,
                            error,
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        if (idContato != -1) {
            viewModel.carregarDados(idContato);
        }

    }
}
