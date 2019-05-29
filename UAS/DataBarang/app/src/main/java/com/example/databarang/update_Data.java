package com.example.databarang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databarang.models.data_barang;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class update_Data extends AppCompatActivity {
    //Deklarasi Variable
    private EditText kodeBaru, namaBaru, jumlahBaru;
    private Button update;
    private DatabaseReference database;
    private FirebaseAuth auth;
    private String cekKode, cekNama, cekJumlah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        getSupportActionBar().setTitle("Update Data");
        kodeBaru = findViewById(R.id.new_kode);
        namaBaru = findViewById(R.id.new_nama);
        jumlahBaru = findViewById(R.id.new_jumlah);
        update = findViewById(R.id.update);

        //Mendapatkan Instance autentikasi dan Referensi dari Database
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        getData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Mendapatkan Data Mahasiswa yang akan dicek
                cekKode = kodeBaru.getText().toString();
                cekNama = namaBaru.getText().toString();
                cekJumlah = jumlahBaru.getText().toString();

                //Mengecek agar tidak ada data yang kosong, saat proses update
                if(isEmpty(cekKode) || isEmpty(cekNama) || isEmpty(cekJumlah)){
                    Toast.makeText(update_Data.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
                    //Menjalankan proses update data
                    data_barang setBarang = new data_barang();
                    setBarang.setKode(kodeBaru.getText().toString());
                    setBarang.setNama(namaBaru.getText().toString());
                    setBarang.setJumlah(jumlahBaru.getText().toString());
                    updateBarang(setBarang);
                }
            }
        });
    }

    // Mengecek apakah ada data yang kosong, sebelum diupdate
    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    //Menampilkan data yang akan di update
    private void getData(){
        final String getKode = getIntent().getExtras().getString("dataKode");
        final String getNama = getIntent().getExtras().getString("dataNama");
        final String getJumlah = getIntent().getExtras().getString("dataJumlah");
        kodeBaru.setText(getKode);
        namaBaru.setText(getNama);
        jumlahBaru.setText(getJumlah);
    }

    //Proses Update data yang sudah ditentukan
    private void updateBarang(data_barang barang){
        String userID = auth.getUid();
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.child("Admin")
                .child(userID)
                .child("Barang")
                .child(getKey)
                .setValue(barang)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        kodeBaru.setText("");
                        namaBaru.setText("");
                        jumlahBaru.setText("");
                        Toast.makeText(update_Data.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
