package com.example.thuchanh1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.thuchanh1.Models.DanhMuc;
import com.example.thuchanh1.Models.DanhMucAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BT20 extends AppCompatActivity {
    private List<DanhMuc> danhMucList;
    private DanhMucAdapter danhMucAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bt20);
        initView();
    }
    private void initView(){
        danhMucList = new ArrayList<>();
        Map<String,String> mapDanhMuc1=new LinkedHashMap<>();
        mapDanhMuc1.put("iphone","baseline_star_24");
        mapDanhMuc1.put("samsung","baseline_phone_android_24");
        mapDanhMuc1.put("xiaomi","baseline_phone_android_24");
        DanhMuc danhMuc1 = new DanhMuc("Điện thoại",mapDanhMuc1);

        Map<String,String> mapDanhMuc2=new LinkedHashMap<>();
        mapDanhMuc2.put("áo","baseline_star_24");
        mapDanhMuc2.put("quần","baseline_phone_android_24");
        mapDanhMuc2.put("giày","baseline_phone_android_24");
        DanhMuc danhMuc2 = new DanhMuc("Quần áo",mapDanhMuc2);

        Map<String,String> mapDanhMuc3=new LinkedHashMap<>();
        mapDanhMuc3.put("bánh","baseline_star_24");
        mapDanhMuc3.put("kẹo","baseline_phone_android_24");
        mapDanhMuc3.put("nước","baseline_phone_android_24");
        DanhMuc danhMuc3 = new DanhMuc("Thực phẩm",mapDanhMuc3);

        danhMucList.add(danhMuc1);
        danhMucList.add(danhMuc2);
        danhMucList.add(danhMuc3);

        danhMucAdapter = new DanhMucAdapter(mapDanhMuc1,this);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(danhMucAdapter);

        ArrayList<String> names = new ArrayList<>(danhMucList.stream().map(DanhMuc::getTen).collect(Collectors.toList()));
        ArrayAdapter<String> tenDanhMucAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, names);
        tenDanhMucAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(tenDanhMucAdapter);
        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Map<String, String> item = danhMucList.get(position).getItems();
                danhMucAdapter.updateItems(item);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}