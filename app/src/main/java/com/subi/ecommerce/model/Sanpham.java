package com.subi.ecommerce.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.subi.ecommerce.R;

import java.io.Serializable;
import java.util.ArrayList;

@Entity (tableName = "sanpham" )
public class Sanpham implements Serializable {
    @PrimaryKey(autoGenerate = false)

    private int id;
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

    public Sanpham() {
    }

    public Sanpham(int id, String tenSanPham, String moTa, String giaSanPham, String loaiSanPham, int image) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.giaSanPham = giaSanPham;
        this.loaiSanPham = loaiSanPham;
        this.image = image;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
