package com.example.baitapthuchanh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class radioAndCheckBox extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_radio_and_check_box);
        seekBar();
        Button guiButton = findViewById(R.id.guiB);

        guiButton.setOnClickListener(v -> {
            Intent mainActivity = new Intent(radioAndCheckBox.this, MainActivity.class);
            Bundle bundle = getInfo();
            mainActivity.putExtras(bundle);
            startActivity(mainActivity);
        });

    }
    private Bundle getInfo() {
        Bundle bundle = new Bundle();
        EditText name = findViewById(R.id.hoTenET);
        bundle.putString("name", name.getText().toString());
        EditText cccd = findViewById(R.id.cccdET);
        bundle.putString("cccd", cccd.getText().toString());
        RadioGroup group = findViewById(R.id.radioGroup);
        int selectedId = group.getCheckedRadioButtonId();
        if(selectedId != -1) {
            RadioButton selected = findViewById(selectedId);
            bundle.putString("bangCap", selected.getText().toString());
        }
        LinearLayout checkBoxGroup = findViewById(R.id.checkBoxList);
        ArrayList<String> selected = new ArrayList<>();
        for(int i = 0; i < checkBoxGroup.getChildCount(); i++) {
            CheckBox checkBox = (CheckBox) checkBoxGroup.getChildAt(i);
            if(checkBox.isChecked()) {
                selected.add(checkBox.getText().toString());
                Log.d("CheckBox", checkBox.getText().toString());
            }
        }
        bundle.putStringArrayList("soThich", selected);
        EditText editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);
        bundle.putString("ghiChu", editTextTextMultiLine.getText().toString());
        return bundle;
    }
    private void seekBar(){
        SeekBar seekBar = findViewById(R.id.seekBar);
        TextView textView = findViewById(R.id.seekBarValue);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("SeekBar", "Start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("SeekBar", "Stop");
            }
        });
    }
}