package com.jcc.smartcar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginPageActivity extends BaseActivity {

    EditText mail;
    EditText password;
    Button signIn;
    Button signInGoogle;
    TextView signUp;
    TextView forgotPassword;
    ProgressBar progressBarLogin;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mail = findViewById(R.id.editTextLoginEmailAddress);
        password = findViewById(R.id.editTextLoginPassword);
        signIn = findViewById(R.id.buttonLoginPage);
        /*signInGoogle = findViewById(R.id.signInButtonLogin);*/
        signUp = findViewById(R.id.textViewLoginRegister);
        forgotPassword = findViewById(R.id.textViewLoginForgotPassword);
        progressBarLogin = findViewById(R.id.progressBarLogin);

        signIn.setOnClickListener(view -> {

            String userEmail = mail.getText().toString();
            String userPassword = password.getText().toString();

            signInWithFirebase(userEmail, userPassword);

        });

        /*signInGoogle.setOnClickListener(view -> {

        });*/

        signUp.setOnClickListener(view -> {

            Intent i = new Intent(LoginPageActivity.this, SignUpActivity.class);
            startActivity(i);

        });

        forgotPassword.setOnClickListener(view -> {

        });
    }

    public void signInWithFirebase(String userEmail, String userPassword){
        progressBarLogin.setVisibility(View.VISIBLE);
        signIn.setClickable(false);

        auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, task -> {

                    if (task.isSuccessful()){
                        Intent i = new Intent(LoginPageActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                                progressBarLogin.setVisibility(View.INVISIBLE);
                                Toast.makeText(LoginPageActivity.this, "Login is successful!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                                Toast.makeText(LoginPageActivity.this, "Login is not successful!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}