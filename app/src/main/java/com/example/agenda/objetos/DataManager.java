package com.example.agenda.objetos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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
            + COLUMN_USER_REMEMBER + " INTEGER NOT NULL " // ojo aqui, realmente es un boolean, tiene que ser un Integer con valor 0 o 1
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
    private static final String COLUMN_TASK_USERCODE = "user";

    // Creamos la tabla Tarea
    private static final String SQL_CREATE_TABLE_TASK = "CREATE TABLE " + TASK_TABLE_NAME + "("
            + COLUMN_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TASK_NAME + " TEXT NOT NULL, "
            + COLUMN_TASK_DESCRIPTION + " TEXT NOT NULL, "
            + COLUMN_TASK_DATE + " TEXT NOT NULL, "
            + COLUMN_TASK_COST + " TEXT NOT NULL, "
            + COLUMN_TASK_PRIORITY + " TEXT NOT NULL, "
            + COLUMN_TASK_STATUS + " INTEGER NOT NULL, "
            + COLUMN_TASK_USERCODE+" INTEGER NOT NULL "
            + ");";


    // Metodos propios de SQLiteOpenHelper
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TASK);
        fillDB(sqLiteDatabase);
    }
    private void fillDB(SQLiteDatabase sqLiteDatabase){
        User user = new User(1, "Ain", "123", true);
        User user2 = new User(2, "Alb", "123", false);

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_PASSWORD, user.getPass());
        // **
        int remem = 0;
        if (user.isRemember()){
            remem = 1;
        }
        values.put(COLUMN_USER_REMEMBER, remem );
        sqLiteDatabase.insert(USER_TABLE_NAME, null, values);
        // **
        values = new ContentValues();
        values.put(COLUMN_USER_NAME, user2.getName());
        values.put(COLUMN_USER_PASSWORD, user2.getPass());

        remem = 0;
        if (user2.isRemember()){
            remem = 1;
        }
        values.put(COLUMN_USER_REMEMBER, remem );
        sqLiteDatabase.insert(USER_TABLE_NAME, null, values);

        Task task = new Task(1,"Acabar el repaso", "el repaso esta en moodle", "13-11-2021", "2h", 1, false,1);
        Task task2 = new Task(2,"Ejercicio 03 PMP", "Poner comentarios en todos los métodos", "20-11-2021", "3h", 2, true,1);
        Task task3 = new Task(3,"Comprar bolis", "", "10-11-2021", "15min", 3, true,1);
        Task task4 = new Task(4,"Repasar examen PM", "", "23-11-2021", "3min", 1, false,2);
        Task task5 = new Task(5,"Comprar regalo ama", "antes de la 18.00", "13-11-2021", "2h", 1, false,2);
        Task task6 = new Task(6,"Ejercicio 03 PMP", "Poner comentarios en todos los métodos", "20-11-2021", "3h", 2, true,1);
        Task task7 = new Task(7,"Ejercicio de PSP", "Los hilos tienen que incluir un boolean", "10-11-2021", "15min", 3, true,2);

        // **
        values = new ContentValues();
        values.put(COLUMN_TASK_NAME, task.getName());
        values.put(COLUMN_TASK_DESCRIPTION, task.getDescription());
        values.put(COLUMN_TASK_DATE, task.getDate());
        values.put(COLUMN_TASK_COST, task.getCost());
        values.put(COLUMN_TASK_PRIORITY, task.getPriority());
        int done = 0;
        if (task.isDone()){
            done = 1;
        }
        values.put(COLUMN_TASK_STATUS, done);
        values.put(COLUMN_TASK_USERCODE,task.getUserCode());
        sqLiteDatabase.insert(TASK_TABLE_NAME, null, values);
        // **
        values = new ContentValues();
        values.put(COLUMN_TASK_NAME, task2.getName());
        values.put(COLUMN_TASK_DESCRIPTION, task2.getDescription());
        values.put(COLUMN_TASK_DATE, task2.getDate());
        values.put(COLUMN_TASK_COST, task2.getCost());
        values.put(COLUMN_TASK_PRIORITY, task2.getPriority());
         done = 0;
        if (task2.isDone()){
            done = 1;
        }
        values.put(COLUMN_TASK_STATUS, done);
        values.put(COLUMN_TASK_USERCODE,task2.getUserCode());
        sqLiteDatabase.insert(TASK_TABLE_NAME, null, values);
        // **
        values = new ContentValues();
        values.put(COLUMN_TASK_NAME, task3.getName());
        values.put(COLUMN_TASK_DESCRIPTION, task3.getDescription());
        values.put(COLUMN_TASK_DATE, task3.getDate());
        values.put(COLUMN_TASK_COST, task3.getCost());
        values.put(COLUMN_TASK_PRIORITY, task3.getPriority());
        done = 0;
        if (task3.isDone()){
            done = 1;
        }
        values.put(COLUMN_TASK_STATUS, done);
        values.put(COLUMN_TASK_USERCODE,task3.getUserCode());
        sqLiteDatabase.insert(TASK_TABLE_NAME, null, values);
        // **
        values = new ContentValues();
        values.put(COLUMN_TASK_NAME, task4.getName());
        values.put(COLUMN_TASK_DESCRIPTION, task4.getDescription());
        values.put(COLUMN_TASK_DATE, task4.getDate());
        values.put(COLUMN_TASK_COST, task4.getCost());
        values.put(COLUMN_TASK_PRIORITY, task4.getPriority());
        done = 0;
        if (task4.isDone()){
            done = 1;
        }
        values.put(COLUMN_TASK_STATUS, done);
        values.put(COLUMN_TASK_USERCODE,task4.getUserCode());
        sqLiteDatabase.insert(TASK_TABLE_NAME, null, values);
        // **
        values = new ContentValues();
        values.put(COLUMN_TASK_NAME, task5.getName());
        values.put(COLUMN_TASK_DESCRIPTION, task5.getDescription());
        values.put(COLUMN_TASK_DATE, task5.getDate());
        values.put(COLUMN_TASK_COST, task5.getCost());
        values.put(COLUMN_TASK_PRIORITY, task5.getPriority());
        done = 0;
        if (task5.isDone()){
            done = 1;
        }
        values.put(COLUMN_TASK_STATUS, done);
        values.put(COLUMN_TASK_USERCODE,task5.getUserCode());
        sqLiteDatabase.insert(TASK_TABLE_NAME, null, values);
