package com.example.ap_progeolocalizacion_26oct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class LugarAdapter extends BaseAdapter {
    private Context context;
    private List<Lugar> lugares;
    private OnBorrarClickListener onBorrarClickListener;

    public interface OnBorrarClickListener {
        void onBorrarClick(int position, Lugar lugar);
    }

    public LugarAdapter(Context context, List<Lugar> lugares) {
        this.context = context;
        this.lugares = lugares;
    }

    public void setOnBorrarClickListener(OnBorrarClickListener listener) {
        this.onBorrarClickListener = listener;
    }

    @Override
    public int getCount() {
        return lugares.size();
    }

    @Override
    public Object getItem(int position) {
        return lugares.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_lugar, parent, false);
        }

        Lugar lugar = lugares.get(position);

        TextView textNombre = convertView.findViewById(R.id.textNombre);
        TextView textCalificacion = convertView.findViewById(R.id.textCalificacion);
        TextView textComentario = convertView.findViewById(R.id.textComentario);
        Button btnBorrar = convertView.findViewById(R.id.btnBorrar);

        textNombre.setText(lugar.getNombre());
        textCalificacion.setText("⭐ " + lugar.getCalificacion() + "/10");
        textComentario.setText(lugar.getComentario());

        btnBorrar.setOnClickListener(v -> {
            if (onBorrarClickListener != null) {
                onBorrarClickListener.onBorrarClick(position, lugar);
            }
        });

        return convertView;
    }
}
