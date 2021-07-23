package vangiau.example.shoptech.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vangiau.example.shoptech.R;
import vangiau.example.shoptech.activity.GioHangActivity;
import vangiau.example.shoptech.activity.TrangChuActivity;
import vangiau.example.shoptech.model.GioHang;

public class GioHangAdapter extends BaseAdapter {

    Context context;
    ArrayList<GioHang> arrGioHang;

    public GioHangAdapter(Context context, ArrayList<GioHang> arrGioHang) {
        this.context = context;
        this.arrGioHang = arrGioHang;
    }

    @Override
    public int getCount() {
        return arrGioHang.size();
    }

    @Override
    public Object getItem(int i) {
        return arrGioHang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private static class ViewHolder3{
        private TextView txtTenGioHang, txtGiaGioHang;
        private ImageView imgGioHang;
        private Button btnMin, btnMax, btnValue;
    }

    @SuppressLint({"SetTextI18n", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder3 viewHolder3;
        if(view == null){
            viewHolder3 = new ViewHolder3();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_giohang, null);
            viewHolder3.txtTenGioHang = view.findViewById(R.id.txtTenGioHang);
            viewHolder3.txtGiaGioHang = view.findViewById(R.id.txtGiaGioHang);
            viewHolder3.btnMin = view.findViewById(R.id.btnMin);
            viewHolder3.btnMax = view.findViewById(R.id.btnMax);
            viewHolder3.imgGioHang = view.findViewById(R.id.imgGioHang);
            viewHolder3.btnValue = view.findViewById(R.id.btnValue);
            view.setTag(viewHolder3);
        }
        else {
            viewHolder3 = (ViewHolder3) view.getTag();
        }
        GioHang gioHang = (GioHang) getItem(i);
        viewHolder3.txtTenGioHang.setText(gioHang.getTen());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder3.txtGiaGioHang.setText("₫ " +decimalFormat.format(gioHang.getGia()));
        Picasso.with(context).load(gioHang.getHinh()).placeholder(R.drawable.no_image_ic).error(R.drawable.error_ic).into(viewHolder3.imgGioHang);
        viewHolder3.btnValue.setText(gioHang.getSoLuong() + "");
        int count = Integer.parseInt(viewHolder3.btnValue.getText().toString());
        if(count >= 10){
            viewHolder3.btnMax.setEnabled(false);
            viewHolder3.btnMin.setEnabled(true);
        }else if(count <= 1){
            viewHolder3.btnMin.setEnabled(false);
        }else {
            viewHolder3.btnMin.setEnabled(true);
            viewHolder3.btnMax.setEnabled(true);
        }
        ViewHolder3 finalViewHolder = viewHolder3;
        viewHolder3.btnMax.setOnClickListener(view1 -> {
            int soLuongMoiNhat = Integer.parseInt(finalViewHolder.btnValue.getText().toString()) + 1;
            int soLuongHienTai = TrangChuActivity.arrGioHang.get(i).getSoLuong();
            long giaHienTai = TrangChuActivity.arrGioHang.get(i).getGia();
            TrangChuActivity.arrGioHang.get(i).setSoLuong(soLuongMoiNhat);
            long giaMoi = (giaHienTai * soLuongMoiNhat) / soLuongHienTai;
            TrangChuActivity.arrGioHang.get(i).setGia(giaMoi);

            DecimalFormat decimalFormat1 = new DecimalFormat("###,###,###");
            finalViewHolder.txtGiaGioHang.setText("₫ " + decimalFormat1.format(giaMoi));
            GioHangActivity.EventUtil();

            if(soLuongMoiNhat > 9){
                finalViewHolder.btnMax.setEnabled(false);
                finalViewHolder.btnMin.setEnabled(true);
                finalViewHolder.btnValue.setText(String.valueOf(soLuongMoiNhat));
            }
            else {
                finalViewHolder.btnMin.setEnabled(true);
                finalViewHolder.btnMax.setEnabled(true);
                finalViewHolder.btnValue.setText(String.valueOf(soLuongMoiNhat));
            }
        });
        ViewHolder3 finalViewHolder1 = viewHolder3;
        viewHolder3.btnMin.setOnClickListener(view12 -> {
            int soLuongMoiNhat = Integer.parseInt(finalViewHolder1.btnValue.getText().toString()) - 1;
            int soLuongHienTai = TrangChuActivity.arrGioHang.get(i).getSoLuong();
            long giaHienTai = TrangChuActivity.arrGioHang.get(i).getGia();
            TrangChuActivity.arrGioHang.get(i).setSoLuong(soLuongMoiNhat);
            long giaMoi = (giaHienTai * soLuongMoiNhat) / soLuongHienTai;
            TrangChuActivity.arrGioHang.get(i).setGia(giaMoi);

            DecimalFormat decimalFormat12 = new DecimalFormat("###,###,###");
            finalViewHolder1.txtGiaGioHang.setText("₫ " + decimalFormat12.format(giaMoi));
            GioHangActivity.EventUtil();

            if(soLuongMoiNhat < 2){
                finalViewHolder1.btnMin.setEnabled(false);
                finalViewHolder1.btnMax.setEnabled(true);
                finalViewHolder1.btnValue.setText(String.valueOf(soLuongMoiNhat));
            }
            else {
                finalViewHolder1.btnMin.setEnabled(true);
                finalViewHolder1.btnMax.setEnabled(true);
                finalViewHolder1.btnValue.setText(String.valueOf(soLuongMoiNhat));
            }
        });
        return view;
    }
}
