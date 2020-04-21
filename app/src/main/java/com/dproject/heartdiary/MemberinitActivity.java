package com.dproject.heartdiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MemberinitActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btnLogin).setOnClickListener(onClickListener);
        findViewById(R.id.btnRegister).setOnClickListener(onClickListener);
        findViewById(R.id.btnPasswordReset).setOnClickListener(onClickListener);
    }

   /* @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }*/

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnLogin:
                    //Log.e("클릭", "클릭");
                    login();
                break;

                case R.id.btnRegister:
                    //Log.e("클릭", "클릭");
                    startRegisterActivity();

                    break;

                case R.id.btnPasswordReset:
                    //Log.e("클릭", "클릭");
                    startPasswordResetActivity();
                    break;

            }
        }
    };

    private void login() {
        String email = ((EditText)findViewById(R.id.et_name)).getText().toString();
        String password = ((EditText)findViewById(R.id.et_password)).getText().toString();

        if(email.length() > 0 && password.length() > 0 ){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("로그인에 성공하였습니다.");
                               startMainActivity();
                            } else {
                                // If sign in fails, display a message to the user.
                                // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                if(task.getException() != null) {
                                    startToast(task.getException().toString());
                                }
                                }
                            }
                        });

        }else{
            startToast("이메일 또는 비밀번호를 입력해주세요.");
        }


    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void startMainActivity(){
        Intent intent=new Intent(MemberinitActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void startPasswordResetActivity(){
        Intent intent=new Intent(MemberinitActivity.this,PasswordResetActivity.class);
        startActivity(intent);
    }

    /*private void myStartActivity(Class c){
        Intent intent=new Intent(this, c);
        startActivity(intent);
    }*/

    private void startRegisterActivity(){
        Intent intent=new Intent(MemberinitActivity.this,RegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}


