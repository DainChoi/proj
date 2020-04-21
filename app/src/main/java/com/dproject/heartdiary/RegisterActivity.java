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


public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btn_check).setOnClickListener(onClickListener);
        findViewById(R.id.btn_gotologin).setOnClickListener(onClickListener);
    }

   /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }*/

    @Override public void onBackPressed(){
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_check:
                    //Log.e("클릭", "클릭");
                    register();
                break;

                case R.id.btn_gotologin:
                    //Log.e("클릭", "클릭");
                    myStartActivity(LoginActivity.class);

                    break;
            }
        }
    };

    private void register() {
        String email = ((EditText)findViewById(R.id.et_name)).getText().toString();
        String password = ((EditText)findViewById(R.id.et_password)).getText().toString();
        String passwordcheck = ((EditText)findViewById(R.id.et_passwordcheck)).getText().toString();

        if(email.length() > 0 && password.length() > 0 && passwordcheck.length() > 0){
            if(password.equals(passwordcheck)){

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    // Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //                           updateUI(user);
                                    startToast("회원가입에 성공하였습니다.");
                                   finish(); //회원가입 후 바로 로그인 성공 상태로 이동
                                   // myStartActivity(LoginActivity.class); //회원가입 후 로그인 창으로 이동 but,뒤로가기 시 회원가입창에서 정보 유지됨
                                } else {
                                    // If sign in fails, display a message to the user.
                                    // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    if(task.getException() != null){
                                        startToast(task.getException().toString());
                                    }


                                    //                           updateUI(null);
                                }

                                // ...
                            }
                        });

            }else {
                startToast("비밀번호가 일치하지 않습니다.");

            }


        }else{
            startToast("이메일 또는 비밀번호를 입력해주세요.");
        }


    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    private void myStartActivity(Class c){
        Intent intent=new Intent(this,c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}


