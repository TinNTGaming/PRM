package com.subi.ecommerce.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.subi.ecommerce.model.Account;

@Dao
public interface AccountDAO {
    @Insert
    void registerAccount(Account accountEntity);
    @Query("SELECT * FROM account WHERE username = :username AND password = :password")
    Account login(String username, String password);

}
