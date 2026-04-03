package com.example.tarefa5;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MainActivity extends AppCompatActivity {
    private MapView mapView;
    private FrameLayout mapContainer;
    private TextView txtCoordenadas;
    private static final int REQUEST_CODE = 101;
    private LocationManager locationManager;
    private Marker markerFixo;
    private Marker markerAtualizavel;
    private Location ultimaLocalizacao;
    private String[] permissoes = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Configuration.getInstance().setUserAgentValue(getPackageName());
        mapContainer = findViewById(R.id.mapContainer);
        txtCoordenadas = findViewById(R.id.txtCoordenadas);
        mapView = findViewById(R.id.mapView);

        if(Permissao.validaPermissao(permissoes, this, REQUEST_CODE)){
            inicializarLocalizacaoTempoReal();
        }
    } //onCreate

    private void inicializarLocalizacaoTempoReal(){
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                ultimaLocalizacao = location;

                txtCoordenadas.setText("Lat: " + location.getLatitude() + " | Long: " + location.getLongitude());
                if(markerFixo == null){
                    inicializarMapa(location);
                }
            }
        });

    }//iltr

    private void inicializarMapa(Location location) {
        if (mapView != null) {
            mapView.setMultiTouchControls(true);
            mapView.getController().setZoom(16.0);
            GeoPoint localInicial = new GeoPoint(location.getLatitude(), location.getLongitude());
            mapView.getController().setCenter(localInicial);
            markerFixo = new Marker(mapView);
            markerFixo.setPosition(localInicial);
            markerFixo.setTitle("Local Inicial");
            mapView.getOverlays().add(markerFixo);

            markerAtualizavel = new Marker(mapView);
            markerAtualizavel.setPosition(localInicial);
            markerAtualizavel.setTitle("Clique para atualizar");
            markerAtualizavel.setIcon(ContextCompat.getDrawable(this, android.R.drawable.btn_star_big_on));

            markerAtualizavel.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker, MapView mapView) {
                    if(ultimaLocalizacao != null){
                        GeoPoint novaPos = new GeoPoint(ultimaLocalizacao.getLatitude(),
                                ultimaLocalizacao.getLongitude());
                        marker.setPosition(novaPos);
                        mapView.getController().animateTo(novaPos);
                        Toast.makeText(MainActivity.this, "Localização atualizada!", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
            mapView.getOverlays().add(markerAtualizavel);
            mapContainer.setVisibility(MapView.VISIBLE);
        }
    }//inicializarMapa

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE) {
            boolean algumaPermissaoNegada = false;
            for (int resultado : grantResults) {
                if (resultado != PackageManager.PERMISSION_GRANTED) {
                    algumaPermissaoNegada = true;
                    break;
                }
            }
            if (!algumaPermissaoNegada) {
                inicializarLocalizacaoTempoReal();
            } else {
                mostrarAlerta();
            }
        }
    } //orpr

    private void mostrarAlerta(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissão necessária");
        builder.setMessage("É necessário conceder essa permissão para o funcionamento do mapa");
        builder.setCancelable(false);
        builder.setPositiveButton("Tentar novamente", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Permissao.validaPermissao(permissoes, MainActivity.this, REQUEST_CODE);
            }
        });
        builder.setNegativeButton("Sair", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }
}//class