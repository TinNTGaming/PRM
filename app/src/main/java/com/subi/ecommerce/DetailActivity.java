package com.subi.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.subi.ecommerce.adapter.ShowDialog;
import com.subi.ecommerce.dao.GioHangDao;
import com.subi.ecommerce.database.DBConnection;
import com.subi.ecommerce.model.GioHang;
import com.subi.ecommerce.model.Sanpham;

public class DetailActivity extends AppCompatActivity {
    private Sanpham sanpham;
    private ImageView imageView;
    private TextView name, price, mota;
    private Button add;
    private GioHangDao gioHangDao;
    private ShowDialog showDialog;
    private GioHang gioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        sanpham = (Sanpham) getIntent().getSerializableExtra("sp");
        //Khai báo
        init();

        //Set tiêu đề
        setTitle("CHI TIẾT SẢN PHẨM");
        //set Data
        try {
            name.setText(sanpham.getTenSanPham());
            price.setText(sanpham.getGiaSanPham());
            mota.setText(sanpham.getMoTa());
            imageView.setImageResource(sanpham.getImage());
        } catch (Exception e) {

        }

        gioHangDao = DBConnection.getInitialDatabase(this).createGioHangDao();

        add.setOnClickListener(view -> {
            if(sanpham != null){
                gioHang = gioHangDao.getGioHang(sanpham.getId());

                if (gioHang != null) {
                    gioHang.setSoLuong(gioHang.getSoLuong() + 1);
                    // Nếu sản phẩm đã tồn tại trong giỏ hàng
                    gioHangDao.update(gioHang);
                    showDialog.show("Số lượng đã được cập nhật trong giỏ hàng!");
                } else {
                    // Nếu sản phẩm chưa tồn tại trong giỏ hàng
                    gioHang = new GioHang(sanpham.getId(), sanpham.getTenSanPham(), sanpham.getMoTa(), sanpham.getGiaSanPham(), sanpham.getLoaiSanPham(), sanpham.getImage(), 1);
                    gioHangDao.them(gioHang); // Không kiểm tra kết quả ở đây
                    showDialog.show("Thêm vào giỏ hàng thành công!");
                }
            }

        });

    }

    private void init() {
        imageView = findViewById(R.id.ivNewsList);
        name = findViewById(R.id.one_name);
        price = findViewById(R.id.one_price);
        mota = findViewById(R.id.tv_mota);
        add = findViewById(R.id.btn_add);
        showDialog = new ShowDialog(this);
    }

    //icon giỏ hàng
//Set menu cho action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_giohang, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.giohang:
                startActivity(new Intent(this, GioHangActivity.class));
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}