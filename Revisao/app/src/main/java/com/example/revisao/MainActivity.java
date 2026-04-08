package com.example.revisao;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.revisao.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActivityMainBinding binding;
    private AppBarConfiguration mAppBarConfiguration;
    private SharedViewModel viewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        drawerLayout = binding.drawerLayout;
        navigationView = binding.navigationView;
        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        setSupportActionBar(binding.appBarMain.toolbar);


        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.item1, R.id.item2, R.id.item3)
                .setOpenableLayout(drawerLayout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_idades){
            abrirIdadesActivity();
            return true;
        }
        if(item.getItemId() == R.id.action_media_imc){
            abrirMediaActvity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    private void abrirMediaActvity() {
        double media = viewModel.getMediaIMC();

        Intent intent = new Intent(this, MediaImcActivity.class);
        intent.putExtra("MEDIA_VALOR", media);
        startActivity(intent);
    }

    private void  abrirIdadesActivity() {
        int[] extremos = viewModel.getMaiorMenorIdade();

        Intent intent = new Intent(this, IdadesActivity.class);
        intent.putExtra("MAIOR", extremos[0]);
        intent.putExtra("MENOR", extremos[1]);
        startActivity(intent);
    }
}