package com.jcc.smartcar;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MoreFragment extends Fragment {

    View root;
    EditText editTextName;
    EditText editTextEmail;
    EditText editTextPassword;
    TextView logout;
    TextView edit;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseUser firebaseUser;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_more, container, false);

        logout = root.findViewById(R.id.textViewLogOut);
        edit = root.findViewById(R.id.textViewEdit);
        editTextName = root.findViewById(R.id.editTextName);
        editTextEmail = root.findViewById(R.id.editEmailAdressProfileInfo);
        editTextPassword = root.findViewById(R.id.editPasswordProfileInfo);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            String name = user.getDisplayName();
            String email = user.getEmail();
            editTextName.setText(name);
            editTextEmail.setText(email);

        }

        //Get information about user from RealTime Database
        getUserInfo();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(auth.getCurrentUser()!=null){
                    auth.signOut();
                    Intent intent = new Intent(getActivity(), LoginPageActivity.class);
                    startActivity(intent);
                }
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });
        return  root;
    }
    //Get user information from Realtime Database
    public void getUserInfo(){

        reference.child("User").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("userName").getValue().toString();
                String email = snapshot.child("userEmail").getValue().toString();
                String password = snapshot.child("userPassword").getValue().toString();
                editTextName.setText(name);
                editTextEmail.setText(email);
                editTextPassword.setText(password);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Update user name
    public void updateProfile(){
        String userName = editTextName.getText().toString();
        reference.child("User").child(firebaseUser.getUid()).child("userName").setValue(userName);
    }

}