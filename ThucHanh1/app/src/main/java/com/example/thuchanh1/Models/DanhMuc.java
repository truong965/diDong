package com.example.thuchanh1.Models;

import java.util.Map;

public class DanhMuc {
    private String ten;
    private Map<String,String> items; // ten item/ten icon
    public DanhMuc(String ten, Map<String, String> items) {
        this.ten = ten;
        this.items = items;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Map<String, String> getItems() {
        return items;
    }

    public void setItems(Map<String, String> items) {
        this.items = items;
    }
}
