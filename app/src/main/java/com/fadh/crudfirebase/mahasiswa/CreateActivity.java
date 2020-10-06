package com.fadh.crudfirebase.mahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fadh.crudfirebase.R;
import com.fadh.crudfirebase.model.Mahasiswa;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editNim , editNama;
    private Button btnSave;

    private Mahasiswa mahasiswa;

    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        editNama = findViewById(R.id.edt_nama);
        editNim = findViewById(R.id.edt_nim);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(this);

        mahasiswa = new Mahasiswa();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save){
            saveMahasiswa();
        }
    }
    private void saveMahasiswa(){
        String nama = editNama.getText().toString().trim();
        String nim = editNim.getText().toString().trim();

        boolean isEmptyFields = false;

        if(TextUtils.isEmpty(nama)){
            isEmptyFields = true;
            editNama.setError("Field jangan Kosong");
        }
        if (TextUtils.isEmpty(nim)){
            isEmptyFields = true;
            editNim.setError("Field jangan kosong");
        }
        if (! isEmptyFields){
            Toast.makeText(CreateActivity.this ,"Saving Data...", Toast.LENGTH_SHORT).show();

            DatabaseReference dbMahasiswa = mDatabase.child("Mahasiswa");

            String id = dbMahasiswa.push().getKey();
            mahasiswa.setId(id);
            mahasiswa.setNama(nama);
            mahasiswa.setNim(nim);
            mahasiswa.setPhoto("");

            //inser data
            dbMahasiswa.child(id).setValue(mahasiswa);

            finish();
        }
    }
}