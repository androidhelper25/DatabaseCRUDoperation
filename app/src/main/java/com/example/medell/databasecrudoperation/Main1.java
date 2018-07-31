package com.example.medell.databasecrudoperation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main1 extends AppCompatActivity {
    dbh dbh1;
    EditText name,num,pwd;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        name=(EditText)findViewById(R.id.name);
        num=(EditText)findViewById(R.id.ph);
        pwd=(EditText)findViewById(R.id.pwd);
        btn=(Button)findViewById(R.id.btn);
        dbh1=new dbh(this);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String str1=name.getText().toString();
                String str2=num.getText().toString();
                String str3=pwd.getText().toString();
                if ((!str1.isEmpty()) && ((!str2.isEmpty())) && ((!str3.isEmpty())) )
                {
                    /*
                        // checking whether details are inserted or not
                        //METHOD 1
                        boolean res=dbHelper.insertDetails(new Details(str1,str2,str3));
                        if(res)
                            {
                            Toast.makeText(getApplicationContext(), "SETUP SUCCESSFUL", Toast.LENGTH_LONG).show();
                            }
                        else
                            {
                            Toast.makeText(getApplicationContext(), "SETUP UNSUCCESSFUL", Toast.LENGTH_LONG).show();
                            }*/
                    //Method 2
                    dbh1.insertDetails(new details(str1,str2,str3));
                    Toast.makeText(getApplicationContext(), "SETUP SUCCESSFUL", Toast.LENGTH_LONG).show();
                    name.setText("");
                    num.setText("");
                    pwd.setText("");
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please enter correct credentials",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.show:
            {
                Intent intent= new Intent(Main1.this,data_show.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
