package com.example.medell.databasecrudoperation;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class data_show extends AppCompatActivity {
    dbh database;
    ListView listView = null;
    /*
    * cursor is used when we have to access the data from the database or upddate the existing database
    */
    Cursor cursor;
    ArrayList<String> data;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_show);
        database =new dbh(this);
        listView=(ListView)findViewById(R.id.list);
        data=new ArrayList<>();
        viewData();
        /*
        * fn for single click task
        */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int index, long l)
            {
                 /*
                 *builder of alert dialog box basically the function of alert dialog box
                 */
                AlertDialog.Builder builder1=new AlertDialog.Builder(data_show.this);
                builder1.setTitle("Update Form");
                builder1.setMessage("Do You Want To Update ?");
                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Intent intent=new Intent(data_show.this,update_activity.class);
                        intent.putExtra("name",data.get(index).toString());
                        startActivity(intent);
                    }
                });
                builder1.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        /*
                        * command to dismiss the alert dialog box
                        */
                        dialogInterface.dismiss();
                    }
                });
                 /*
                 * creating of alert dialog box
                 */
                AlertDialog alertDialog2=builder1.create();
                 /*
                 *  command to show the alert dialog box
                 */
                alertDialog2.show();
            }
        });
        /*
        * fn for long click task
        */
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int index, long l)
            {
                AlertDialog.Builder builder=new AlertDialog.Builder(data_show.this);
                builder.setTitle("Delete Form");
                builder.setMessage("Do You Want To Delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        boolean res=database.deleteByName(data.get(index).toString());
                        if(res)
                        {
                            Toast.makeText(getApplicationContext(),"Deleted ",Toast.LENGTH_SHORT).show();
                           /*
                           * command to tell the system that the deletion task id completed
                           */
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Not deleted",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog1=builder.create();
                alertDialog1.show();
                return true;
            }
        });

    }

    void viewData()
    {
        /*
        * giving database the a type of pointer
        */
        cursor=database.getData();
        /*
        * moving the pointer to the first position
        */
        cursor.moveToFirst();
        /*
        * loop till the cursor move to the last position of the database
        */
        while (cursor.isAfterLast()==false)
        {
            /*
            * adding data to the arraylist
            */
            data.add(cursor.getString(cursor.getColumnIndex(dbh.ColName)));
            /*
            * moves the pointer to the next position
            */
            cursor.moveToNext();
        }
        /*
        * checking whether the cursor is closed  after the cursor points the last value
        */
        if(!cursor.isClosed())
        {
            cursor.isClosed();
        }
        /*
        * making an adapter for the list view as we have used array list so we make an array adapter
        * along with the customed layout listview
        * and passing data array adapter to it
        */
        arrayAdapter=new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,data);
        listView.setAdapter(arrayAdapter);
    }

}
