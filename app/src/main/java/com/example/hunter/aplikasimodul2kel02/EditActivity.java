package com.example.hunter.aplikasimodul2kel02;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private User usermodel;
    private EditText etUserAbout, etPassAbout;
    private Button btnUpdate, btnDelete;
    DatabaseHandler cek = LoginActivity.getPresenter();
    User usr = LoginActivity.getUser();
    String name = usr.getUsername();
    String pass = usr.getPassword();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initView();
        update();
        delete();
        deletePreference();
        etUserAbout.setText(name);
        etPassAbout.setText(pass);
        initUser();
    }

    private void initView() {
        etUserAbout = findViewById(R.id.etUserAbout);
        etPassAbout = findViewById(R.id.etPassAbout);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
    }

    private void initUser() {
        name = etUserAbout.getText().toString();
        pass = etPassAbout.getText().toString();

        usr.setUsername(name);
        usr.setPassword(pass);
    }

    private void update() {
        btnUpdate.setOnClickListener(v -> {
            initUser();
            cek.updateMahasiswa(usr);
            Intent reg = new Intent(this, MainActivity.class);
            startActivity(reg);
            finish();
        });

    }

    private void delete() {
        btnDelete.setOnClickListener(view -> showAlertDialog());
    }

    public void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Apa ingin menghapus Akun ini?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deletePreference();
                        Intent login = new Intent(EditActivity.this, LoginActivity.class);
                        startActivity(login);
                        cek.deleteModel(usr);
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void deletePreference(){
        SharedPreferences preferences = getSharedPreferences("LoginPreference", MODE_PRIVATE);
        preferences.edit().remove("username").commit();
        preferences.edit().remove("password").commit();
    }
    @Override
    public void onBackPressed() {
        back();
    }

    private void back() {
        Intent reg = new Intent(this, MainActivity.class);
        startActivity(reg);
        finish();
    }
}
