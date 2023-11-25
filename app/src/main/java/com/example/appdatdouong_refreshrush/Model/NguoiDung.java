package com.example.appdatdouong_refreshrush.Model;

public class NguoiDung {
    private String hoTen,tenTaiKhoan, email, soDienThoai, matKhau;

    public NguoiDung() {
    }
    public NguoiDung( String tenTaiKhoan, String matKhau) {
        this.hoTen = hoTen;
        this.tenTaiKhoan = tenTaiKhoan;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
