package com.example.baitapthuchanh;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baitapthuchanh.Models.SinhVien;
import com.example.baitapthuchanh.Models.SinhVienAdapter;

import java.util.ArrayList;

public class MenuTest extends AppCompatActivity {
    private SinhVienAdapter sinhVienAdapter;
    private ArrayList<SinhVien> sinhVienList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_test);
        registerForContextMenu(findViewById(R.id.editTextText));
        sinhVienList =  new ArrayList<>();
        sinhVienAdapter =new SinhVienAdapter(sinhVienList,this);
        ListView listView = findViewById(R.id.list_item);
        listView.setAdapter(sinhVienAdapter);

        initGioiTinhSpiner();
        addActionForButton();
    }
    // option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_test, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        if(item.getItemId()==R.id.danhBa){
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(ContactsContract.Contacts.CONTENT_URI);
        }
        else if(item.getItemId()==R.id.tinNhan){
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("sms:0123456789"));
            intent.putExtra("sms_body","Hello");
        }
        else if(item.getItemId()==R.id.thuVienAnh){
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setType("image/*");
        }
        else if(item.getItemId()==R.id.loginAct){
            intent= new Intent(this, LoginActivity.class);
        } else if (item.getItemId() == R.id.exit) {
            showDialogBox();
            return true;
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
    // context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_test,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        EditText et = findViewById(R.id.editTextText);
        if(item.getItemId() == R.id.normal){
            et.setText("Normal");
            et.setTypeface(null, Typeface.NORMAL);
        }
        else if(item.getItemId() == R.id.bold){
            et.setText("Bold");
            et.setTypeface(null, Typeface.BOLD);
        }
        else if(item.getItemId() == R.id.italic){
            et.setText("Italic");
            et.setTypeface(null, Typeface.ITALIC);
        }
        return super.onContextItemSelected(item);
    }
    private void showDialogBox() {
        new AlertDialog.Builder(this)
                .setTitle("Thoát ứng dụng")
                .setMessage("Bạn có muốn thoát ứng dụng không?")
                .setIcon(android.R.drawable.ic_dialog_alert)  // Sử dụng icon hệ thống để tránh lỗi
                .setPositiveButton("Có", (dialog, which) -> finish())
                .setNegativeButton("Không", (dialog, which) -> dialog.dismiss())
                .show();
    }
    private void initGioiTinhSpiner(){
        String[] gioiTinh = {"Nam","Nữ"};
        Spinner spinner = findViewById(R.id.spinnerGioiTinh);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, gioiTinh);
        adapter.notifyDataSetChanged();
        spinner.setAdapter(adapter);
    }
    private void addActionForButton(){
        Button them = findViewById(R.id.them);
        them.setOnClickListener(v -> {
            EditText font = findViewById(R.id.editTextText);
            Spinner gioiTinh = findViewById(R.id.spinnerGioiTinh);
            EditText ten = findViewById(R.id.editTextHoTen);
            EditText tuoi = findViewById(R.id.editTextTuoi);

            SinhVien sv = new SinhVien();
            sv.setTen(ten.getText().toString());
            sv.setTuoi(Integer.parseInt(tuoi.getText().toString()));
            sv.setGioiTinh(gioiTinh.getSelectedItem().toString().equals("Nam"));
            Bundle bundle = new Bundle();
            bundle.putParcelable("sinhVien",sv);
            bundle.putString("font",font.getText().toString());
            if(themSinhVien(bundle)){
                ten.setText("");
                tuoi.setText("");
                gioiTinh.setSelection(0);
                font.setText("");
            }
        });
        Button xoa = findViewById(R.id.xoa);
        xoa.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("xóa sinh viên")
                    .setMessage("Bạn có muốn xóa sinh viên không?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("có",(dialog,which)->sinhVienAdapter.removeCheckedSinhVien())
                    .setNegativeButton("không",(dialog,which)->dialog.dismiss())
                    .show();
        });
    }
    private boolean themSinhVien(Bundle bundle){
        SinhVien sv = bundle.getParcelable("sinhVien");
        sinhVienAdapter.addSinhVien(sv);
        return true;
    }
}