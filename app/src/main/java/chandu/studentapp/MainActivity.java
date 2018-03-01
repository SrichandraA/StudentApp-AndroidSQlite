package chandu.studentapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button addClass;
    ArrayList<Data> arrayList=new ArrayList<>();
    Adapter adapter;
    Cursor cursor;
    SQLiteDatabase sqLiteDatabase;
    SQLClassDbHelper sqlClassDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.list);
        addClass=(Button)findViewById(R.id.addClass);

        sqlClassDbHelper=new SQLClassDbHelper(MainActivity.this);
        sqLiteDatabase=sqlClassDbHelper.getReadableDatabase();
        cursor=sqlClassDbHelper.getInformation(sqLiteDatabase);
        if(cursor.moveToFirst()) {
            do {
                arrayList.add(new Data(cursor.getString(1),cursor.getInt(0)));
            } while (cursor.moveToNext());
        }
        sqlClassDbHelper.close();
        adapter=new Adapter(MainActivity.this,R.layout.row,arrayList);
        listView.setAdapter(adapter);


        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,AddClass.class);

                startActivity(i);
            }
        });






    }
}
