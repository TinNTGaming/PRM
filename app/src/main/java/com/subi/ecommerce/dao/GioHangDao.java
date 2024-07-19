package com.subi.ecommerce.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.subi.ecommerce.model.GioHang;

import java.util.List;

@Dao
public interface   GioHangDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void them(GioHang gioHang);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(GioHang gioHang);

    @Delete
    void xoa(GioHang gioHang);

    @Query("SELECT * FROM giohang")
    List<GioHang> getAll();

    @Query("SELECT * FROM giohang WHERE idSanPham = :id")
    GioHang getGioHang(int id);

    @Query("DELETE FROM giohang")
    void delete();
    @Query("SELECT COUNT(*) FROM giohang")
    int countProductsInCart();

    @Query("SELECT COUNT(*) FROM giohang")
    boolean hasProductsInCart();

}