// **
        values = new ContentValues();
        values.put(COLUMN_TASK_NAME, task6.getName());
        values.put(COLUMN_TASK_DESCRIPTION, task6.getDescription());
        values.put(COLUMN_TASK_DATE, task6.getDate());
        values.put(COLUMN_TASK_COST, task6.getCost());
        values.put(COLUMN_TASK_PRIORITY, task6.getPriority());
        done = 0;
        if (task6.isDone()){
            done = 1;
        }
        values.put(COLUMN_TASK_STATUS, done);
        values.put(COLUMN_TASK_USERCODE,task6.getUserCode());
        sqLiteDatabase.insert(TASK_TABLE_NAME, null, values);
        // **
        values = new ContentValues();
        values.put(COLUMN_TASK_NAME, task7.getName());
        values.put(COLUMN_TASK_DESCRIPTION, task7.getDescription());
        values.put(COLUMN_TASK_DATE, task7.getDate());
        values.put(COLUMN_TASK_COST, task7.getCost());
        values.put(COLUMN_TASK_PRIORITY, task7.getPriority());
        done = 0;
        if (task7.isDone()){
            done = 1;
        }
        values.put(COLUMN_TASK_STATUS, done);
        values.put(COLUMN_TASK_USERCODE,task7.getUserCode());
        //sQLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(TASK_TABLE_NAME, null, values);
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

    public boolean m_ContraseniaCorrecta(String username, String pass) {

        SQLiteDatabase myDB = this.getWritableDatabase();

        String s;
        Cursor cursor = myDB.rawQuery("select * from USER_TABLE NAME where " + username + " =? and" + pass + " =?", null);

        if(cursor.getCount()>0) {
            return true;
        } else {
            return false;
        }

    }

    //------------------------------ selectAll ------------------------------//
    public ArrayList<Task> selectAllTaskData(int user){
        ArrayList<Task> ret = new ArrayList<Task>();
        String query = "SELECT * FROM " + TASK_TABLE_NAME + " WHERE " + COLUMN_TASK_USERCODE + " = "+ user;
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);
        Task task;
        while(cursor.moveToNext()){
            task = new Task();
            int code = Integer.parseInt(cursor.getString(0));
            task.setCode(code);
            task.setName(cursor.getString(1));
            task.setDescription(cursor.getString(2));
            task.setDate(cursor.getString(3));
            task.setCost(cursor.getString(4));
            task.setPriority(cursor.getInt(5));
            int status = cursor.getInt(6);
            boolean done=false;
            if (status==1){
                done=true;
            }
            task.setDone(done);
            task.setUserCode(cursor.getInt(7));
            ret.add(task);
        }
        cursor.close();
        sQLiteDatabase.close();
        return ret;
    }

    public ArrayList<User> selectAllUserData(){
        ArrayList<User> ret = new ArrayList<User>();
        String query = "SELECT * FROM " + USER_TABLE_NAME;
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);
        User user;
        while(cursor.moveToNext()){
            user = new User();
            int code = Integer.parseInt(cursor.getString(0));
            user.setCode(code);
            user.setName(cursor.getString(1));
            user.setPass(cursor.getString(2));
            int remmeber = cursor.getInt(3);
            boolean done=false;
            if (remmeber==1){
                done=true;
            }
            user.setRemember(done);
            ret.add(user);
        }
        cursor.close();
        sQLiteDatabase.close();
        return ret;
    }

