package co.gov.mintic.misiontic.ciclo4.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.google.android.material.snackbar.Snackbar;

import co.gov.mintic.misiontic.ciclo4.modelo.crud.CrudUsuario;
import co.gov.mintic.misiontic.ciclo4.modelo.datos.Usuario;
import co.gov.mintic.misiontic.ciclo4.vistas.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
       // setContentView(R.layout.activity_main);
        binding.btnCancelarAct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiar();
            }
        });

    }

    private void limpiar(){
        binding.campoEmailAc1.setText("");
        binding.campoClaveAc1.setText("");
    }


    public void login(View view) {
        String email = binding.campoEmailAc1.getText().toString();
        String clave = binding.campoClaveAc1.getText().toString();
        CrudUsuario crud = new CrudUsuario(this);
        try {
            Usuario u = crud.iniciarSesion(email, clave);
        } catch (Exception e) {
            Snackbar.make(view, "Acceso Negado:", Snackbar.LENGTH_INDEFINITE)
                    .setBackgroundTint(getResources().getColor(R.color.mensaje_error_fond))
                    .setTextColor(getResources().getColor(R.color.mensaje_error_letra))
                    .setAction("Registrate...", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(MainActivity.this, ActividadCrudUsuario.class);
                            startActivity(i);
                        }
                    })
                    .show();
        }
    }
}