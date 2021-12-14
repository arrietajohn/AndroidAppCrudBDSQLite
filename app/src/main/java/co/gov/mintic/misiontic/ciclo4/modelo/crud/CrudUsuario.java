package co.gov.mintic.misiontic.ciclo4.modelo.crud;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import co.gov.mintic.misiontic.ciclo4.modelo.datos.Usuario;
import co.gov.mintic.misiontic.ciclo4.utilidades.ConexionBD;

public class CrudUsuario {
    private ConexionBD conexion;

    public CrudUsuario(Context app){
        Log.e("BD", "Conectando con la BD...");
        conexion = new ConexionBD(app, ConexionBD.nombreBD, null, ConexionBD.version);
        SQLiteDatabase bd = conexion.getWritableDatabase();
        conexion.setBd(bd);
        Log.e("BD", "Conexion OK");
    }

    public void agregarUsuario(Usuario user) throws Exception {
        String sqlInser = "INSERT INTO Usuarios " +
                "VALUES('"+user.getCedula()+"','"+user.getClave()+"','"+user.getNombre()+"'" +
                ", '"+user.getEmail()+"')";
        Log.e("SQL",sqlInser);
        try {
            conexion.modificarLaBD(sqlInser);
        } catch (Exception error) {
            String mensaje ="El Usuario no fue agregado.\nMOTIVO: "+error.getMessage();
            Log.e("ERROR",mensaje);
           throw new Exception(mensaje);
        }
    }

    public Usuario buscarUsuario(String cedula) throws Exception {
        String sqlSelect = "SELECT * FROM Usuario WHERE cedula = '"+cedula+"'";
        Log.e("SQL",sqlSelect);
        try {
            Cursor resultado = conexion.consultarEnLaBD(sqlSelect);
            if(resultado != null && resultado.moveToFirst()){
                return crearUsuario(resultado);
            }
            else
                throw new Exception("Usuario No existe");
        } catch (Exception error) {
            String mensaje ="El Usuario no fue encontrado.\nMOTIVO: "+error.getMessage();
            Log.e("ERROR",mensaje);
            throw new Exception(mensaje);
        }
    }

    public Usuario iniciarSesion(String email, String clave) throws Exception {
        String sqlSelect = "SELECT * " +
                "FROM Usuarios " +
                "WHERE email = '"+email+"' AND clave = '"+clave+"'";
        Log.e("SQL", sqlSelect);
        try {
            Cursor resultado = conexion.consultarEnLaBD(sqlSelect);
            if(resultado != null && resultado.moveToFirst()){
                return crearUsuario(resultado);
            }
            else
                throw new Exception("Usuario No existe");
        } catch (Exception error) {
            String mensaje ="El Usuario no fue encontrado.\nMOTIVO: "+error.getMessage();
            Log.e("ERROR",mensaje);
            throw new Exception(mensaje);
        }
    }

    @SuppressLint("Range")
    public Usuario crearUsuario(Cursor resultado){
        Usuario user = new Usuario();
        user.setCedula(resultado.getString(resultado.getColumnIndex("cedula")));
        user.setClave(resultado.getString(resultado.getColumnIndex("clave")));
        user.setCedula(resultado.getString(resultado.getColumnIndex("nombre")));
        user.setCedula(resultado.getString(resultado.getColumnIndex("email")));
        return user;
    }
}
