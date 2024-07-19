package com.subi.ecommerce.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "account")
public class Account  implements Serializable  {

    @PrimaryKey(autoGenerate = true)
    public  int Id;
    @ColumnInfo(name="username")
    public String username;
    @ColumnInfo(name="phone")
    public String phone;
    @ColumnInfo(name="password")
    public String password;

    public Account() {
    }

    public Account(int id, String username, String phone, String password) {
        Id = id;
        this.username = username;
        this.phone = phone;
        this.password = password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
