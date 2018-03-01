package chandu.studentapp;

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

import java.util.List;



/**
 * Created by Chandu on 1/18/2018.
 */

public class AdapterStudent extends ArrayAdapter<StudentData>{
    PlaceHolder holder;
    Context ctx;
//    TextView studentName;
//    TextView studentEmail;
//    Button save,delete;
    SQLiteDatabase sqLiteDatabase;
    SQLClassDbHelper sqlClassDbHelper;
    public AdapterStudent(@NonNull Context context, @LayoutRes int resource, @NonNull List<StudentData> objects) {
        super(context, resource, objects);
        this.ctx=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

//        View row=convertView;
        if(convertView==null){
//row=convertView;
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.rowstudent, parent, false);
            holder=new PlaceHolder();

        holder.studentName=(TextView)convertView.findViewById(R.id.studentName);
          holder. studentEmail=(TextView)convertView.findViewById(R.id.studentEmail);
       holder.save=(Button)convertView.findViewById(R.id.savestudent);
            holder.delete=(Button)convertView.findViewById(R.id.deletestudent);

            convertView.setTag(holder);

        }
        else {
            holder=new PlaceHolder();

            holder=(PlaceHolder)convertView.getTag();
        }

        final StudentData studentData=getItem(position);
      holder.studentName.setText(studentData.getName());
     holder.studentEmail.setText(studentData.getEmail());



        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(ctx,EditStudent.class);
                i.putExtra("id",studentData.getId());
                i.putExtra("pid",studentData.getPid());
                ctx.startActivity(i);



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
                        sqLiteDatabase= sqlClassDbHelper.getReadableDatabase();
                        sqlClassDbHelper.deleteStudent(studentData.getId(),sqLiteDatabase);
                        Intent i = new Intent(ctx, Student.class);
                        i.putExtra("id", studentData.getPid());
                        ctx.startActivity(i);
                        Toast.makeText(ctx, "Deleted Successfully !", Toast.LENGTH_SHORT).show();



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


        return convertView;
    }

    public class PlaceHolder{
        TextView studentName;
        TextView studentEmail;
        Button save,delete;
    }
}
