package com.example.ap_progeolocalizacion_26oct;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.AdapterView;

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
    
    // Listas para almacenar lugares por categoría
    private List<Lugar> cafeLugares = new ArrayList<>();
    private List<Lugar> polloLugares = new ArrayList<>();
    private List<Lugar> gymLugares = new ArrayList<>();
    private List<Lugar> mercaLugares = new ArrayList<>();
    private List<Lugar> persoLugares = new ArrayList<>();
    private List<Lugar> cineLugares = new ArrayList<>();
    
   
    private Button btnCafe, btnPollo, btnGym, btnMerca, btnPerso, btnCine;
    
    // almacena la posición del clic
    private LatLng posicionClic;
    
    //  desplegable
    private View layoutListaLugares;
    private ListView listViewLugares;
    private TextView textTituloCategoria;
    private LugarAdapter adapter;
    private String categoriaActual = "";
    
    // Variables para expansión/colapso
    private View headerExpandible;
    private View contenidoExpandible;
    private TextView textExpandirColapsar;
    private boolean estaExpandido = true;

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
        
        // Inicializar componentes del ListView
        layoutListaLugares = findViewById(R.id.layoutListaLugares);
        listViewLugares = layoutListaLugares.findViewById(R.id.listViewLugares);
        textTituloCategoria = layoutListaLugares.findViewById(R.id.textTituloCategoria);
        
        // Inicializar componentes de expansión/colapso
        headerExpandible = layoutListaLugares.findViewById(R.id.headerExpandible);
        contenidoExpandible = layoutListaLugares.findViewById(R.id.contenidoExpandible);
        textExpandirColapsar = layoutListaLugares.findViewById(R.id.textExpandirColapsar);
        
        // Configurar click listener para expandir/colapsar
        headerExpandible.setOnClickListener(v -> toggleExpansion());

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
        
        // Inicializar lugares con calificaciones
        inicializarLugares();
    }
    
    private void inicializarLugares() {

        cafeLugares.add(new Lugar("La Condesa", "Cafetería", -17.727268079582213, -63.167935340590816, 9, "Excelente café y ambiente acogedor"));
        cafeLugares.add(new Lugar("Panpino", "Cafetería", -17.748196178360832, -63.17591759461908, 8, "Buenos pasteles y café"));
        cafeLugares.add(new Lugar("Larome", "Cafetería", -17.751537809998183, -63.17084333030127, 7, "Café artesanal de calidad"));
        cafeLugares.add(new Lugar("La Tribu", "Cafetería", -17.762020060485384, -63.19399360412474, 6, "Ambiente bohemio, café regular"));
        cafeLugares.add(new Lugar("Cafe La Pradera", "Cafetería", -17.70074244524683, -63.17729155762567, 8, "Ubicación perfecta, buen servicio"));
        cafeLugares.add(new Lugar("Fábrica", "Cafetería", -17.722186899574545, -63.16288618214799, 9, "Café premium, ambiente moderno"));
        

        polloLugares.add(new Lugar("Pollo Campeón", "Restaurante", -17.73991011056409, -63.16416883801959, 8, "Excelente pollo a la brasa"));
        polloLugares.add(new Lugar("Pollo Sakura", "Restaurante", -17.736507869410126, -63.16995804253194, 7, "Buen pollo, servicio rápido"));
        polloLugares.add(new Lugar("Pollo Kiky", "Restaurante", -17.720082348183233, -63.16318344150685, 6, "Pollo decente, precios accesibles"));
        polloLugares.add(new Lugar("Pollo 10", "Restaurante", -17.73228430773756, -63.16926005333541, 8, "Muy buen pollo y papas"));
        polloLugares.add(new Lugar("Pollo Solar", "Restaurante", -17.721099209868605, -63.16420989620764, 7, "Pollo sabroso, ambiente familiar"));
        
        // ejercicio
        gymLugares.add(new Lugar("Gimnasio OneFitness", "Gimnasio", -17.71280269719577, -63.17168494965753, 9, "Excelente equipamiento y entrenadores"));
        gymLugares.add(new Lugar("Gimnasio Belén", "Gimnasio", -17.732342692295003, -63.1694962671014, 7, "Buen gimnasio, precios razonables"));
        
        // mercado
        mercaLugares.add(new Lugar("Hipermaxi", "Supermercado", -17.727943539361235, -63.16551831090474, 8, "Variedad de productos, buen servicio"));
        mercaLugares.add(new Lugar("FreshShop", "Supermercado", -17.714603162044934, -63.16929199365491, 7, "Productos frescos, precios competitivos"));
        mercaLugares.add(new Lugar("Amarket", "Supermercado", -17.715238722009936, -63.16676539540731, 6, "Supermercado básico, precios económicos"));
        
        // famialia amigos
        persoLugares.add(new Lugar("Casa de Karen", "Casa", -17.72397830809843, -63.161136154969704, 10, "Amiga de la universidad, muy acogedora"));
        persoLugares.add(new Lugar("Casa de Camila", "Casa", -17.728439800806242, -63.162783841231494, 9, "Prima, ambiente familiar"));
        persoLugares.add(new Lugar("Casa de Tía Sofía", "Casa", -17.725250657778794, -63.18329557414842, 8, "Tía querida, siempre bienvenido"));
        persoLugares.add(new Lugar("Casa de Prima Daniela", "Casa", -17.717532613976978, -63.17893829003355, 7, "Prima, buenos momentos"));
        persoLugares.add(new Lugar("Casa de Waldir", "Casa", -17.716208753556273, -63.16402765570867, 8, "Amigo de la universidad, buena compañía"));
        persoLugares.add(new Lugar("Casa de Fernando", "Casa", -17.72399470749222, -63.16480498735593, 6, "Amigo, ambiente casual"));
        persoLugares.add(new Lugar("Casa de Andrés", "Casa", -17.72752249419689, -63.17515310501948, 7, "Amigo, buenas conversaciones"));
        
        // cine
        cineLugares.add(new Lugar("Multicine", "Cine", -17.747604876167493, -63.17595064239762, 8, "Buenas películas, cómodas butacas"));
        cineLugares.add(new Lugar("Cinemark", "Cine", -17.753377192962812, -63.19930710761017, 9, "Excelente calidad de imagen y sonido"));
        cineLugares.add(new Lugar("Cine Center", "Cine", -17.78434741936059, -63.1550333017166, 7, "Cine decente, precios accesibles"));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;

        // centrar camara en Santa Cruz
        LatLng santaCruz = new LatLng(-17.75, -63.18);
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(santaCruz, 12));

        //  marcadores con calificaciones
        crearMarcadoresConCalificaciones();

        myMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                posicionClic = latLng;
                mostrarDialogoAgregarLugar();
            }
        });
    }
    
    private void crearMarcadoresConCalificaciones() {
        // cafes
        for (Lugar lugar : cafeLugares) {
            LatLng posicion = new LatLng(lugar.getLatitud(), lugar.getLongitud());
            cafeMarkers.add(myMap.addMarker(new MarkerOptions()
                    .position(posicion)
                    .title(lugar.getNombre())
                    .snippet(lugar.getSnippetConCalificacion())
                    .icon(BitmapDescriptorFactory.fromBitmap(
                        Bitmap.createScaledBitmap(
                            BitmapFactory.decodeResource(getResources(), R.drawable.cafeic),
                            100, 100, false
                        )
                    ))));
        }

        // restaurantess
        for (Lugar lugar : polloLugares) {
            LatLng posicion = new LatLng(lugar.getLatitud(), lugar.getLongitud());
            polloMarkers.add(myMap.addMarker(new MarkerOptions()
                    .position(posicion)
                    .title(lugar.getNombre())
                    .snippet(lugar.getSnippetConCalificacion())
                    .icon(BitmapDescriptorFactory.fromBitmap(
                        Bitmap.createScaledBitmap(
                            BitmapFactory.decodeResource(getResources(), R.drawable.polloic),
                            100, 100, false
                        )
                    ))));
        }

        // 🏋️ EJERCICIO
        for (Lugar lugar : gymLugares) {
            LatLng posicion = new LatLng(lugar.getLatitud(), lugar.getLongitud());
            gymMarkers.add(myMap.addMarker(new MarkerOptions()
                    .position(posicion)
                    .title(lugar.getNombre())
                    .snippet(lugar.getSnippetConCalificacion())
                    .icon(BitmapDescriptorFactory.fromBitmap(
                        Bitmap.createScaledBitmap(
                            BitmapFactory.decodeResource(getResources(), R.drawable.gymic),
                            100, 100, false
                        )
                    ))));
        }

        // mercados
        for (Lugar lugar : mercaLugares) {
            LatLng posicion = new LatLng(lugar.getLatitud(), lugar.getLongitud());
            mercaMarkers.add(myMap.addMarker(new MarkerOptions()
                    .position(posicion)
                    .title(lugar.getNombre())
                    .snippet(lugar.getSnippetConCalificacion())
                    .icon(BitmapDescriptorFactory.fromBitmap(
                        Bitmap.createScaledBitmap(
                            BitmapFactory.decodeResource(getResources(), R.drawable.mercaic),
                            100, 100, false
                        )
                    ))));
        }

        // familiares
        for (Lugar lugar : persoLugares) {
            LatLng posicion = new LatLng(lugar.getLatitud(), lugar.getLongitud());
            persoMarkers.add(myMap.addMarker(new MarkerOptions()
                    .position(posicion)
                    .title(lugar.getNombre())
                    .snippet(lugar.getSnippetConCalificacion())
                    .icon(BitmapDescriptorFactory.fromBitmap(
                        Bitmap.createScaledBitmap(
                            BitmapFactory.decodeResource(getResources(), R.drawable.persoic),
                            100, 100, false
                        )
                    ))));
        }

        // cines
        for (Lugar lugar : cineLugares) {
            LatLng posicion = new LatLng(lugar.getLatitud(), lugar.getLongitud());
            cineMarkers.add(myMap.addMarker(new MarkerOptions()
                    .position(posicion)
                    .title(lugar.getNombre())
                    .snippet(lugar.getSnippetConCalificacion())
                    .icon(BitmapDescriptorFactory.fromBitmap(
                        Bitmap.createScaledBitmap(
                            BitmapFactory.decodeResource(getResources(), R.drawable.cineic),
                            100, 100, false
                        )
                    ))));
        }
    }
    
    private void mostrarDialogoAgregarLugar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_agregar_lugar, null);
        
        // campos pa llenar
        EditText editNombre = dialogView.findViewById(R.id.editNombre);
        Spinner spinnerTipo = dialogView.findViewById(R.id.spinnerTipo);
        SeekBar seekBarCalificacion = dialogView.findViewById(R.id.seekBarCalificacion);
        TextView textCalificacion = dialogView.findViewById(R.id.textCalificacion);
        EditText editComentario = dialogView.findViewById(R.id.editComentario);
        Button btnCancelar = dialogView.findViewById(R.id.btnCancelar);
        Button btnGuardar = dialogView.findViewById(R.id.btnGuardar);
        
        //spinner tipos
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipos_lugares, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapter);
        
        //  SeekBar de calificación
        seekBarCalificacion.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int calificacion = progress + 1; // 1-10
                textCalificacion.setText("⭐ " + calificacion + "/10");
            }
            
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        btnCancelar.setOnClickListener(v -> dialog.dismiss());
        
        btnGuardar.setOnClickListener(v -> {
            String nombre = editNombre.getText().toString().trim();
            String tipo = spinnerTipo.getSelectedItem().toString();
            int calificacion = seekBarCalificacion.getProgress() + 1;
            String comentario = editComentario.getText().toString().trim();
            
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa el nombre del lugar", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if (comentario.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa un comentario", Toast.LENGTH_SHORT).show();
                return;
            }
            

            Lugar nuevoLugar = new Lugar(nombre, tipo, posicionClic.latitude, posicionClic.longitude, calificacion, comentario);
            
            // agrega a la lista correspondiente y crea marcador
            agregarNuevoLugar(nuevoLugar);
            
            Toast.makeText(this, "Lugar agregado exitosamente", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
        
        dialog.show();
    }
    
    private void agregarNuevoLugar(Lugar lugar) {
        // Determinar a qué lista agregar según el tipo
        String tipo = lugar.getTipo().toLowerCase();
        
        if (tipo.contains("cafetería") || tipo.contains("cafe")) {
            cafeLugares.add(lugar);
            crearMarcadorParaLugar(lugar, cafeMarkers, R.drawable.cafeic);
        } else if (tipo.contains("restaurante") || tipo.contains("pollo")) {
            polloLugares.add(lugar);
            crearMarcadorParaLugar(lugar, polloMarkers, R.drawable.polloic);
        } else if (tipo.contains("gimnasio") || tipo.contains("gym")) {
            gymLugares.add(lugar);
            crearMarcadorParaLugar(lugar, gymMarkers, R.drawable.gymic);
        } else if (tipo.contains("supermercado") || tipo.contains("mercado")) {
            mercaLugares.add(lugar);
            crearMarcadorParaLugar(lugar, mercaMarkers, R.drawable.mercaic);
        } else if (tipo.contains("casa")) {
            persoLugares.add(lugar);
            crearMarcadorParaLugar(lugar, persoMarkers, R.drawable.persoic);
        } else if (tipo.contains("cine")) {
            cineLugares.add(lugar);
            crearMarcadorParaLugar(lugar, cineMarkers, R.drawable.cineic);
        } else {
            // Por defecto agregar a restaurantes
            polloLugares.add(lugar);
            crearMarcadorParaLugar(lugar, polloMarkers, R.drawable.polloic);
        }
    }
    
    private void crearMarcadorParaLugar(Lugar lugar, List<Marker> listaMarcadores, int iconResource) {
        LatLng posicion = new LatLng(lugar.getLatitud(), lugar.getLongitud());
        Marker nuevoMarcador = myMap.addMarker(new MarkerOptions()
                .position(posicion)
                .title(lugar.getNombre())
                .snippet(lugar.getSnippetConCalificacion())
                .icon(BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(getResources(), iconResource),
                        70, 70, false
                    )
                )));
        listaMarcadores.add(nuevoMarcador);
    }

    private void mostrarSoloCategoria(String categoria) {
        // Ocultar todos los marcadores primero
        ocultarTodosLosMarcadores();
        
        // Mostrar solo los marcadores de la categoría seleccionada
        switch (categoria) {
            case "cafe":
                mostrarMarcadores(cafeMarkers);
                mostrarListaLugares(cafeLugares, "Cafeterías");
                break;
            case "pollo":
                mostrarMarcadores(polloMarkers);
                mostrarListaLugares(polloLugares, "Restaurantes");
                break;
            case "gym":
                mostrarMarcadores(gymMarkers);
                mostrarListaLugares(gymLugares, "Gimnasios");
                break;
            case "merca":
                mostrarMarcadores(mercaMarkers);
                mostrarListaLugares(mercaLugares, "Supermercados");
                break;
            case "perso":
                mostrarMarcadores(persoMarkers);
                mostrarListaLugares(persoLugares, "Casas Familiares");
                break;
            case "cine":
                mostrarMarcadores(cineMarkers);
                mostrarListaLugares(cineLugares, "Cines");
                break;
        }
    }
    
    private void mostrarListaLugares(List<Lugar> lugares, String titulo) {
        categoriaActual = titulo;
        textTituloCategoria.setText(titulo);
        
        adapter = new LugarAdapter(this, lugares);
        listViewLugares.setAdapter(adapter);
        
        // Configurar listener para botón de borrar
        adapter.setOnBorrarClickListener(new LugarAdapter.OnBorrarClickListener() {
            @Override
            public void onBorrarClick(int position, Lugar lugar) {
                mostrarDialogoConfirmarBorrado(lugar, position);
            }
        });
        
        // Mostrar el ListView
        layoutListaLugares.setVisibility(View.VISIBLE);
        
        // Asegurar que esté expandido cuando se muestra
        contenidoExpandible.setVisibility(View.VISIBLE);
        textExpandirColapsar.setText("▼");
        estaExpandido = true;
        
        // Configurar click listener para los items del ListView
        listViewLugares.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lugar lugarSeleccionado = lugares.get(position);
                LatLng posicion = new LatLng(lugarSeleccionado.getLatitud(), lugarSeleccionado.getLongitud());
                myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(posicion, 15));
            }
        });
    }
    
    private void toggleExpansion() {
        if (estaExpandido) {
            // Colapsar
            contenidoExpandible.setVisibility(View.GONE);
            textExpandirColapsar.setText("▶");
            estaExpandido = false;
        } else {
            // Expandir
            contenidoExpandible.setVisibility(View.VISIBLE);
            textExpandirColapsar.setText("▼");
            estaExpandido = true;
        }
    }
    
    private void mostrarDialogoConfirmarBorrado(Lugar lugar, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar Borrado")
                .setMessage("¿Estás seguro de que quieres borrar '" + lugar.getNombre() + "'?")
                .setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        borrarLugar(lugar, position);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
    
    private void borrarLugar(Lugar lugar, int position) {
        // Determinar de qué lista borrar según el tipo
        String tipo = lugar.getTipo().toLowerCase();
        
        if (tipo.contains("cafetería") || tipo.contains("cafe")) {
            cafeLugares.remove(lugar);
            cafeMarkers.remove(position).remove();
        } else if (tipo.contains("restaurante") || tipo.contains("pollo")) {
            polloLugares.remove(lugar);
            polloMarkers.remove(position).remove();
        } else if (tipo.contains("gimnasio") || tipo.contains("gym")) {
            gymLugares.remove(lugar);
            gymMarkers.remove(position).remove();
        } else if (tipo.contains("supermercado") || tipo.contains("mercado")) {
            mercaLugares.remove(lugar);
            mercaMarkers.remove(position).remove();
        } else if (tipo.contains("casa")) {
            persoLugares.remove(lugar);
            persoMarkers.remove(position).remove();
        } else if (tipo.contains("cine")) {
            cineLugares.remove(lugar);
            cineMarkers.remove(position).remove();
        }
        
        // Actualizar el ListView
        adapter.notifyDataSetChanged();
        
        Toast.makeText(this, "Lugar borrado exitosamente", Toast.LENGTH_SHORT).show();
    }
    
    private void ocultarListaLugares() {
        layoutListaLugares.setVisibility(View.GONE);
    }
    
    private void ocultarTodosLosMarcadores() {
        ocultarMarcadores(cafeMarkers);
        ocultarMarcadores(polloMarkers);
        ocultarMarcadores(gymMarkers);
        ocultarMarcadores(mercaMarkers);
        ocultarMarcadores(persoMarkers);
        ocultarMarcadores(cineMarkers);
        ocultarListaLugares();
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