package com.example.tekmulang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditProfilActivity extends AppCompatActivity {
    private Button simpan, batalkan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        simpan = (Button)findViewById(R.id.simpan);
        batalkan=(Button)findViewById(R.id.batalkan);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent simpans = new Intent(getApplicationContext(), AccountReset.class);
                startActivity(simpans);
            }
        });
        batalkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent batals = new Intent(getApplicationContext(), AccountReset.class);
                startActivity(batals);
            }
        });
    }

}
