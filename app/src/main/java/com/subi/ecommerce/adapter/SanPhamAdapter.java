package com.subi.ecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.subi.ecommerce.DetailActivity;
import com.subi.ecommerce.R;
import com.subi.ecommerce.model.Sanpham;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {
    private Context context;
    private List<Sanpham> list;
    List<Sanpham> listSort;
    Filter filter;
    NumberFormat format = NumberFormat.getCurrencyInstance();

    public SanPhamAdapter() {
    }

    public SanPhamAdapter(Context context, List<Sanpham> list) {
        this.context = context;
        this.list = list;
        this.listSort = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Set layout cho adapter để hiển thị lên list
        View view = LayoutInflater.from(context).inflate(R.layout.one_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Trả về 1 item tại vị trí position(vị trí hiện tại theo list)
        Sanpham sp = list.get(position);
        //Set tiêu đề
        holder.name.setText(sp.getTenSanPham());
        holder.price.setText(format.format(Integer.parseInt(sp.getGiaSanPham().trim())));
        holder.imageView.setImageResource(sp.getImage());
        //Bắt sự kiện khi click vào một item
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                //Truyền toàn bộ data sang
                intent.putExtra("sp", sp);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, price;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Khai báo id theo itemView
            imageView = itemView.findViewById(R.id.ivNewsList);
            name = itemView.findViewById(R.id.one_name);
            price = itemView.findViewById(R.id.one_price);
            view = itemView;
        }
    }

    public void resetData() {
        list = listSort;
    }

    public Filter getFilter() {
        if (filter == null)
            filter = new CustomFilter();
        return filter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = listSort;
                results.count = listSort.size();
            } else {
                ArrayList<Sanpham> findItem = new ArrayList<>();
                for (Sanpham p : list) {
                    if (p.getTenSanPham().toUpperCase().contains(constraint.toString().toUpperCase()))
                        findItem.add(p);
                }
                results.values = findItem;
                results.count = findItem.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0) {
//                notifyDataSetInvalidated();
            } else {
                list = (ArrayList<Sanpham>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
