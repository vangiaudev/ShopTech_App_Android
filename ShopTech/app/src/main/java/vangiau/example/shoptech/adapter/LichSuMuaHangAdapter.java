package vangiau.example.shoptech.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vangiau.example.shoptech.R;
import vangiau.example.shoptech.model.ChiTietDonHang;

public class LichSuMuaHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<ChiTietDonHang> arrLichSuMuaHang;

    public LichSuMuaHangAdapter(Context context, ArrayList<ChiTietDonHang> arrLichSuMuaHang) {
        this.context = context;
        this.arrLichSuMuaHang = arrLichSuMuaHang;
    }

    @Override
    public int getCount() {
        return arrLichSuMuaHang.size();
    }

    @Override
    public Object getItem(int i) {
        return arrLichSuMuaHang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class ViewHolder{
        public TextView txtNgayDatLS, txtGiaLS, txtTenLS, txtSoLuongLS, txtMaVanDonLS;
    }

    @SuppressLint({"SetTextI18n", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_lichsudonhang, null);
            viewHolder.txtNgayDatLS = view.findViewById(R.id.txtNgayDatLS);
            viewHolder.txtGiaLS = view.findViewById(R.id.txtGiaLS);
            viewHolder.txtSoLuongLS = view.findViewById(R.id.txtSoLuongLS);
            viewHolder.txtTenLS = view.findViewById(R.id.txtTenLS);
            viewHolder.txtMaVanDonLS = view.findViewById(R.id.txtMaVanDonLS);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        ChiTietDonHang chiTietDonHang = (ChiTietDonHang) getItem(i);
        viewHolder.txtNgayDatLS.setText("Ngày: " + chiTietDonHang.getNgayDat());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaLS.setText(decimalFormat.format(chiTietDonHang.getGiaThietBi()) + " ₫");
        viewHolder.txtTenLS.setMaxLines(2);
        viewHolder.txtTenLS.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtTenLS.setText(chiTietDonHang.getTenThietBi());
        viewHolder.txtSoLuongLS.setText("Số lượng x " + chiTietDonHang.getSoLuongThietBi());
        viewHolder.txtMaVanDonLS.setText("Mã vận đơn: HD000" + chiTietDonHang.getMaDonHang());

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_list);
        view.startAnimation(animation);
        return view;
    }
}
