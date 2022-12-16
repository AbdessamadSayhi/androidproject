package com.example.insertodb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    EditText username,password,repassword;
    Button signup,signin;
    DB_Helper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        signup = findViewById(R.id.signup);
        signin = findViewById(R.id.signin);
        DB = new DB_Helper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                    Toast.makeText(Signup.this,"Tous les champs sont requis",Toast.LENGTH_SHORT).show();
                else{
                    if (pass.equals(repass)){
                        Boolean checkusername = DB.checkUsername(user);
                        if (!checkusername) {
                            Boolean insert = DB.insertData(user,pass);
                            if(insert){
                                Toast.makeText(Signup.this,"Enregistré avec succès",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Signup.this,"échec de l'enregistrement",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Signup.this,"Le nom existe déjà",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Signup.this,"Les mots de passe ne correspondent pas",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Signup.class);
                startActivity(intent);
            }
        });
    }
}