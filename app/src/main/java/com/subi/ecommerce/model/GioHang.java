package com.subi.ecommerce.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "giohang")

public class GioHang implements Serializable {
    @PrimaryKey(autoGenerate = false)

    private int idSanPham;
    @ColumnInfo

    private String tenSanPham;
    @ColumnInfo

    private String moTa;
    @ColumnInfo

    private String giaSanPham;
    @ColumnInfo

    private String loaiSanPham;
    @ColumnInfo

    private int image;
    @ColumnInfo

    private int soLuong;

    public GioHang() {
    }

    public GioHang(int idSanPham, String tenSanPham, String moTa, String giaSanPham, String loaiSanPham, int image, int soLuong) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.giaSanPham = giaSanPham;
        this.loaiSanPham = loaiSanPham;
        this.image = image;
        this.soLuong = soLuong;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(String giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "GioHang{" +
                "idSanPham=" + idSanPham +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", moTa='" + moTa + '\'' +
                ", giaSanPham='" + giaSanPham + '\'' +
                ", loaiSanPham='" + loaiSanPham + '\'' +
                ", image=" + image +
                ", soLuong=" + soLuong +
                '}';
    }
}
