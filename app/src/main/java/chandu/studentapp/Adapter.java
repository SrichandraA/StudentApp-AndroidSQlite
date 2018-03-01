package chandu.studentapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by Chandu on 1/17/2018.
 */

public class Adapter extends ArrayAdapter<Data> {
    Context ctx;
    PlaceHolder holder=null;
    SQLiteDatabase sqLiteDatabase;
    SQLClassDbHelper sqlClassDbHelper;
    public Adapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Data> objects) {
        super(context, resource, objects);
        this.ctx=context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row;
        row=convertView;
         holder=new PlaceHolder();
        if(row==null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.row, parent, false);

            holder.className = (TextView) row.findViewById(R.id.className);
            holder.save = (Button) row.findViewById(R.id.save);
            holder.delete = (Button) row.findViewById(R.id.delete);
            holder.show = (Button) row.findViewById(R.id.show);

            row.setTag(holder);
        }
        else {
            holder=(PlaceHolder)row.getTag();
        }
            final Data data = getItem(position);
            holder.className.setText(data.getName());

            holder.save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    sqlClassDbHelper=new SQLClassDbHelper(ctx);
//                    sqLiteDatabase=sqlClassDbHelper.getWritableDatabase();
//                    int count=sqlClassDbHelper.updateClass(data.getId(),holder.className.getText().toString(),sqLiteDatabase);
//                     Toast.makeText(ctx,"Edited Successfully !",Toast.LENGTH_SHORT).show();
//                            Intent i=new Intent(ctx,MainActivity.class);
//                            ctx.startActivity(i);
                    Intent i=new Intent(ctx,EditClass.class);
                    i.putExtra("id",data.getId());
                    ctx.startActivity(i);


                }
            });
            holder.show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(getContext(),Student.class);
                    i.putExtra("id",data.getId());
                    getContext().startActivity(i);

                }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
                    dialog.setTitle("Delete ?");
                    dialog.setMessage("This action will delete all your data. Are you sure you want to continue?");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {

                            sqlClassDbHelper=new SQLClassDbHelper(ctx);
                            sqLiteDatabase = sqlClassDbHelper.getReadableDatabase();
                            sqlClassDbHelper.deleteClass(data.getId(),sqLiteDatabase);
                            Intent i=new Intent(ctx,MainActivity.class);
                            i.setFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
                            ctx.startActivity(i);

                        }
                    });

                    dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dlg, int which)
                        {
                            dlg.cancel();
                        }
                    });

                    AlertDialog al = dialog.create();
                    al.show();


                }
            });

        return row;
    }
    public class PlaceHolder{
        TextView className;
        Button save;
        Button delete;
        Button show;
    }
}
