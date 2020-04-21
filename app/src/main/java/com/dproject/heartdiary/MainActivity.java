package com.dproject.heartdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
//로그인 시 로그아웃 버튼 생성되는 액티비티
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null){
            startRegisterActivity();
        }else{
                for (UserInfo profile : user.getProviderData()) {
                    String name = profile.getDisplayName();
                }

        }*/

        findViewById(R.id.btnLogout).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnLogout:
                    FirebaseAuth.getInstance().signOut();
                    startRegisterActivity();
                    break;
            }

        }
    };



    private void startRegisterActivity(){
        Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}
