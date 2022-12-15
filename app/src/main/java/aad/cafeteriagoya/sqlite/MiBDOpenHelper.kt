package aad.cafeteriagoya.sqlite

import aad.cafeteriagoya.entidades.Producto
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MiBDOpenHelper(contex: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(contex, DATABASE_NAME, factory, DATABASE_VERSION) {

    val TAG = "SQLite"

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "productos.db"
        val T_PRODUCTOS = "productos"
        val PRODUCTO_INSERCION_ID = "id_insercion_producto"
        val PRODUCTO_ID = "id_producto"
        val PRODUCTO_NOMBRE = "nombre"
        val PRODUCTO_PRECIO = "precio"
        val PRODUCTO_CATEGORIA = "categoria"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var crearTablaPorductos = "CREATE TABLE $T_PRODUCTOS " +
                "($PRODUCTO_INSERCION_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$PRODUCTO_ID INTEGER, " +
                "$PRODUCTO_NOMBRE TEXT," +
                "$PRODUCTO_PRECIO DOUBLE," +
                "$PRODUCTO_CATEGORIA TEXT)"
        var insercion_producto_prueba =
            "INSERT INTO $T_PRODUCTOS ($PRODUCTO_ID,$PRODUCTO_NOMBRE,$PRODUCTO_PRECIO,$PRODUCTO_CATEGORIA) " +
                    "VALUES (0,'Prueba',0.5,'pincho');"
        db!!.execSQL(crearTablaPorductos)
        db!!.execSQL(insercion_producto_prueba)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.e("$TAG (onUpgrade)", "Pendiente de realizar")
    }


    fun anadirProducto(p: Producto) {
        val db = this.writableDatabase
            val data = ContentValues()
            data.put(PRODUCTO_ID, p.id)
            data.put(PRODUCTO_NOMBRE, p.nombre)
            data.put(PRODUCTO_PRECIO, p.precio)
            data.put(PRODUCTO_CATEGORIA, p.categoria)

            db.insert(T_PRODUCTOS, null, data)
            db.close()
    }

    fun listarProducto(): List<Producto> {
        val db = this.readableDatabase
        var cursor = db.rawQuery("SELECT * FROM $T_PRODUCTOS", null)
        var lista = mutableListOf<Producto>()
        try {
            if (cursor.moveToFirst()) {
                do {
                    var p = Producto()
                    p.id = cursor.getInt(1)
                    p.nombre = cursor.getString(2)
                    p.precio= cursor.getDouble(3)
                    p.categoria= cursor.getString(4)
                    lista.add(p)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.d(TAG, "Error obteneniendo las tematicas de la base de datos")
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }
        return lista
    }

}