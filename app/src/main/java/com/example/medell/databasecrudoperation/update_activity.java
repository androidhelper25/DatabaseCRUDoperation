package com.example.medell.databasecrudoperation;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update_activity extends AppCompatActivity {

    dbh database=null;
    EditText n,num,pwd;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_activity);
        n=(EditText)findViewById(R.id.name);
        num=(EditText)findViewById(R.id.ph);
        pwd=(EditText)findViewById(R.id.pwd);
        btn=(Button)findViewById(R.id.btn);
        String str=getIntent().getStringExtra("name");
        database=new dbh(this);
        /*
        * pinting the data in the database by the help of name
        */
        Cursor cursor=database.getDatabyname(str);
        /*
        * as soon as the data is found the pointer is moved back to first position
        */
        cursor.moveToFirst();
        /*
        * as the pointer has been set at the data now getting the data of same index from all other columns and setting them to be edited
        */
        final String name=cursor.getString(cursor.getColumnIndex(dbh.ColName));
        String phone= cursor.getString(cursor.getColumnIndex(dbh.ColNum));
        String pass= cursor.getString(cursor.getColumnIndex(dbh.ColPwd));
        n.setText(name);
        num.setText(phone);
        pwd.setText(pass);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String str1=n.getText().toString();
                String str2=num.getText().toString();
                String str3=pwd.getText().toString();
                if ((!str1.isEmpty()) && ((!str2.isEmpty())) && ((!str3.isEmpty())) )
                {
                    /*
                    * checking whether the data has been successfully updated or not
                   */
                    boolean res=database.updateDetails(new details(str1,str2,str3),name);
                    if(res)
                    {
                        Toast.makeText(getApplicationContext(), "UPDATE SUCCESSFUL", Toast.LENGTH_LONG).show();
                        /*
                        * as soon as the data is updated the finish fn finished the editing and shows backnthe updated list
                        */
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "UPDATE UNSUCCESSFUL", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please enter correct credentials",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    }

