package co.gov.mintic.misiontic.ciclo4.utilidades;

import android.content.Context;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import co.gov.mintic.misiontic.ciclo4.vistas.R;

public class Utilidad {
    public static void mostrarMensaje(View v, String mensaje, int colorF, int colorL){
        Snackbar.make(v, mensaje, Snackbar.LENGTH_LONG)
                .setBackgroundTint(colorF)
                .setTextColor(colorL)
                .show();
    }
}
