package com.example.thuchanh1.Models;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.thuchanh1.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DanhMucAdapter extends BaseAdapter {
    private Map<String, String> items;
    private Context context;

    public DanhMucAdapter(Map<String, String> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0; // Trả về số lượng phần tử trong items
    }

    @Override
    public Object getItem(int position) {
        List<String> keys = new ArrayList<>(items.keySet());
        return keys.get(position); // Trả về key tại vị trí 'position'
    }

    @Override
    public long getItemId(int position) {
        return position; // Sử dụng position làm ID của item
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_danh_muc, parent, false);
        }

        // Lấy reference tới MaterialTextView
        MaterialTextView textView = convertView.findViewById(R.id.materialTextView);

        // Lấy key từ vị trí 'position' trong items map
        String key = (String) getItem(position);
        Log.d("DanhMucAdapter", "getView: " + key);
        // Lấy giá trị drawable name từ map
        String drawableName = items.get(key);

        // Thay đổi text của MaterialTextView
        textView.setText(key);

        // Xử lý drawable nếu có (nếu có drawableName)
        if (drawableName != null && !drawableName.isEmpty()) {
            int drawableResId = context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
            if (drawableResId != 0) {
                // Đặt icon ở phía trái (drawableStart) của TextView
                textView.setCompoundDrawablesWithIntrinsicBounds(drawableResId, 0, 0, 0);  // (Left, Top, Right, Bottom)
            }
        }

        return convertView;
    }

    // Phương thức cập nhật dữ liệu
    public void updateItems(Map<String, String> items) {
        this.items = items;
        notifyDataSetChanged(); // Thông báo rằng dữ liệu đã thay đổi
    }
}
