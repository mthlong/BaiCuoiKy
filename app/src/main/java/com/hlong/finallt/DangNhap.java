package com.hlong.finallt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.hlong.finallt.Fragments.DangKy;
import com.hlong.finallt.Fragments.TaikhoanFragment;

public class DangNhap extends AppCompatActivity {
    TextView textViewSignUp, info;
    ImageView profile;
    private EditText inputEmail, inputPass;
    Button dangNhap,btnLoginPhone,btnLoginFoody;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;
    private LoginButton loginButtonFB;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        hideNavigationBar();

        textViewSignUp = findViewById(R.id.textViewSignUp);
        inputPass = findViewById(R.id._inputPassword);
        inputEmail = findViewById(R.id.edtName);
        dangNhap = findViewById(R.id._btnlogin);
        btnLoginPhone =findViewById(R.id.btnLoginPhone);
        loginButtonFB = findViewById(R.id.btnFacebook);
        info = findViewById(R.id.info);
        profile = findViewById(R.id.profile);

        callbackManager = CallbackManager.Factory.create();

        loginButtonFB.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(DangNhap.this, Navbottom.class);
                startActivity(i);
                Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(DangNhap.this);

        dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
        btnLoginPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DangNhap.this , DangKyDT.class);
                startActivity(i);
            }
        });
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DangNhap.this , DangKy.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode,data);
    }

    private void check() {
        String pass = inputPass.getText().toString();
        String email = inputEmail.getText().toString();
        if(email.isEmpty()||!email.contains("@")){
            showError(inputEmail,"Email không hợp lệ");
        }
        else if(pass.isEmpty() || pass.length()<7 ){
            showError(inputPass,"Mật khẩu phải trên 7 kí tự");
        }else {
            mLoadingBar.setTitle("Đăng nhập");
            mLoadingBar.setMessage("Xin vui lòng chờ!, đang kiểm tra");
            mLoadingBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(DangNhap.this, "Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                        mLoadingBar.dismiss();
                        Intent intent = new Intent(DangNhap.this, Navbottom.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(DangNhap.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(DangNhap.this, TaikhoanFragment.class));
        finish();
    }
}
