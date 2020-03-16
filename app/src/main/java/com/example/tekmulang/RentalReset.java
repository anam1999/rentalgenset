package com.example.tekmulang;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RentalReset extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private TextView Logout;
    private TextView nama;
    //
    private DatabaseReference databaseReference;
    private TextView Namaperental,limaratuswat;
    private Button buttonSave;
    //
    Button cekpesan;
    //
    int Jumlah=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentalreset);

        getSupportActionBar().setTitle("");
        ActionBar menu=getSupportActionBar();
        menu.setDisplayHomeAsUpEnabled(true);
        menu.setDisplayShowHomeEnabled(true);


        cekpesan = (Button) findViewById(R.id.cekpesanan);
        cekpesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameEditText=(EditText)findViewById(R.id.masuknama);
                String nama=nameEditText.getText().toString();
                String namaku=createOrder3(nama);
                panggil3(namaku);
                Log.v("LoginReset","Nama:"+nama);


                int saya=harga();
                String buat=createOrder(saya);
                panggil1(buat);


            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        //
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Namaperental = (TextView) findViewById(R.id.namaperental);
        limaratuswat = (TextView) findViewById(R.id.limaratuswatt);
        buttonSave = (Button) findViewById(R.id.pesansekrang);

        buttonSave.setOnClickListener(this);
        //
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginReset.class));
        }


        FirebaseUser user = firebaseAuth.getCurrentUser();
        nama = (TextView) findViewById(R.id.nama);
        nama.setText("" + user.getEmail());

        Logout = (TextView) findViewById(R.id.keluar);

        Logout.setOnClickListener(this);

    }
    //
    private void saveDataTotal(){
        String nama = Namaperental.getText().toString().trim();
        String limaratus = limaratuswat.getText().toString().trim();

        DataTotal dataTotal = new DataTotal(nama,limaratus);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(dataTotal);

        Toast.makeText(this, "Pesanan anda diproses...", Toast.LENGTH_SHORT).show();
    }

    //PERINTAH MENGGUNAKAN TOMBOL TAMBAH

    public void kurangi(View view){// perintah tombol tambah //
        if(Jumlah==0){
            Toast.makeText(this,"kosong",Toast.LENGTH_SHORT).show();
            return;
        }
        Jumlah = Jumlah -1 ;
        display(Jumlah);
    }
    public void tambahi(View view){//perintah tombol tambah
        if (Jumlah==24){
            Toast.makeText(this,"Maks Rental waktu 24Jam",Toast.LENGTH_SHORT).show();
            return;
        }
        Jumlah = Jumlah +1;
        display(Jumlah);

    }

    //
    private int harga(){
        int harga =50000;
        return Jumlah * harga;
    }

    //MENAMPILKAN INPUTAN NAMA
    private String createOrder3(String nama) {
        String tampil1="\n Nama = "+ nama;
        return tampil1;
    }

    //MENAMPILKAN HASIL PESANAN Rental
    private String createOrder(int limaratus) {
        String ditampilkan="";
        ditampilkan+="\n Jumlah  = "+Jumlah;
        ditampilkan+="\n Harga Rental = Rp " +limaratus;
        return  ditampilkan;
    }

    //mencetak hasil perintah nama yang ditampilkan
    private void panggil3(String nama) {
        TextView priceTextView = (TextView) findViewById(R.id.namaperental);
        priceTextView.setText(nama);
    }
    // mencetak hasil perintah yang di tampilkan
    private void panggil1(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.limaratuswatt);
        priceTextView.setText(message);
    }


    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.tampil1);
        quantityTextView.setText("" + number);
    }



    @Override
    public void onClick(View view) {
        if (view == Logout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginReset.class));
        }
        if (view == buttonSave){
            saveDataTotal();
        }
    }
}
