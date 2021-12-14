package co.gov.mintic.misiontic.ciclo4.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import co.gov.mintic.misiontic.ciclo4.modelo.crud.CrudUsuario;
import co.gov.mintic.misiontic.ciclo4.modelo.datos.Usuario;
import co.gov.mintic.misiontic.ciclo4.utilidades.Utilidad;
import co.gov.mintic.misiontic.ciclo4.vistas.databinding.LayoutCrudUsuarioBinding;

public class ActividadCrudUsuario extends AppCompatActivity {

    LayoutCrudUsuarioBinding binding;
    Usuario u;
    @Override
    protected void onCreate(@Nullable Bundle paquete) {
        super.onCreate(paquete);
        binding = LayoutCrudUsuarioBinding.inflate(getLayoutInflater());
        View raiz = binding.getRoot();
        setContentView(raiz);
        Bundle paquete2 = getIntent().getExtras();
        if(paquete2 != null){
             u = (Usuario) paquete2.getSerializable("user.login");
            if(u != null){
                Log.e("Nombre",u.getNombre());
                Log.e("EMAIL:",u.getEmail());
                cargarUsuario(u);
            }
        }

    }

    public void guardarUsuario(View view) {
        String cedula = binding.campoCedula.getText().toString();
        String clave = binding.campoClaveAc2.getText().toString();
        String nombre = binding.campoNombre.getText().toString();
        String email = binding.campoEmailAc2.getText().toString();
        Usuario u = new Usuario(cedula, clave, nombre, email);
        CrudUsuario crud = new CrudUsuario(this);
        try {
            crud.agregarUsuario(u);
            Utilidad.mostrarMensaje(view, "Usuario Agregado",
                    getResources().getColor(R.color.mensaje_ok_fond),
                    getResources().getColor(R.color.mensaje_ok_letra));
            limpiar(view);

        } catch (Exception error) {
            Snackbar.make(view,error.getMessage() , Snackbar.LENGTH_LONG)
                    .setBackgroundTint(getResources().getColor(R.color.mensaje_error_fond))
                    .setTextColor(getResources().getColor(R.color.mensaje_error_letra))
                    .show();
        }
    }


    public void cargarUsuario(Usuario u){
        this.u = u;
        binding.campoCedula.setText(u.getCedula());
        binding.campoClaveAc2.setText(u.getClave());
        binding.campoNombre.setText(u.getNombre());
        binding.campoEmailAc2.setText(u.getEmail());
    }


    public void limpiar(View v){
        binding.campoEmailAc2.setText("");
        binding.campoClaveAc2.setText("");
        binding.campoCedula.setText("");
        binding.campoNombre.setText("");
    }

    public void borrarUsuario(View view) {
        String cedula = binding.campoCedula.getText().toString();
        CrudUsuario crud = new CrudUsuario(this);
        try {
            crud.eliminarUsuario(cedula);
            Utilidad.mostrarMensaje(view, "Usuario ELiminado",
                    getResources().getColor(R.color.mensaje_ok_fond),
                    getResources().getColor(R.color.mensaje_ok_letra));
            limpiar(view);
        } catch (Exception e) {
            Utilidad.mostrarMensaje(view, e.getMessage(),
                    getResources().getColor(R.color.mensaje_error_fond),
                    getResources().getColor(R.color.mensaje_error_letra));
        }
    }

    public void editarUsuario(View view) {
       // String cedula = binding.campoCedula.getText().toString();
        String clave = binding.campoClaveAc2.getText().toString();
      //  String nombre = binding.campoNombre.getText().toString();
        String email = binding.campoEmailAc2.getText().toString();
        //Usuario u = new Usuario();
        u.setEmail(email);
        u.setClave(clave);
        CrudUsuario crud = new CrudUsuario(this);
        try {
            crud.actualizarUsuario(u);
            Utilidad.mostrarMensaje(view, "Usuario Modificado",
                    getResources().getColor(R.color.mensaje_ok_fond),
                    getResources().getColor(R.color.mensaje_ok_letra));
        } catch (Exception e) {
            Utilidad.mostrarMensaje(view, e.getMessage(),
                    getResources().getColor(R.color.mensaje_error_fond),
                    getResources().getColor(R.color.mensaje_error_letra));
        }

    }

    public void buscarUsuario(View view) {
        String cc = binding.campoCedula.getText().toString();
        CrudUsuario crud = new CrudUsuario(this);
        try {
            Usuario u = crud.buscarUsuario(cc);
            cargarUsuario(u);
            Utilidad.mostrarMensaje(view, "Usuario encontrado",
                    getResources().getColor(R.color.mensaje_ok_fond),
                    getResources().getColor(R.color.mensaje_ok_letra));
        } catch (Exception e) {
            Utilidad.mostrarMensaje(view, e.getMessage(),
                    getResources().getColor(R.color.mensaje_error_fond),
                    getResources().getColor(R.color.mensaje_error_letra));
        }
    }
}
