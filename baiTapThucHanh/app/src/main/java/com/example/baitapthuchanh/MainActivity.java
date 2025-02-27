package com.example.baitapthuchanh;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("LifeCycle", " M1 onCreate");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            changeContent(bundle);
        }
    }
    private void changeContent(Bundle bundle){
        EditText et=findViewById(R.id.hoTenET);
        et.setText(bundle.getString("name"));
        et=findViewById(R.id.cccdET);
        et.setText(bundle.getString("cccd"));
        et=findViewById(R.id.bangCap);
        et.setText(bundle.getString("bangCap"));
        et=findViewById(R.id.soThich);
        ArrayList<String> soThich = bundle.getStringArrayList("soThich");
        StringBuilder stringBuilder = new StringBuilder();
        for(String s: soThich){
            stringBuilder.append(s).append(", ");
        }
        et.setText(stringBuilder.toString());
        et=findViewById(R.id.editTextTextMultiLine);
        et.setText(bundle.getString("ghiChu"));
    }
    public MainActivity() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LifeCycle", "M1 onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LifeCycle", "M1 onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LifeCycle", "M1 onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LifeCycle", "M1 onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LifeCycle", "M1 onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycle", "M1 onDestroy");
    }
    // dùng dê nhann bundle trả về của 1 activity khác
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            if (resultCode == RESULT_OK) {
//                String returnData = data.getStringExtra("returnData");
//                TextView tv = findViewById(R.id.textView1);
//                tv.setText(returnData);
//            }
//        }
//    }
}