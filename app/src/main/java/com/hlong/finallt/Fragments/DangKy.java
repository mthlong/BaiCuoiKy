package com.hlong.finallt.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.hlong.finallt.DangNhap;
import com.hlong.finallt.R;

public class DangKy extends AppCompatActivity {
    TextView alreadyHaveAccount;
    private EditText inputUser, inputPass, inputEmail, inputRepass;
    Button dangKy;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        hideNavigationBar();

        alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);

        inputUser = findViewById(R.id.inputUsername);
        inputPass = findViewById(R.id._inputPassword);
        inputEmail = findViewById(R.id.edtName);
        inputRepass = findViewById(R.id.inputConformPassword);
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(DangKy.this);

        dangKy = findViewById(R.id.btnRegister);
        dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });



        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DangKy.this, DangNhap.class);
                startActivity(i);
            }
        });


    }

    private void check() {
        String username = inputUser.getText().toString();
        String pass = inputPass.getText().toString();
        String email = inputEmail.getText().toString();
        String repass = inputRepass.getText().toString();

        if (username.isEmpty() || username.length()<7) {
            showError(inputUser,"Tên tài khoản không hợp lệ");
        }
        else if(email.isEmpty()||!email.contains("@")){
            showError(inputEmail,"Email không hợp lệ");
        }
        else if(pass.isEmpty() || pass.length()<7 ){
            showError(inputPass,"Mật khẩu phải trên 7 kí tự");
        }
        else if (repass.isEmpty() || !repass.equals(pass)) {
            showError(inputRepass,"Không trùng với mật khẩu vừa nhập");
        }
        else {
            mLoadingBar.setTitle("Đăng ký");
            mLoadingBar.setMessage("Xin vui lòng chờ!, đang kiểm tra");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        mLoadingBar.dismiss();
                        Toast.makeText(DangKy.this, "Đăng ký thành công, xin mời đăng nhập",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DangKy.this, DangNhap.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(DangKy.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }
    private void hideNavigationBar() {
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }

    private void showError(EditText input, String s) {

        input.setError(s);
        input.requestFocus();
    }

    public void back_homepage(View view) {
        startActivity(new Intent(DangKy.this, DangNhap.class));
        finish();
    }
}
