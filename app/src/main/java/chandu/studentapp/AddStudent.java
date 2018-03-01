package chandu.studentapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by Chandu on 1/19/2018.
 */

public class AddStudent extends AppCompatActivity {

    Button addStudentBtn;
    EditText studentNameEt,studentEmailEt;
    int pid;
    SQLClassDbHelper sqlClassDbHelper;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstudent);
        addStudentBtn=(Button)findViewById(R.id.addStudentBtn);
        studentNameEt=(EditText)findViewById(R.id.addStudentNameEt);
        studentEmailEt=(EditText)findViewById(R.id.addStudentEmailEt);
        pid=getIntent().getExtras().getInt("id");

        sqlClassDbHelper=new SQLClassDbHelper(AddStudent.this);
        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase=sqlClassDbHelper.getWritableDatabase();
                sqlClassDbHelper.addStudent(studentNameEt.getText().toString(),studentEmailEt.getText().toString(),pid,sqLiteDatabase);
                Intent i=new Intent(AddStudent.this,Student.class);
                i.putExtra("id",pid);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Student Added !",Toast.LENGTH_SHORT).show();

            }
        });





    }

}
