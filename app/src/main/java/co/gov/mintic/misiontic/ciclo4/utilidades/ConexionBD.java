package co.gov.mintic.misiontic.ciclo4.utilidades;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ConexionBD extends SQLiteOpenHelper {
    public static String nombreBD = "bd_ejemplo_g45.sqlite";
    public  static int version = 1;
    private Context app;
    private SQLiteDatabase bd;
    private String tablasUsuario = "CREATE TABLE Usuarios" +
            "(" +
            "cedula VARCHAR(15) PRIMARY KEY, " +
            "clave VARCHAR(30) DEFAULT 'Abc', " +
            "nombre VARCHAR(100) NOT NULL, " +
            "email VARCHAR(70) NOT NULL UNIQUE" +
            ")";


    public ConexionBD(@Nullable Context context, @Nullable String nameDB,
                      @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nameDB, factory, version);
        this.app = context;
        this.nombreBD = nameDB;
        this.version = version;
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(tablasUsuario);
        Log.e("NOMBRE BD", nombreBD);
        Log.e("SQL",tablasUsuario);
        this.bd = bd;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void modificarLaBD(String sql) throws Exception{
        Log.e("SQL",sql);
        try{
            bd.execSQL(sql);
        }
        catch (Exception error){
            Log.e("ERROR:", error.getMessage());
            throw new Exception("ERROR: "+error.getMessage());
        }
    }

    public Cursor consultarEnLaBD(String sql) throws Exception {
        Log.e("SQL",sql);
        try{
            return bd.rawQuery(sql, null);
        }
        catch (Exception error){
            Log.e("ERROR:", error.getMessage());
            throw new Exception("ERROR: "+error.getMessage());
        }
    }

    public void desconectar(){
        if(bd != null && bd.isOpen()){
            bd.close();
        }

    }

    public void setBd(SQLiteDatabase bd) {
        this.bd = bd;
    }
}
