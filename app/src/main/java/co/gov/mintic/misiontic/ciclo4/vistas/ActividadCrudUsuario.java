package co.gov.mintic.misiontic.ciclo4.vistas;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import co.gov.mintic.misiontic.ciclo4.vistas.databinding.LayoutCrudUsuarioBinding;

public class ActividadCrudUsuario extends AppCompatActivity {

    LayoutCrudUsuarioBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutCrudUsuarioBinding.inflate(getLayoutInflater());
        View raiz = binding.getRoot();
        setContentView(raiz);
    }

    public void guardarUsuario(View view) {
    }
}
