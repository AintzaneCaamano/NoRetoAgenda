package com.example.agenda.objetos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataManager extends SQLiteOpenHelper {

    private final Context context;

    // Nombre de la database
    private static  final String DB_NAME = "agenda.db";

    // Version de la database
    private static final int DB_VERSION = 1;

    // Constructor
    public DataManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    // Nombre de la tabla Usuario
    public static final String USER_TABLE_NAME = "Usuario";

    // Columnas de la tabla Usuario
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USER_NAME = "nombre_usuario";
    private static final String COLUMN_USER_PASSWORD = "contrasenia";
    private static final String COLUMN_USER_REMEMBER = "recuerdame";

    // Creamos la tabla Usuario
    private static final String SQL_CREATE_TABLE_USER = "CREATE TABLE " +  USER_TABLE_NAME + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USER_NAME + " TEXT NOT NULL, "
            + COLUMN_USER_PASSWORD + " TEXT NOT NULL, "
            + COLUMN_USER_REMEMBER + " TEXT NOT NULL " // ojo aqui, realmente es un boolean, tiene que ser un Integer con valor 0 o 1
            + ");";

    // Nombre de la tabla Tarea
    private static  final  String TASK_TABLE_NAME = "Tarea";

    // Columnas de la tabla tarea
    private static final String COLUMN_TASK_ID = "id";
    private static final String COLUMN_TASK_NAME = "nombre_tarea";
    private static final String COLUMN_TASK_DESCRIPTION = "descripcion";
    private static final String COLUMN_TASK_DATE = "fecha";
    private static final String COLUMN_TASK_COST = "coste";
    private static final String COLUMN_TASK_PRIORITY = "prioridad";
    private static final String COLUMN_TASK_STATUS = "estado";

    // Creamos la tabla Tarea
    private static final String SQL_CREATE_TABLE_TASK = "CREATE TABLE " + TASK_TABLE_NAME + "("
            + COLUMN_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TASK_NAME + " TEXT NOT NULL, "
            + COLUMN_TASK_DESCRIPTION + " TEXT NOT NULL, "
            + COLUMN_TASK_DATE + " TEXT NOT NULL, "
            + COLUMN_TASK_COST + " TEXT NOT NULL, "
            + COLUMN_TASK_PRIORITY + " TEXT NOT NULL, "
            + COLUMN_TASK_STATUS + " TEXT NOT NULL "
            + ");";


    // Metodos propios de SQLiteOpenHelper
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TASK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    // Otros métodos
    public boolean m_ExisteUsuario(String username) {

        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from USER_TABLE_NAME where COLUMN_USER_NAME = ?", new String[]{username});

        if(cursor.getCount()>0) {
            return true;
        } else {
            return false;
        }

    }

//    public boolean m_ContraseniaCorrecta(String username, String pass) {
//
//
//
//
////}

}
