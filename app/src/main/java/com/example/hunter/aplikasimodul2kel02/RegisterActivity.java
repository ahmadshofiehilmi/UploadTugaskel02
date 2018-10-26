package com.example.hunter.aplikasimodul2kel02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseHandler databaseHandler;
    private User usermodel;
    private EditText etUsernameRegister;
    private EditText etPasswordRegister;
    private Button btRegister;
    private String username, password;

    @Override
    public void onBackPressed() {
        back();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        register();
    }

    private void register() {
        btRegister.setOnClickListener(V ->
                initDataHandler()
        );
    }

    private void back() {
        Intent reg = new Intent(this, LoginActivity.class);
        startActivity(reg);
        finish();
    }


    private void initView() {
        etUsernameRegister = findViewById(R.id.etUsernameRegister);
        etPasswordRegister = findViewById(R.id.etPasswordRegister);
        btRegister = findViewById(R.id.btRegister);
    }

    private void initUser() {
        username = etUsernameRegister.getText().toString();
        password = etPasswordRegister.getText().toString();

        usermodel = new User();
        usermodel.setUsername(username);
        usermodel.setPassword(password);
    }

    private void initDataHandler() {
        initUser();
        String pass = etPasswordRegister.getText().toString();
        if (TextUtils.isEmpty(pass) || pass.length() < 6) {
            etPasswordRegister.setError("You must have 6 characters in your password");
            return;
        } else {
            databaseHandler = new DatabaseHandler(this);
            databaseHandler.addUser(usermodel);
            User model = databaseHandler.getMahasiswa(1);
            Log.e("record", model.getUsername().toString());
            Intent admin = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(admin);
            finish();
        }
    }
}