package com.jcc.smartcar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginPageActivity extends AppCompatActivity {

    EditText mail;
    EditText password;
    Button signIn;
    Button signInGoogle;
    TextView signUp;
    TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mail = findViewById(R.id.editTextLoginEmailAddress);
        password = findViewById(R.id.editTextLoginPassword);
        signIn = findViewById(R.id.buttonLoginPage);
        signInGoogle = findViewById(R.id.signInButtonLogin);
        signUp = findViewById(R.id.textViewLoginRegister);
        forgotPassword = findViewById(R.id.textViewLoginForgotPassword);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        signInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(LoginPageActivity.this, SignUpActivity.class);
                startActivity(i);

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}