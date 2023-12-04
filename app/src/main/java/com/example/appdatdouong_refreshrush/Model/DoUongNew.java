package com.example.appdatdouong_refreshrush.Model;

public class DoUongNew {
    private int idNew, giaNew;
    private String tenNew, diaChiNew;

    public DoUongNew() {
    }

    public DoUongNew(int idNew, int giaNew, String tenNew, String diaChiNew) {
        this.idNew = idNew;
        this.giaNew = giaNew;
        this.tenNew = tenNew;
        this.diaChiNew = diaChiNew;
    }

    public int getIdNew() {
        return idNew;
    }

    public void setIdNew(int idNew) {
        this.idNew = idNew;
    }

    public int getGiaNew() {
        return giaNew;
    }

    public void setGiaNew(int giaNew) {
        this.giaNew = giaNew;
    }

    public String getTenNew() {
        return tenNew;
    }

    public void setTenNew(String tenNew) {
        this.tenNew = tenNew;
    }

    public String getDiaChiNew() {
        return diaChiNew;
    }

    public void setDiaChiNew(String diaChiNew) {
        this.diaChiNew = diaChiNew;
    }
}
