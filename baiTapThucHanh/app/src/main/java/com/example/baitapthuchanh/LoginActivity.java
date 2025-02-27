package com.example.baitapthuchanh;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.baitapthuchanh.databinding.ActivityLoginBinding;
import com.example.baitapthuchanh.databinding.CustomInputFieldBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUserNameAndPass();
        addActionDangNhapVaThoatButton();
    }
    private void addActionDangNhapVaThoatButton(){
        Button dangNhap = findViewById(R.id.dangNhap);
        dangNhap.setOnClickListener(v -> {
            CustomInputFieldBinding usernameBinding = CustomInputFieldBinding.bind(binding.tenDangNhapInclude.getRoot());
            CustomInputFieldBinding passwordBinding = CustomInputFieldBinding.bind(binding.matKhauInclude.getRoot());
            if(usernameBinding.etInput.getText().toString().isEmpty() || passwordBinding.etInput.getText().toString().isEmpty()) {
                new AlertDialog.Builder(this)
                        .setTitle("Lỗi")
                        .setMessage("Tên đăng nhập hoặc mật khẩu không được để trống")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("OK", null)
                        .show();
            }
            else {
                new AlertDialog.Builder(this)
                        .setTitle("Đăng nhập")
                        .setMessage("Chúc mừng bạn đã đăng nhập thành công")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton("OK", null)
                        .show();
            }
        });
        Button thoat = findViewById(R.id.thoat);
        thoat.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Thoát ứng dụng")
                    .setMessage("Bạn có muốn thoát ứng dụng không?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("có",(dialog,which)-> finish())
                    .setNegativeButton("không",null)
                    .show();
        });
    }
    private void initUserNameAndPass(){
        CustomInputFieldBinding usernameBinding = CustomInputFieldBinding.bind(binding.tenDangNhapInclude.getRoot());

        CustomInputFieldBinding passwordBinding = CustomInputFieldBinding.bind(binding.matKhauInclude.getRoot());
        // Thiết lập nội dung
        usernameBinding.tvLabel.setText("Tên đăng nhập");
        usernameBinding.etInput.setHint("Nhập tên đăng nhập");

        passwordBinding.tvLabel.setText("Mật khẩu");
        passwordBinding.etInput.setHint("Nhập mật khẩu");
        passwordBinding.etInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }
}