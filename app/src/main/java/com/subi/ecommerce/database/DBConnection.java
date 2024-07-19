package com.subi.ecommerce.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import androidx.room.RoomDatabase;

import com.subi.ecommerce.dao.AccountDAO;
import com.subi.ecommerce.dao.GioHangDao;
import com.subi.ecommerce.dao.ProductDAO;
import com.subi.ecommerce.model.Account;
import com.subi.ecommerce.model.GioHang;
import com.subi.ecommerce.model.Sanpham;

@Database(entities = {Sanpham.class, Account.class, GioHang.class}, version = 3)

public abstract class DBConnection extends RoomDatabase {
    private static DBConnection INSTANCE;
    public abstract ProductDAO createdataDAO();
    public abstract GioHangDao createGioHangDao();
    public abstract AccountDAO accountDAO();

    public static DBConnection getInitialDatabase(Context context)
    {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, DBConnection.class,
                            "Grocery.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }


}
