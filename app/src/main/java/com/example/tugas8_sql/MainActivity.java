package com.example.tugas8_sql;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import com.example.tugas8_sql.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    private ActivityMainBinding binding;

    RecyclerView recyclerView;
    String s1[],s2[],s3[];
    int images[] = {R.drawable.yamahaf310,R.drawable.elektrikcortm200,R.drawable.bassfender};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("Toko Gitar Pamungkas");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        binding.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance().enqueueUniqueWork(
                        "Notifikasi", ExistingWorkPolicy.REPLACE, request);
            }
        });

        dl = (DrawerLayout)findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(abdt);
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home){
                    Intent a = new Intent(MainActivity.this, MainActivity.class);startActivity(a);
                }else if (id == R.id.nav_chat){
                    Intent a = new Intent(MainActivity.this, MainActivity2.class);startActivity(a);
                }
                else if (id == R.id.nav_alarm){
                    Intent a = new Intent(MainActivity.this, MainActivity3.class);startActivity(a);
                }
                else if (id == R.id.nav_api){
                    Intent a = new Intent(MainActivity.this, MainActivity4.class);startActivity(a);
                }
                else if (id == R.id.nav_shared){
                    Intent a = new Intent(MainActivity.this, MainActivity5.class);startActivity(a);
                }
                return true;
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        s1 = getResources().getStringArray(R.array.Sepeda);
        s2 = getResources().getStringArray(R.array.deskripsi);
        s3 = getResources().getStringArray(R.array.star);

        SepedaAdapter appAdapter = new SepedaAdapter(
                this,s1,s2,s3,images
        );

        recyclerView.setAdapter(appAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                MainActivity.this,LinearLayoutManager.HORIZONTAL,false
        );

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}