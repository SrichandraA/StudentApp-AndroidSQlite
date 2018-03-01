package chandu.studentapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



/**
 * Created by Chandu on 1/18/2018.
 */

public class AddClass extends AppCompatActivity {
    EditText classNameEt;
    Button addClassBtn;

    SQLiteDatabase sqLiteDatabase;
    SQLClassDbHelper sqlClassDbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addclass);

        classNameEt=(EditText)findViewById(R.id.addClassNameEt);
        addClassBtn=(Button)findViewById(R.id.ClassBtn);
       sqlClassDbHelper=new SQLClassDbHelper(AddClass.this);

        addClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sqLiteDatabase=sqlClassDbHelper.getWritableDatabase();
                sqlClassDbHelper.addInformation(classNameEt.getText().toString(),sqLiteDatabase);
                Toast.makeText(getApplicationContext(),"inserted",Toast.LENGTH_SHORT).show();
                sqlClassDbHelper.close();
                Intent i=new Intent(AddClass.this, MainActivity.class);
                i.setFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);


            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(AddClass.this,MainActivity.class);
        startActivity(i);
    }
}
