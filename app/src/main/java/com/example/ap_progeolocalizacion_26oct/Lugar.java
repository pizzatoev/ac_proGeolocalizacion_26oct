package com.example.ap_progeolocalizacion_26oct;

public class Lugar {
    private String nombre;
    private String tipo;
    private double latitud;
    private double longitud;
    private int calificacion;
    private String comentario;

    public Lugar(String nombre, String tipo, double latitud, double longitud, int calificacion, String comentario) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public double getLatitud() { return latitud; }
    public double getLongitud() { return longitud; }
    public int getCalificacion() { return calificacion; }
    public String getComentario() { return comentario; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setLatitud(double latitud) { this.latitud = latitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }
    public void setCalificacion(int calificacion) { this.calificacion = calificacion; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    // Método para obtener el snippet con calificación
    public String getSnippetConCalificacion() {
        return "⭐ " + calificacion + "/10 - " + comentario;
    }
}
