package chandu.studentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Chandu on 1/27/2018.
 */

public class SQLClassDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="RIKTAM.DB";
    private static final int DATABASE_VERSION=1;
    private static final String CREATE_QUERY=
            "CREATE TABLE "+ SQLClassFields.NewClass.TABLE_NAME + " (" + SQLClassFields.NewClass.CLASS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    SQLClassFields.NewClass.CLASS_NAME + " TEXT);";

    private static final String CREATE_QUERY2 =
            "CREATE TABLE "+ SQLStudentFields.NewStudent.TABLE_NAME + " (" + SQLStudentFields.NewStudent.STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    SQLStudentFields.NewStudent.STUDENT_NAME + " TEXT," + SQLStudentFields.NewStudent.STUDENT_EMAIL + " TEXT," + SQLStudentFields.NewStudent.STUDENT_PID +
                    " INTEGER, CONSTRAINT foreign_key FOREIGN KEY (" + SQLStudentFields.NewStudent.STUDENT_PID +
            ") REFERENCES " + SQLClassFields.NewClass.TABLE_NAME + " (" + SQLClassFields.NewClass.CLASS_ID + ") ON DELETE CASCADE);";

    public SQLClassDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

        Log.e("DATABASE OPERATIONS:","Database created..");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON;");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        db.execSQL(CREATE_QUERY2);

        Log.e("DATABASE OPERATIONS:","Tables created..");

    }

    public void addInformation(String name, SQLiteDatabase db){

        ContentValues contentValues=new ContentValues();
        contentValues.put(SQLClassFields.NewClass.CLASS_NAME,name);
        db.insert(SQLClassFields.NewClass.TABLE_NAME,null,contentValues);
        Log.e("DATABASE OPERATIONS:","Inserted..");

    }
    public void addStudent(String name, String email,int pid, SQLiteDatabase db){

        ContentValues contentValues=new ContentValues();
        contentValues.put(SQLStudentFields.NewStudent.STUDENT_NAME,name);
        contentValues.put(SQLStudentFields.NewStudent.STUDENT_EMAIL,email);
        contentValues.put(SQLStudentFields.NewStudent.STUDENT_PID,pid);



        db.insert(SQLStudentFields.NewStudent.TABLE_NAME,null,contentValues);
        Log.e("DATABASE OPERATIONS:","Inserted..");

    }

    public Cursor getInformation(SQLiteDatabase db){
        Cursor cursor;
        String[] projections = {SQLClassFields.NewClass.CLASS_ID,SQLClassFields.NewClass.CLASS_NAME};
        cursor= db.query(SQLClassFields.NewClass.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }

    public Cursor getAllStudent(SQLiteDatabase db){
        Cursor cursor;
        String[] projections = {SQLStudentFields.NewStudent.STUDENT_ID,SQLStudentFields.NewStudent.STUDENT_NAME,
        SQLStudentFields.NewStudent.STUDENT_EMAIL,SQLStudentFields.NewStudent.STUDENT_PID};
        cursor= db.query(SQLStudentFields.NewStudent.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }

    public Cursor getStudentByPid(int id,SQLiteDatabase db){

        Cursor cursor;
        String[] projections = {SQLStudentFields.NewStudent.STUDENT_ID,SQLStudentFields.NewStudent.STUDENT_NAME,
                SQLStudentFields.NewStudent.STUDENT_EMAIL,SQLStudentFields.NewStudent.STUDENT_PID};
        String selection = SQLStudentFields.NewStudent.STUDENT_PID + " LIKE ?";
        String[] selection_args = {String.valueOf(id)};
        cursor = db.query(SQLStudentFields.NewStudent.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;

    }

    public Cursor getStudentById(int id,SQLiteDatabase db){

        Cursor cursor;
        String[] projections = {SQLStudentFields.NewStudent.STUDENT_ID,SQLStudentFields.NewStudent.STUDENT_NAME,
                SQLStudentFields.NewStudent.STUDENT_EMAIL,SQLStudentFields.NewStudent.STUDENT_PID};
        String selection = SQLStudentFields.NewStudent.STUDENT_ID + " LIKE ?";
        String[] selection_args = {String.valueOf(id)};
        cursor = db.query(SQLStudentFields.NewStudent.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;

    }
    public Cursor getClassById(int id,SQLiteDatabase db){
        Cursor cursor;
        String[] projections = {SQLClassFields.NewClass.CLASS_NAME};
        String selection = SQLClassFields.NewClass.CLASS_ID+" LIKE ?";
        String[] selection_args = {String.valueOf(id)};
        cursor = db.query(SQLClassFields.NewClass.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }

    public int updateClass(int id,String name, SQLiteDatabase db){
        ContentValues contentValues=new ContentValues();
        contentValues.put(SQLClassFields.NewClass.CLASS_NAME,name);
        String selection = SQLClassFields.NewClass.CLASS_ID+" LIKE ?";
        String[] selection_args = {String.valueOf(id)};
        int count = db.update(SQLClassFields.NewClass.TABLE_NAME,contentValues,selection,selection_args);
        return count;
    }

    public int updateStudent(int id,String name,String email, SQLiteDatabase db){
        ContentValues contentValues=new ContentValues();
        contentValues.put(SQLStudentFields.NewStudent.STUDENT_NAME,name);
        contentValues.put(SQLStudentFields.NewStudent.STUDENT_EMAIL,email);

        String selection = SQLStudentFields.NewStudent.STUDENT_ID+" LIKE ?";
        String[] selection_args = {String.valueOf(id)};
        int count = db.update(SQLStudentFields.NewStudent.TABLE_NAME,contentValues,selection,selection_args);
        return count;
    }

    public void deleteClass(int id,SQLiteDatabase db){
        String selection = SQLClassFields.NewClass.CLASS_ID + " LIKE ?";
        String[] selection_args = {String.valueOf(id)};
        db.delete(SQLClassFields.NewClass.TABLE_NAME,selection,selection_args);
    }
    public void deleteStudent(int id,SQLiteDatabase db){
        String selection = SQLStudentFields.NewStudent.STUDENT_ID + " LIKE ?";
        String[] selection_args = {String.valueOf(id)};
        db.delete(SQLStudentFields.NewStudent.TABLE_NAME,selection,selection_args);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