//------------------------------ select by ID ------------------------------//

    public Task selectTaskById(int id){
        Task ret = new Task();
        String query = "SELECT * FROM " + TASK_TABLE_NAME + " WHERE id = "+ id ;
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);

        while(cursor.moveToNext()){
            ret.setCode( cursor.getInt(0));
            ret.setName(cursor.getString(1));
            ret.setDescription(cursor.getString(2));
            ret.setDate(cursor.getString(3));
            ret.setCost(cursor.getString(4));
            ret.setPriority(cursor.getInt(5));
            int status = Integer.parseInt(cursor.getString(6));
            boolean done=false;
            if (status==1){
                done=true;
            }
            ret.setDone(done);
            ret.setUserCode(cursor.getInt(7));
        }
        cursor.close();
        sQLiteDatabase.close();
        return ret;
    }
    public User selectUserById(int id){
        User ret = new User();
        String query = "SELECT * FROM " + USER_TABLE_NAME + " WHERE id = "+ id ;
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);

        while(cursor.moveToNext()){
            ret.setCode( cursor.getInt(0));
            ret.setName(cursor.getString(1));
            ret.setPass(cursor.getString(2));
            int status = Integer.parseInt(cursor.getString(3));
            boolean done=false;
            if (status==1){
                done=true;
            }
            ret.setRemember(done);
        }
        cursor.close();
        sQLiteDatabase.close();
        return ret;
    }
//------------------------------ Insert ------------------------------//

    public void insertTask(Task task) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK_NAME, task.getName());
        values.put(COLUMN_TASK_DESCRIPTION, task.getDescription());
        values.put(COLUMN_TASK_DATE, task.getDate());
        values.put(COLUMN_TASK_COST, task.getCost());
        values.put(COLUMN_TASK_PRIORITY, task.getPriority());
        int done = 0;
        if (task.isDone()){
            done = 1;
        }
        values.put(COLUMN_TASK_STATUS, done);
        values.put(COLUMN_TASK_USERCODE, task.getUserCode());
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        sQLiteDatabase.insert(TASK_TABLE_NAME, null, values);
        sQLiteDatabase.close();
    }

    public void insertUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_PASSWORD, user.getPass());

        int remem = 0;
        if (user.isRemember()){
            remem = 1;
        }
        values.put(COLUMN_USER_REMEMBER, remem );
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        sQLiteDatabase.insert(USER_TABLE_NAME, null, values);
        sQLiteDatabase.close();
    }
//------------------------------ select task by status ------------------------------//
    public ArrayList<Task> selectTaskByStatus(boolean status, int user){
        String estatus="0";
        ArrayList<Task> ret = new ArrayList<Task>();
        if(status){
            estatus="1";
        }

        String query = "SELECT * FROM " + TASK_TABLE_NAME + " where " + COLUMN_TASK_STATUS + " = " +estatus + " AND " + COLUMN_TASK_USERCODE + " = " + user;
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);
        Task task;
        while(cursor.moveToNext()){
            task = new Task();
            int code = Integer.parseInt(cursor.getString(0));
            task.setCode(code);
            task.setName(cursor.getString(1));
            task.setDescription(cursor.getString(2));
            task.setDate(cursor.getString(3));
            task.setCost(cursor.getString(4));
            task.setPriority(cursor.getInt(5));
            int status2 = Integer.parseInt(cursor.getString(6));
            boolean done=false;
            if (status2==1){
                done=true;
            }
            task.setDone(done);
            task.setUserCode(cursor.getInt(7));
            ret.add(task);
        }
        cursor.close();
        sQLiteDatabase.close();
        return ret;
    }
    //------------------------------ Update ------------------------------//
    public boolean updateTask(Task task) {
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_TASK_NAME, task.getName());
        args.put(COLUMN_TASK_DESCRIPTION, task.getDescription());
        args.put(COLUMN_TASK_DATE, task.getDate());
        args.put(COLUMN_TASK_COST, task.getCost());
        args.put(COLUMN_TASK_PRIORITY,  task.getPriority());

        int done = 0;
        if (task.isDone()){
            done = 1;
        }
        args.put(COLUMN_TASK_STATUS, done);
        String whereClause = COLUMN_TASK_ID + "=" + task.getCode();
        return sQLiteDatabase.update(TASK_TABLE_NAME, args, whereClause, null) > 0;
    }

    public boolean updateUser(User user) {
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_USER_NAME, user.getName());
        args.put(COLUMN_USER_PASSWORD, user.getPass());

        int remem = 0;
        if (user.isRemember()){
            remem = 1;
        }
        args.put(COLUMN_USER_REMEMBER, remem);
        String whereClause = COLUMN_USER_ID + "=" + user.getCode();
        return sQLiteDatabase.update(USER_TABLE_NAME, args, whereClause, null) > 0;
    }
    //------------------------------ Delete ------------------------------//

    public void deleteTask (int id) {
       SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
       sQLiteDatabase.delete(TASK_TABLE_NAME, COLUMN_TASK_ID + "=?",
                new String[] {
                        String.valueOf(id)
                });
       sQLiteDatabase.close();
    }

}
