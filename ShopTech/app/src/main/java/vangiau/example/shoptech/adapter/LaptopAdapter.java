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

public class LaptopAdapter extends BaseAdapter {
    Context context;
    ArrayList<ThietBi> arrLaptop;

    public LaptopAdapter(Context context, ArrayList<ThietBi> arrLaptop) {
        this.context = context;
        this.arrLaptop = arrLaptop;
    }

    @Override
    public int getCount() {
        return arrLaptop.size();
    }

    @Override
    public Object getItem(int i) {
        return arrLaptop.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class ViewHolder2{
        public TextView txtLaptop, txtGiaLaptop, txtMoTaLaptop,txtGiaGocLaptop;
        public ImageView imgLaptop;
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder2 viewHolder2;
        if(view == null){
            viewHolder2 = new ViewHolder2();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_macbook, null);
            viewHolder2.txtLaptop = view.findViewById(R.id.txtLaptop);
            viewHolder2.txtGiaLaptop = view.findViewById(R.id.txtGiaLaptop);
            viewHolder2.txtMoTaLaptop = view.findViewById(R.id.txtMoTaLaptop);
            viewHolder2.imgLaptop = view.findViewById(R.id.imgLaptop);
            viewHolder2.txtGiaGocLaptop = view.findViewById(R.id.txtGiaGocLaptop);
            view.setTag(viewHolder2);
        }
        else {
            viewHolder2 = (ViewHolder2) view.getTag();

        }
        ThietBi thietBi = (ThietBi) getItem(i);
        viewHolder2.txtLaptop.setText(thietBi.getTenThietBi());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder2.txtGiaGocLaptop.setText("₫ " +decimalFormat.format(thietBi.getGiaGoc()));
        viewHolder2.txtGiaGocLaptop.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder2.txtGiaLaptop.setText("₫ " + decimalFormat.format(thietBi.getGiaTri()));
        viewHolder2.txtMoTaLaptop.setMaxLines(2);
        viewHolder2.txtMoTaLaptop.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder2.txtMoTaLaptop.setText(thietBi.getMoTa());
        Picasso.with(context).load(thietBi.getHinhAnhThietBi()).placeholder(R.drawable.no_image_ic).error(R.drawable.error_ic).into(viewHolder2.imgLaptop);
        return view;
    }
}
