package vangiau.example.shoptech.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vangiau.example.shoptech.R;
import vangiau.example.shoptech.model.ThietBi;

public class TabletAdapter extends BaseAdapter {
    Context context;
    ArrayList<ThietBi> arrTablet;

    public TabletAdapter(Context context, ArrayList<ThietBi> arrTablet) {
        this.context = context;
        this.arrTablet = arrTablet;
    }

    @Override
    public int getCount() {
        return arrTablet.size();
    }

    @Override
    public Object getItem(int i) {
        return arrTablet.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class ViewHolder3{
        public TextView txtTablet, txtGiaTablet, txtMoTaTablet, txtGiaGocTablet;
        public ImageView imgTablet;
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder3 viewHolder3;
        if(view == null){
            viewHolder3 = new ViewHolder3();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_tablet, null);
            viewHolder3.txtTablet = view.findViewById(R.id.txtTablet);
            viewHolder3.txtGiaTablet = view.findViewById(R.id.txtGiaTablet);
            viewHolder3.txtMoTaTablet = view.findViewById(R.id.txtMoTaTablet);
            viewHolder3.imgTablet = view.findViewById(R.id.imgTablet);
            viewHolder3.txtGiaGocTablet = view.findViewById(R.id.txtGiaGocTablet);
            view.setTag(viewHolder3);
        }
        else {
            viewHolder3 = (ViewHolder3) view.getTag();

        }
        ThietBi thietBi = (ThietBi) getItem(i);
        viewHolder3.txtTablet.setText(thietBi.getTenThietBi());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder3.txtGiaGocTablet.setText(decimalFormat.format(thietBi.getGiaGoc()));
        viewHolder3.txtGiaGocTablet.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder3.txtGiaTablet.setText("₫ " + decimalFormat.format(thietBi.getGiaTri()));
        viewHolder3.txtMoTaTablet.setMaxLines(2);
        viewHolder3.txtMoTaTablet.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder3.txtMoTaTablet.setText(thietBi.getMoTa());
        Picasso.with(context).load(thietBi.getHinhAnhThietBi()).placeholder(R.drawable.no_image_ic).error(R.drawable.error_ic).into(viewHolder3.imgTablet);
        return view;
    }
}
