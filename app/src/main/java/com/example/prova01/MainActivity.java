package com.example.prova01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText edtName, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnLogin = findViewById(R.id.btnLogin);
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String name = edtName.getText().toString().trim();
                    String password = edtPassword.getText().toString().trim();
                    if (name.length() < 1) {
                        Toast.makeText(MainActivity.this, "Enter a valid login!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (password.length() < 1) {
                        Toast.makeText(MainActivity.this, "Enter a valid password!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    boolean isCorrectCredentials = verifyPassword(name, password);

                    if (!isCorrectCredentials) {
                        Toast.makeText(MainActivity.this, "Incorrect user or password!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    startActivity(new Intent(MainActivity.this, SecondaryActivity.class));

                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this, "Unexpected error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private Map<String, String> getLogins() {
        Map<String, String> logins = new HashMap<String, String>();
        logins.put("Administrador", "Administrador");
        logins.put("Adm", "Adm123");
        logins.put("Administrator", "Que3B1eng4ElT0r0");
        logins.put("Root", "pr0m1uscu0");

        return logins;
    }

    private boolean verifyPassword(String name, String password) {
        Map<String, String> logins = getLogins();
        String correctPassword = logins.get(name);

        if (correctPassword == null || !correctPassword.equals(password)) {
            return false;
        }

        return true;
    }
}