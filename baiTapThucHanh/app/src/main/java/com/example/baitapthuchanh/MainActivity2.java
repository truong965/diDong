package com.example.baitapthuchanh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends Activity {
    TextView tv;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            Log.d("LifeCycle", " M2 onCreate");
            tv = findViewById(R.id.textView);
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            String data = bundle.getString("myData");
            tv.setText(data);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("returnData", "Hello from M2");
            setResult(RESULT_OK, returnIntent);
            finish();
//            startActivityForResult(returnIntent, 1);
        }

        public MainActivity2() {
            super();
        }

        @Override
        protected void onStart() {
            super.onStart();
            Log.d("LifeCycle", "M2 onStart");
        }

        @Override
        protected void onRestart() {
            super.onRestart();
            Log.d("LifeCycle", "M2 onRestart");
        }

        @Override
        protected void onResume() {
            super.onResume();
            Log.d("LifeCycle", "M2 onResume");
        }

        @Override
        protected void onPause() {
            super.onPause();
            Log.d("LifeCycle", "M1 onPause");
        }

        @Override
        protected void onStop() {
            super.onStop();
            Log.d("LifeCycle", "M2 onStop");
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            Log.d("LifeCycle", "M2 onDestroy");
        }

}