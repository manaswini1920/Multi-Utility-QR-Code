package com.example.mad.animation;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity
{
    EditText name,mobile,email,password,confirmpass;
    Button submit;
    UserRegister userRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=(EditText)findViewById(R.id.name);
        mobile=(EditText)findViewById(R.id.mobile);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.pass);
        confirmpass=(EditText)findViewById(R.id.cinfim);
        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String namee = name.getText().toString();
                String mobilee = mobile.getText().toString();
                String emaill = email.getText().toString();
                String passwordd = password.getText().toString();
                String confirmpasss = confirmpass.getText().toString();
                submit = (Button) findViewById(R.id.submit);
                submit.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        String namee = name.getText().toString();
                        String mobilee = mobile.getText().toString();
                        String emaill = email.getText().toString();
                        String passwordd = password.getText().toString();
                        String confirmpasss = confirmpass.getText().toString();
                        //Pradeep

                        if (name.length() == 0)
                        {
                            name.setError(" Empty");
                            name.requestFocus();
                        }
                        else if (mobilee.length() != 10)
                        {
                            mobile.requestFocus();
                            mobile.setError("Incorrect");
                        }
                        else if (emaill.isEmpty())
                        {
                            email.setError("Empty");
                            email.requestFocus();
                        }
                        else if (!isValideEmail(email.getText().toString()))
                        {
                            email.setError("Enter Valid Email");
                            email.requestFocus();
                        }
                        else if (passwordd.length() == 0)
                        {
                            password.setError("Password not entered");
                            password.requestFocus();
                        }
                        else if (confirmpass.length() == 0)
                        {

                            confirmpass.setError("Empty");
                            confirmpass.requestFocus();

                        }
                        else if (!password.getText().toString().equals(confirmpass.getText().toString()))
                        {
                            confirmpass.setError("Password Not matched");
                            confirmpass.requestFocus();
                        }
                        else
                            {
                            userRegister = new UserRegister(Register.this);
                            SQLiteDatabase sqLiteDatabase = userRegister.getWritableDatabase();

                            String qry = "insert into user_details values('" + namee + "','" + mobilee + "','" + emaill + "','" + passwordd + "')";
                            sqLiteDatabase.execSQL(qry);
                            Toast.makeText(getApplicationContext(), "Success..", Toast.LENGTH_LONG).show();
                            String details=namee+"\n"+mobilee+"\n"+emaill+"\n";
                                Intent intent=new Intent(Register.this,QRGenerator.class);
                                intent.putExtra("details",details);
                                startActivity(intent);
                            name.setText("");
                            mobile.setText("");
                            email.setText("");
                            password.setText("");
                            confirmpass.setText("");



                        }


                    }
                });


            }

            private boolean isValideEmail(String Email)
            {
                String Email_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                Pattern pattern = Pattern.compile(Email_PATTERN);
                Matcher matcher = pattern.matcher(Email);
                return matcher.matches();
            }
        });
    }
}

