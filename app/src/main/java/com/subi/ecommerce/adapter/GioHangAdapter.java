package com.subi.ecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.subi.ecommerce.GioHangActivity;
import com.subi.ecommerce.R;
import com.subi.ecommerce.database.DBConnection;
import com.subi.ecommerce.dao.GioHangDao;
import com.subi.ecommerce.model.GioHang;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    private Context context;
    private List<GioHang> list;
    NumberFormat format = NumberFormat.getCurrencyInstance();

    public GioHangAdapter() {
    }

    public GioHangAdapter(Context context, ArrayList<GioHang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Set layout cho adapter để hiển thị lên list
        View view = LayoutInflater.from(context).inflate(R.layout.one_giohang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Trả về 1 item tại vị trí position(vị trí hiện tại theo list)
        GioHang gh = list.get(position);
        //Set tiêu đề
        holder.name.setText(gh.getTenSanPham());
        holder.price.setText(format.format(Integer.parseInt(gh.getGiaSanPham().trim())));
        holder.imageView.setImageResource(gh.getImage());
        holder.sl.setText("Số lượng: " + gh.getSoLuong());
        holder.del.setOnClickListener(view -> {
            Toast.makeText(context, "xoa", Toast.LENGTH_SHORT).show();
            GioHangDao gioHangDao = DBConnection.getInitialDatabase(context).createGioHangDao();
            gioHangDao.xoa(gh);
            list.clear();
            list.addAll(gioHangDao.getAll());
            notifyDataSetChanged();
            if (list.isEmpty()) {
                GioHangActivity.pay.setVisibility(View.GONE);
                GioHangActivity.emty.setVisibility(View.VISIBLE);
            }
            Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, del;
        TextView name, price, sl;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Khai báo id theo itemView
            imageView = itemView.findViewById(R.id.ivNewsList);
            name = itemView.findViewById(R.id.one_name);
            price = itemView.findViewById(R.id.one_price);
            sl = itemView.findViewById(R.id.one_sl);
            del = itemView.findViewById(R.id.iv_del);
            view = itemView;
        }
    }

}
