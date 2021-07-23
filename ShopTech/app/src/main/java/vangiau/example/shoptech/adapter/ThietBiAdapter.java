package vangiau.example.shoptech.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vangiau.example.shoptech.R;
import vangiau.example.shoptech.activity.ChiTietThietBiActivity;
import vangiau.example.shoptech.model.ThietBi;

public class ThietBiAdapter extends RecyclerView.Adapter<ThietBiAdapter.ItemHolder> {
    Context context;
    ArrayList<ThietBi> arrThietBi;

    public ThietBiAdapter(Context context, ArrayList<ThietBi> arrThietBi) {
        this.context = context;
        this.arrThietBi = arrThietBi;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams")
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_thietbimoinhat, null);
        return new ItemHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        ThietBi thietBi = arrThietBi.get(position);
        holder.txtTenThietBi.setMaxLines(2);
        holder.txtTenThietBi.setEllipsize(TextUtils.TruncateAt.END);
        holder.txtTenThietBi.setText(thietBi.getTenThietBi());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiaGoc.setText("₫ " +decimalFormat.format(thietBi.getGiaGoc()));
        holder.txtGiaGoc.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.txtGiaTri.setText("₫ " + decimalFormat.format(thietBi.getGiaTri()));
        Picasso.with(context).load(thietBi.getHinhAnhThietBi()).placeholder(R.drawable.no_image_ic).error(R.drawable.error_ic).into(holder.imgHinhThietBi);
    }

    @Override
    public int getItemCount() {
        return arrThietBi.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgHinhThietBi;
        public TextView txtTenThietBi, txtGiaTri, txtGiaGoc;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhThietBi = itemView.findViewById(R.id.imgThietBiMoiNhat);
            txtGiaGoc = itemView.findViewById(R.id.txtGiaGoc);
            txtGiaTri = itemView.findViewById(R.id.txtGiaTri);
            txtTenThietBi = itemView.findViewById(R.id.txtTenThietBiMoiNhat);
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, ChiTietThietBiActivity.class);
                intent.putExtra("thongtinthietbi", arrThietBi.get(getPosition()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            });
        }
    }
}
