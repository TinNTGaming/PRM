package com.subi.ecommerce;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.subi.ecommerce.adapter.GioHangAdapter;
import com.subi.ecommerce.adapter.ShowDialog;
import com.subi.ecommerce.dao.GioHangDao;
import com.subi.ecommerce.database.DBConnection;
import com.subi.ecommerce.model.GioHang;

import java.text.NumberFormat;
import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {
    private ArrayList<GioHang> list = new ArrayList<>();
    GioHangAdapter gioHangAdapter;
    private GioHangDao dao;

    private GioHangDao gioHangDao;
    private RecyclerView recyclerView;
    public static Button pay;
    private ShowDialog showDialog;
    public static LinearLayout emty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        init();
        //Set tiêu đề
        setTitle("Danh Sách Sản Phẩm");

        gioHangDao = DBConnection.getInitialDatabase(this).createGioHangDao();

        list.addAll(gioHangDao.getAll());

        if (list.isEmpty()) {
            pay.setVisibility(View.GONE);
            emty.setVisibility(View.VISIBLE);
        }

        //Khi ấn thanh toán
        pay.setOnClickListener(v -> {
            Dialog dialog = new Dialog(GioHangActivity.this);
            dialog.setContentView(R.layout.payment);
            dialog.setCancelable(true);
            Window window = dialog.getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (dialog != null && dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            //Khai báo biến
            TextInputEditText name = dialog.findViewById(R.id.edt_name);
            TextInputEditText address = dialog.findViewById(R.id.edt_address);
            TextInputEditText sdt = dialog.findViewById(R.id.edt_sdt);
            Button add = dialog.findViewById(R.id.btnThem);
            Button cancle = dialog.findViewById(R.id.btnHuy);

            //Khi ấn thnah toán
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nameText = name.getText().toString();
                    String sdtText = sdt.getText().toString();  
                    String addressText = address.getText().toString();
                    if (nameText.isEmpty() || sdtText.isEmpty() || addressText.isEmpty()) {
                        showDialog.show("Không được để trống!");
                    } else if (sdtText.length() != 10) {
                        showDialog.show("Số điện thoại không chính xác");
                    } else {
                        NumberFormat format = NumberFormat.getCurrencyInstance();
                        showDialog.show("Đã đặt hàng thành công! Đơn Hàng sẽ sớm được chuyển đến tay bạn!");
                        gioHangDao.delete();
                        list.clear();
                        gioHangAdapter.notifyDataSetChanged();
                        pay.setVisibility(View.GONE);
                        emty.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    }
                }
            });

            //Khi ấn nút hủy
            cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        });
    }

    private void init() {
        dao = DBConnection.getInitialDatabase(this).createGioHangDao();

        recyclerView = findViewById(R.id.rv_list);
        pay = findViewById(R.id.btn_pay);
        emty = findViewById(R.id.ln_emty);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //Set list vào adapter
        gioHangAdapter = new GioHangAdapter(this, list);
        recyclerView.setAdapter(gioHangAdapter);
//        gioHangDao = new GioHangDao(this);
        showDialog = new ShowDialog(this);
    }
}