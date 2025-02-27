package com.example.baitapthuchanh.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class SinhVien implements Parcelable {
    private String ten;
    private int tuoi;
    private boolean gioiTinh;
    private  boolean checked;
    public  SinhVien(){
        this.checked=false;
    };
    public  SinhVien(String ten, int tuoi, boolean gioiTinh){
        this.ten = ten;
        this.tuoi = tuoi;
        this.gioiTinh = gioiTinh;
        this.checked = false;
    }
    // viết đúng thứ tu ghi
    protected  SinhVien(Parcel in){
        ten = in.readString();
        tuoi = in.readInt();
        gioiTinh = in.readByte() != 0;
    }
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(ten);
        dest.writeInt(tuoi);
        dest.writeByte((byte) (gioiTinh ? 1 : 0));
    }
    public  static  final  Creator<SinhVien> CREATOR= new Creator<SinhVien>() {
        @Override
        public SinhVien createFromParcel(Parcel in) {
            return new SinhVien(in);
        }

        @Override
        public SinhVien[] newArray(int size) {
            return new SinhVien[size];
        }
    };
    public String getTen() {
        return ten;
    }


    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
