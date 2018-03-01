package chandu.studentapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Chandu on 1/29/2018.
 */

public class EditStudent extends AppCompatActivity {

    TextView heading;
    EditText name,email;
    Button editBtn;
    int id,pid;
    SQLClassDbHelper sqlClassDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstudent);
        heading=(TextView)findViewById(R.id.studentheading);
        name=(EditText)findViewById(R.id.addStudentNameEt);
        email=(EditText)findViewById(R.id.addStudentEmailEt);
        editBtn=(Button)findViewById(R.id.addStudentBtn);

        heading.setText("Edit Student");
        editBtn.setText("Edit");

        id=getIntent().getExtras().getInt("id");
        pid=getIntent().getExtras().getInt("pid");

        sqlClassDbHelper=new SQLClassDbHelper(EditStudent.this);
        sqLiteDatabase=sqlClassDbHelper.getReadableDatabase();
        cursor=sqlClassDbHelper.getStudentById(id,sqLiteDatabase);
        if(cursor.moveToFirst()){
            do{
                name.setText(cursor.getString(1));
                email.setText(cursor.getString(2));
            }while (cursor.moveToNext());
        }

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlClassDbHelper=new SQLClassDbHelper(EditStudent.this);
                sqLiteDatabase=sqlClassDbHelper.getWritableDatabase();
                int count=sqlClassDbHelper.updateStudent(id,name.getText().toString(),email.getText().toString(),sqLiteDatabase);
                Intent i=new Intent(EditStudent.this,Student.class);
                i.putExtra("id",pid);
                startActivity(i);
            }
        });



    }
}
