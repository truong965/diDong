package com.example.thuchanh1.Models;

public class KhachHang {
    private  String name;
    private int soLuongSach;
    public KhachHang() {}
    public KhachHang(String name, int soLuongSach, boolean isVip) {
        this.name = name;
        this.soLuongSach = soLuongSach;
        this.isVip = isVip;
    }

    private boolean isVip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSoLuongSach() {
        return soLuongSach;
    }

    public void setSoLuongSach(int soLuongSach) {
        this.soLuongSach = soLuongSach;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }
}

