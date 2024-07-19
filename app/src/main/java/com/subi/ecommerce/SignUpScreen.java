package com.subi.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.subi.ecommerce.dao.AccountDAO;
import com.subi.ecommerce.database.DBConnection;
import com.subi.ecommerce.model.Account;

public class SignUpScreen extends AppCompatActivity {
    EditText edtUsername, edtPassword, edtPhone;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtPhone = findViewById(R.id.edtPhone);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account accountEntity = new Account();
                accountEntity.setUsername(edtUsername.getText().toString());
                accountEntity.setPhone(edtPhone.getText().toString());
                accountEntity.setPassword(edtPassword.getText().toString());
                if (validateInput(accountEntity)) {
                    DBConnection database = DBConnection.getInitialDatabase(getApplicationContext());
                    AccountDAO accountDAO = database.accountDAO();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            accountDAO.registerAccount(accountEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUpScreen.this, SignInScreen.class);
                                    startActivity(intent);
                                    finish(); // Kết thúc SignUpScreen
                                }
                            });
                        }
                    }).start();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Fill all Field!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateInput(Account accountEntity){
        if(accountEntity.getUsername().isEmpty() || accountEntity.getPassword().isEmpty()
                || accountEntity.getPhone().isEmpty()){
            return  false;
        }
        return  true;
    }



}




