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

public class DongHoAdapter extends BaseAdapter {
    Context context;
    ArrayList<ThietBi> arrDongHo;

    public DongHoAdapter(Context context, ArrayList<ThietBi> arrDongHo) {
        this.context = context;
        this.arrDongHo = arrDongHo;
    }

    @Override
    public int getCount() {
        return arrDongHo.size();
    }

    @Override
    public Object getItem(int i) {
        return arrDongHo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class ViewHolder4{
        public TextView txtDongHo, txtGiaDongHo, txtMoTaDongHo, txtGiaGocDongHo;
        public ImageView imgDongHo;
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder4 viewHolder4;
        if(view == null){
            viewHolder4 = new ViewHolder4();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_dongho, null);
            viewHolder4.txtDongHo = view.findViewById(R.id.txtDongHo);
            viewHolder4.txtGiaDongHo = view.findViewById(R.id.txtGiaDongHo);
            viewHolder4.txtMoTaDongHo = view.findViewById(R.id.txtMoTaDongHo);
            viewHolder4.imgDongHo = view.findViewById(R.id.imgDongHo);
            viewHolder4.txtGiaGocDongHo = view.findViewById(R.id.txtGiaGocDongHo);
            view.setTag(viewHolder4);
        }
        else {
            viewHolder4 = (ViewHolder4) view.getTag();

        }
        ThietBi thietBi = (ThietBi) getItem(i);
        viewHolder4.txtDongHo.setText(thietBi.getTenThietBi());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder4.txtGiaGocDongHo.setText("₫ " +decimalFormat.format(thietBi.getGiaGoc()));
        viewHolder4.txtGiaGocDongHo.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder4.txtGiaDongHo.setText("₫ " + decimalFormat.format(thietBi.getGiaTri()));
        viewHolder4.txtMoTaDongHo.setMaxLines(2);
        viewHolder4.txtMoTaDongHo.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder4.txtMoTaDongHo.setText(thietBi.getMoTa());
        Picasso.with(context).load(thietBi.getHinhAnhThietBi()).placeholder(R.drawable.no_image_ic).error(R.drawable.error_ic).into(viewHolder4.imgDongHo);
        return view;
    }
}
