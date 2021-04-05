package com.bcit.comp3717_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class HomePageActivity extends AppCompatActivity {

    private TextView userName;
    private Button logOut, addEventBtn, friendEventBtn, manageContact;

    //jennie
    private Button accountSettings;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        userName = (TextView) findViewById(R.id.home_tv_username);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User currentUserProfile = dataSnapshot.getValue(User.class);

                if(currentUserProfile !=null) {
                    String currentUserName = currentUserProfile.name;
                    userName.setText(currentUserName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePageActivity.this,
                        "Something went Wrong.....",
                        Toast.LENGTH_LONG).show();
            }

        });


        logOut = (Button) findViewById(R.id.home_button_logout);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent mainIntent  = new Intent(HomePageActivity.this,
                        MainActivity.class);
                startActivity(mainIntent);
            }
        });


        addEventBtn = (Button)findViewById(R.id.home_button_addViewMyEvent);
        addEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addEventIntent  = new Intent(HomePageActivity.this,
                        AddEventsActivity.class);
                startActivity(addEventIntent);
            }
        });

        friendEventBtn = (Button)findViewById(R.id.home_button_view_events);
        friendEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addEventIntent  = new Intent(HomePageActivity.this,
                        FriendsEventsActivity.class);
                startActivity(addEventIntent);
            }
        });

        //jennie
        accountSettings = (Button) findViewById(R.id.home_button_acc_set);
        accountSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, AccountSettings.class));
            }
        });

        manageContact = (Button)findViewById(R.id.home_button_manage_contacts);
        manageContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addEventIntent  = new Intent(HomePageActivity.this,
                        ManageContacts.class);
                startActivity(addEventIntent);
            }
        });

    }
}