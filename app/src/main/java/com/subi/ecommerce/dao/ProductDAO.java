package com.subi.ecommerce.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.subi.ecommerce.model.Sanpham;

import java.util.List;

@Dao
public interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Sanpham sp);
    @Delete
    void delete(Sanpham sp);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Sanpham sp);
    @Query("SELECT * FROM sanpham")
    public List<Sanpham> loadAllData();
    @Query("SELECT * FROM sanpham WHERE id=:id")
    public List<Sanpham> findDatabyID(int id);
    @Query("DELETE FROM sanpham WHERE id=:id")
    public void deleteItemByIds(int id);
    @Query("DELETE FROM sanpham")
    public void ClearDatabase();
}