package com.example.mad.animation;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{
    TextView register;
    Button submit;
    EditText U_email,password;
    SQLiteDatabase db;
    UserRegister userRegister;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        U_email=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.pass);
        userRegister=new UserRegister(this);
        db=userRegister.getReadableDatabase();
        register=(TextView) findViewById(R.id.reg);
        submit=(Button) findViewById(R.id.submit);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Register.class);
                startActivity(intent);

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                String email = U_email.getText().toString();
                String pass = password.getText().toString();
                Boolean emaipassword=userRegister.emailpassword(email,pass);
                if(emaipassword==true)
                {
                    Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(MainActivity.this,QRScanner.class);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"User name or password incorrect",Toast.LENGTH_LONG).show();


                }


            }
        });



    }
}
