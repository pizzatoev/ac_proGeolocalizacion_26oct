package com.example.ap_progeolocalizacion_26oct;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap myMap;
    
    // Listas para almacenar marcadores por categoría
    private List<Marker> cafeMarkers = new ArrayList<>();
    private List<Marker> polloMarkers = new ArrayList<>();
    private List<Marker> gymMarkers = new ArrayList<>();
    private List<Marker> mercaMarkers = new ArrayList<>();
    private List<Marker> persoMarkers = new ArrayList<>();
    private List<Marker> cineMarkers = new ArrayList<>();
    
    // Botones de categorías
    private Button btnCafe, btnPollo, btnGym, btnMerca, btnPerso, btnCine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar botones
        btnCafe = findViewById(R.id.btnCafe);
        btnPollo = findViewById(R.id.btnPollo);
        btnGym = findViewById(R.id.btnGym);
        btnMerca = findViewById(R.id.btnMerca);
        btnPerso = findViewById(R.id.btnPerso);
        btnCine = findViewById(R.id.btnCine);

        // Configurar listeners
        btnCafe.setOnClickListener(v -> mostrarSoloCategoria("cafe"));
        btnPollo.setOnClickListener(v -> mostrarSoloCategoria("pollo"));
        btnGym.setOnClickListener(v -> mostrarSoloCategoria("gym"));
        btnMerca.setOnClickListener(v -> mostrarSoloCategoria("merca"));
        btnPerso.setOnClickListener(v -> mostrarSoloCategoria("perso"));
        btnCine.setOnClickListener(v -> mostrarSoloCategoria("cine"));

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;

        // Centrar cámara en Santa Cruz
        LatLng santaCruz = new LatLng(-17.75, -63.18);
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(santaCruz, 12));

        // ☕ CAFETERÍAS
        LatLng laCondesa = new LatLng(-17.727268079582213, -63.167935340590816);
        cafeMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(laCondesa)
                .title("La Condesa (Cafetería)")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.cafeic),
                        70, 70, false
                    )
                ))));

        LatLng panpino = new LatLng(-17.748196178360832, -63.17591759461908);
        cafeMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(panpino)
                .title("Panpino (Cafetería)")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.cafeic),
                        70, 70, false
                    )
                ))));

        LatLng larome = new LatLng(-17.751537809998183, -63.17084333030127);
        cafeMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(larome)
                .title("Larome (Cafetería)")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.cafeic),
                        70, 70, false
                    )
                ))));

        LatLng laTribu = new LatLng(-17.762020060485384, -63.19399360412474);
        cafeMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(laTribu)
                .title("La Tribu (Cafetería)")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.cafeic),
                        70, 70, false
                    )
                ))));

        LatLng cafePradera = new LatLng(-17.70074244524683, -63.17729155762567);
        cafeMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(cafePradera)
                .title("Cafe La Pradera (Cafetería)")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.cafeic),
                        70, 70, false
                    )
                ))));

        LatLng fabrica = new LatLng(-17.722186899574545, -63.16288618214799);
        cafeMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(fabrica)
                .title("Fábrica (Cafetería)")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.cafeic),
                        70, 70, false
                    )
                ))));

        // 🍗 POLLOS
        LatLng polloCampeon = new LatLng(-17.73991011056409, -63.16416883801959);
        polloMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(polloCampeon)
                .title("Pollo Campeón")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.polloic),
                        70, 70, false
                    )
                ))));

        LatLng polloSakura = new LatLng(-17.736507869410126, -63.16995804253194);
        polloMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(polloSakura)
                .title("Pollo Sakura")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.polloic),
                        70, 70, false
                    )
                ))));

        LatLng polloKiky = new LatLng(-17.720082348183233, -63.16318344150685);
        polloMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(polloKiky)
                .title("Pollo Kiky")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.polloic),
                        70, 70, false
                    )
                ))));

        LatLng pollo10 = new LatLng(-17.73228430773756, -63.16926005333541);
        polloMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(pollo10)
                .title("Pollo 10")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.polloic),
                        70, 70, false
                    )
                ))));

        LatLng polloSolar = new LatLng(-17.721099209868605, -63.16420989620764);
        polloMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(polloSolar)
                .title("Pollo Solar")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.polloic),
                        70, 70, false
                    )
                ))));

        // 🏋️ EJERCICIO
        LatLng gymOne = new LatLng(-17.71280269719577, -63.17168494965753);
        gymMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(gymOne)
                .title("Gimnasio OneFitness")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.gymic),
                        70, 70, false
                    )
                ))));

        LatLng gymBelen = new LatLng(-17.732342692295003, -63.1694962671014);
        gymMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(gymBelen)
                .title("Gimnasio Belén")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.gymic),
                        70, 70, false
                    )
                ))));

        // 🛒 MERCADOS
        LatLng hipermaxi = new LatLng(-17.727943539361235, -63.16551831090474);
        mercaMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(hipermaxi)
                .title("Hipermaxi")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.mercaic),
                        70, 70, false
                    )
                ))));

        LatLng freshShop = new LatLng(-17.714603162044934, -63.16929199365491);
        mercaMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(freshShop)
                .title("FreshShop")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.mercaic),
                        70, 70, false
                    )
                ))));

        LatLng amarket = new LatLng(-17.715238722009936, -63.16676539540731);
        mercaMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(amarket)
                .title("Amarket")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.mercaic),
                        70, 70, false
                    )
                ))));

        // 🏠 FAMILIARES
        LatLng casaKaren = new LatLng(-17.72397830809843, -63.161136154969704);
        persoMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(casaKaren)
                .title("Casa de Karen (Amiga de la U)")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.persoic),
                        70, 70, false
                    )
                ))));

        LatLng casaCamila = new LatLng(-17.728439800806242, -63.162783841231494);
        persoMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(casaCamila)
                .title("Casa de Camila")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.persoic),
                        70, 70, false
                    )
                ))));

        LatLng casaTiaSofia = new LatLng(-17.725250657778794, -63.18329557414842);
        persoMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(casaTiaSofia)
                .title("Casa de Tía Sofía")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.persoic),
                        70, 70, false
                    )
                ))));

        LatLng casaDaniela = new LatLng(-17.717532613976978, -63.17893829003355);
        persoMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(casaDaniela)
                .title("Casa de Prima Daniela")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.persoic),
                        70, 70, false
                    )
                ))));

        LatLng casaWaldir = new LatLng(-17.716208753556273, -63.16402765570867);
        persoMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(casaWaldir)
                .title("Casa de Waldir (Amigo de la U)")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.persoic),
                        70, 70, false
                    )
                ))));

        LatLng casaFernando = new LatLng(-17.72399470749222, -63.16480498735593);
        persoMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(casaFernando)
                .title("Casa de Fernando")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.persoic),
                        70, 70, false
                    )
                ))));

        LatLng casaAndres = new LatLng(-17.72752249419689, -63.17515310501948);
        persoMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(casaAndres)
                .title("Casa de Andrés")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.persoic),
                        70, 70, false
                    )
                ))));

        // 🎬 CINES
        LatLng multicine = new LatLng(-17.747604876167493, -63.17595064239762);
        cineMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(multicine)
                .title("Multicine")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.cineic),
                        70, 70, false
                    )
                ))));

        LatLng cinemark = new LatLng(-17.753377192962812, -63.19930710761017);
        cineMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(cinemark)
                .title("Cinemark")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.cineic),
                        70, 70, false
                    )
                ))));

        LatLng cineCenter = new LatLng(-17.78434741936059, -63.1550333017166);
        cineMarkers.add(myMap.addMarker(new MarkerOptions()
                .position(cineCenter)
                .title("Cine Center")
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), R.drawable.cineic),
                        70, 70, false
                    )
                ))));
    }

    private void mostrarSoloCategoria(String categoria) {
        // Ocultar todos los marcadores primero
        ocultarTodosLosMarcadores();
        
        // Mostrar solo los marcadores de la categoría seleccionada
        switch (categoria) {
            case "cafe":
                mostrarMarcadores(cafeMarkers);
                break;
            case "pollo":
                mostrarMarcadores(polloMarkers);
                break;
            case "gym":
                mostrarMarcadores(gymMarkers);
                break;
            case "merca":
                mostrarMarcadores(mercaMarkers);
                break;
            case "perso":
                mostrarMarcadores(persoMarkers);
                break;
            case "cine":
                mostrarMarcadores(cineMarkers);
                break;
        }
    }
    
    private void ocultarTodosLosMarcadores() {
        ocultarMarcadores(cafeMarkers);
        ocultarMarcadores(polloMarkers);
        ocultarMarcadores(gymMarkers);
        ocultarMarcadores(mercaMarkers);
        ocultarMarcadores(persoMarkers);
        ocultarMarcadores(cineMarkers);
    }
    
    private void ocultarMarcadores(List<Marker> marcadores) {
        for (Marker marker : marcadores) {
            marker.setVisible(false);
        }
    }
    
    private void mostrarMarcadores(List<Marker> marcadores) {
        for (Marker marker : marcadores) {
            marker.setVisible(true);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}