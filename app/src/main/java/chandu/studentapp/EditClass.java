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

import org.w3c.dom.Text;

/**
 * Created by Chandu on 1/29/2018.
 */

public class EditClass extends AppCompatActivity {

    EditText editName;
    Button editBtn;
    TextView heading;
    int id;
    SQLClassDbHelper sqlClassDbHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addclass);
        heading=(TextView)findViewById(R.id.classheading);
        editBtn=(Button)findViewById(R.id.ClassBtn);
        editName=(EditText)findViewById(R.id.addClassNameEt);
        id=getIntent().getExtras().getInt("id");

        sqlClassDbHelper=new SQLClassDbHelper(EditClass.this);
        sqLiteDatabase=sqlClassDbHelper.getReadableDatabase();
        cursor=sqlClassDbHelper.getClassById(id,sqLiteDatabase);
        heading.setText("Edit Class");
        editBtn.setText("Edit");
        if(cursor.moveToFirst()){
            do{
                editName.setText(cursor.getString(0));
            }while (cursor.moveToNext());
        }

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sqlClassDbHelper=new SQLClassDbHelper(EditClass.this);
                sqLiteDatabase=sqlClassDbHelper.getWritableDatabase();
                int count=sqlClassDbHelper.updateClass(id,editName.getText().toString(),sqLiteDatabase);
                Intent i=new Intent(EditClass.this,MainActivity.class);
                i.setFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }
}
