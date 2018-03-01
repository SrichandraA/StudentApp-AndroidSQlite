package chandu.studentapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;


/**
 * Created by Chandu on 1/18/2018.
 */

public class Student extends AppCompatActivity{

    Button save;
    ListView listView;
    AdapterStudent adapterStudent;
    int id;
    Cursor cursor;
    SQLiteDatabase sqLiteDatabase;
    SQLClassDbHelper sqlClassDbHelper;
    ArrayList<StudentData> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);
        save=(Button)findViewById(R.id.addStudent);
        listView=(ListView)findViewById(R.id.listStudent);
        id=getIntent().getExtras().getInt("id");
        //Toast.makeText(getApplicationContext(),String.valueOf(id),Toast.LENGTH_SHORT).show();

            sqlClassDbHelper=new SQLClassDbHelper(Student.this);
        sqLiteDatabase= sqlClassDbHelper.getReadableDatabase();
        cursor=sqlClassDbHelper.getStudentByPid(id,sqLiteDatabase);
        if(cursor.moveToFirst()) {
            do {
                //Toast.makeText(getApplicationContext(),String.valueOf(cursor.getInt(0))+cursor.getString(1),Toast.LENGTH_SHORT).show();
                arrayList.add(new StudentData(cursor.getString(1),cursor.getString(2),cursor.getInt(0),cursor.getInt(3)));
            } while (cursor.moveToNext());
        }

                 adapterStudent=new AdapterStudent(Student.this,R.layout.rowstudent,arrayList);
                listView.setAdapter(adapterStudent);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Student.this,AddStudent.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(Student.this,MainActivity.class);
        startActivity(i);
    }
}
