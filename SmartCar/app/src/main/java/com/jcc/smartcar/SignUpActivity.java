package com.jcc.smartcar;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        Button btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        EditText editTextName = findViewById(R.id.et_name);
        EditText editTextEmail = findViewById(R.id.et_email);
        EditText editTextPassword = findViewById(R.id.et_password);

        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (validateForm(name, email, password)) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                // Firebase registered user
                                FirebaseUser firebaseUser = task.getResult().getUser();
                                // Registered Email
                                String registeredEmail = firebaseUser.getEmail();

                                // Class user needs to be created for this line to work properly
                                // User user = new User(firebaseUser.getUid(), name, registeredEmail);

                                // call the registerUser function of FirestoreClass to make an entry in the database. FireStroreclass still missing
                                //FirestoreClass().registerUser(this, user)
                            } else {
                                Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private boolean validateForm(final String name, final String email, final String password) {
        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            return true;
        } else {
            showErrorSnackBar("Please fill all fields.");
            return false;
        }
    }
}