package com.example.tugas8_sql;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.example.tugas8_sql.databinding.ActivityProfileBinding;


public class ProfileActivity extends AppCompatActivity {
    private ActivityProfileBinding binding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("Toko Gitar Pamungkas");
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());

        //setup view binding
        binding =
                ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferences = getSharedPreferences("AndroidHiveLogin",
                0);
        editor = preferences.edit();
        binding.buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                Intent intent = new Intent(ProfileActivity.this, MainActivity5.class);
                startActivity(intent);
                finish();
            }
                                                        });
    }
}