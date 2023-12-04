package com.example.appdatdouong_refreshrush.Model;

public class DoUong {
    private int idDoUong, giaDoUong;
    private String tenDoUong, tinhTrang, diaChiQuan;

    public DoUong() {
    }

    public DoUong(int idDoUong, int giaDoUong, String tenDoUong, String diaChiQuan, String tinhTrang) {
        this.idDoUong = idDoUong;
        this.giaDoUong = giaDoUong;
        this.tenDoUong = tenDoUong;
        this.diaChiQuan = diaChiQuan;
        this.tinhTrang = tinhTrang;
    }



    public String getDiaChiQuan() {
        return diaChiQuan;
    }

    public void setDiaChiQuan(String diaChiQuan) {
        this.diaChiQuan = diaChiQuan;
    }

    public int getIdDoUong() {
        return idDoUong;
    }

    public void setIdDoUong(int idDoUong) {
        this.idDoUong = idDoUong;
    }

    public int getGiaDoUong() {
        return giaDoUong;
    }

    public void setGiaDoUong(int giaDoUong) {
        this.giaDoUong = giaDoUong;
    }

    public String getTenDoUong() {
        return tenDoUong;
    }

    public void setTenDoUong(String tenDoUong) {
        this.tenDoUong = tenDoUong;
    }

    

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
