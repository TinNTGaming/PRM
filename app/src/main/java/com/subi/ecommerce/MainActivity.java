package com.subi.ecommerce;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.subi.ecommerce.adapter.SanPhamAdapter;
import com.subi.ecommerce.dao.GioHangDao;
import com.subi.ecommerce.dao.ProductDAO;
import com.subi.ecommerce.database.DBConnection;
import com.subi.ecommerce.model.Sanpham;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Sanpham> list  ;
//private ArrayList<Sanpham> list = new Sanpham().getAll();

    boolean hasProductInCart = false;

    private ProductDAO dao;
    private GioHangDao dao1;
    SanPhamAdapter sanPhamAdapter;
    private RecyclerView recyclerView;
    private TextInputEditText find;

    public static final String CHANNEL_ID = "push_notification_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        init();
        //Set tiêu đề
        setTitle("TRANG CHỦ");
        //Set tìm kiếm
        setFindNews();
        checkCartProducts();
    }

    private void setFindNews() {
        recyclerView.setFilterTouchesWhenObscured(true);
        find.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int
                    count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    sanPhamAdapter.resetData();
                }
                sanPhamAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void init() {
        dao = DBConnection.getInitialDatabase(this).createdataDAO();
//        list = dao.loadAllData();
        list = getAll();
    for (Sanpham sp : list){
        dao.insert(sp);
    }
        find = findViewById(R.id.tvFind);
        recyclerView = findViewById(R.id.rcvListNews);
        //Cài đặt layout cho list, set cố cột là 2 cột
        LinearLayoutManager layoutManager
                = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        //Set list vào adapter
        sanPhamAdapter = new SanPhamAdapter(this, list);
        recyclerView.setAdapter(sanPhamAdapter);


    }
    private void checkCartProducts() {
        // Tạo đối tượng quản lý giỏ hàng (ví dụ: GioHangDao)
        dao1 = DBConnection.getInitialDatabase(this).createGioHangDao();


        // Kiểm tra giỏ hàng có sản phẩm hay không
        if (dao1.hasProductsInCart()) {
            // Nếu có sản phẩm trong giỏ hàng, hiển thị thông báo
            createChannelNotification();
            sendNotification("BE2Nursing", "Chào Mừng Quay Trở Lại ");

        }
    }



    private void createChannelNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "PushNotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    public void sendNotification(String strTitle, String message) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(strTitle)
                .setContentText(message)
                .setSmallIcon(R.drawable.be2_logo)
                .setContentIntent(pendingIntent);
        Notification notification = noBuilder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager != null){
            notificationManager.notify(1, notification);
        }
    }






    @Override
    public void onBackPressed() {

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
            case R.id.location:
                startActivity(new Intent(this, Location.class));
                return true;
            case R.id.logout:
                startActivity(new Intent(this, SignInScreen.class));
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    public List<Sanpham> getAll() {
        List<Sanpham> list = new ArrayList<>();
        list.add(new Sanpham(1, "Bia", "Bia được sản xuất theo quy trình hiện đại với công thức truyền thống lâu đời, mang đến cho người tiêu dùng trải nghiệm thích thú khi thưởng thức.", "22", "FullTime", R.drawable.bia));
        list.add(new Sanpham(2, "Dầu ăn", "Dầu ăn chiết xuất từ thực vật, tốt cho sức khỏe và mang lại hương vị tuyệt vời cho món ăn.", "24", "FullTime", R.drawable.dauan));
        list.add(new Sanpham(3, "Bột ngọt", "Bột ngọt tinh khiết, gia vị không thể thiếu trong bữa ăn hàng ngày, giúp tăng hương vị cho món ăn.", "40", "FullTime", R.drawable.botngot));
        list.add(new Sanpham(4, "Mì gói", "Mì gói nhanh chóng, tiện lợi, phù hợp cho những bữa ăn nhanh và đơn giản.", "10", "FullTime", R.drawable.migoi));
        list.add(new Sanpham(5, "Hạt nêm", "Hạt nêm từ xương hầm và thịt, gia vị hoàn hảo cho các món canh và súp.", "30", "FullTime", R.drawable.hatnem));
        list.add(new Sanpham(6, "Giày vệ sinh", "Giày vệ sinh tiện dụng, dễ lau chùi và bảo quản.", "25", "FullTime", R.drawable.giayvesinh));
        list.add(new Sanpham(7, "Hủ tiếu", "Hủ tiếu khô, dễ chế biến, mang lại hương vị đặc trưng của ẩm thực Việt Nam.", "80", "FullTime", R.drawable.hutieu));
        list.add(new Sanpham(8, "Kem đánh răng", "Kem đánh răng trắng sáng, bảo vệ men răng và chống sâu răng hiệu quả.", "35", "FullTime", R.drawable.kemdanhrang));
        list.add(new Sanpham(9, "Khăn giấy", "Khăn giấy mềm mại, tiện dụng cho mọi nhu cầu sử dụng hàng ngày.", "8", "FullTime", R.drawable.khangiay));
        list.add(new Sanpham(10, "Muối", "Muối tinh khiết, sử dụng hàng ngày, giúp tăng hương vị cho các món ăn.", "18", "FullTime", R.drawable.muoi));

        return list;
    }


}