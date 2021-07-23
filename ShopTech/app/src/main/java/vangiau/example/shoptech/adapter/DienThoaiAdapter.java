package vangiau.example.shoptech.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vangiau.example.shoptech.R;
import vangiau.example.shoptech.model.ThietBi;

public class DienThoaiAdapter extends BaseAdapter {
    Context context;
    ArrayList<ThietBi> arrDienThoai;

    public DienThoaiAdapter(Context context, ArrayList<ThietBi> arrDienThoai) {
        this.context = context;
        this.arrDienThoai = arrDienThoai;
    }

    @Override
    public int getCount() {
        return arrDienThoai.size();
    }

    @Override
    public Object getItem(int i) {
        return arrDienThoai.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class ViewHolder{

        public TextView txtDienThoai, txtGiaDienThoai, txtMoTaDienThoai, txtGiaGocDienThoai;
        public ImageView imgDienThoai;
    }

    @SuppressLint({"SetTextI18n", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_dienthoai, null);
            viewHolder.txtDienThoai = view.findViewById(R.id.txtDienThoai);
            viewHolder.txtGiaDienThoai = view.findViewById(R.id.txtGiaDienThoai);
            viewHolder.txtMoTaDienThoai = view.findViewById(R.id.txtMoTaDienThoai);
            viewHolder.imgDienThoai = view.findViewById(R.id.imgDienThoai);
            viewHolder.txtGiaGocDienThoai = view.findViewById(R.id.txtGiaGocDienThoai);
            view.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ThietBi thietBi = (ThietBi) getItem(i);
        viewHolder.txtDienThoai.setText(thietBi.getTenThietBi());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaGocDienThoai.setText("₫ " + decimalFormat.format(thietBi.getGiaGoc()));
        viewHolder.txtGiaGocDienThoai.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.txtGiaDienThoai.setText("₫ " + decimalFormat.format(thietBi.getGiaTri()));
        viewHolder.txtMoTaDienThoai.setMaxLines(2);
        viewHolder.txtMoTaDienThoai.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMoTaDienThoai.setText(thietBi.getMoTa());

        Picasso.with(context).load(thietBi.getHinhAnhThietBi()).placeholder(R.drawable.no_image_ic).error(R.drawable.error_ic).into(viewHolder.imgDienThoai);
//        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_list);
//        view.startAnimation(animation);
        return view;
    }
}
