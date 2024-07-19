package com.subi.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.subi.ecommerce.dao.AccountDAO;
import com.subi.ecommerce.database.DBConnection;
import com.subi.ecommerce.model.Account;

public class SignInScreen extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameText = edtUsername.getText().toString();
                String passwordText = edtPassword.getText().toString();
                if(usernameText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Fill  all Fields",Toast.LENGTH_SHORT).show();
                }else {
                    DBConnection database =  DBConnection.getInitialDatabase(getApplicationContext());
                    AccountDAO accountDAO = database.accountDAO();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Account accountEntity = accountDAO.login(usernameText,passwordText);
                            if(accountEntity == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),"Invalid",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                startActivity(new Intent(SignInScreen.this, MainActivity.class));
                            }
                        }
                    }).start();
                }
            }
        });
    }
}