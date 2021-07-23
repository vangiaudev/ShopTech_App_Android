package vangiau.example.shoptech.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vangiau.example.shoptech.R;
import vangiau.example.shoptech.model.LoaiThietBi;

public class LoaiThietBiAdapter extends BaseAdapter {

    ArrayList<LoaiThietBi> loaiThietBiArrayList;
    Context context;

    public LoaiThietBiAdapter(ArrayList<LoaiThietBi> loaiThietBiArrayList, Context context) {
        this.loaiThietBiArrayList = loaiThietBiArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return loaiThietBiArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return loaiThietBiArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class ViewHolder{
        TextView txtLoaiThietBi;
        ImageView imgLoaiThietBi;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_thietbi, null);
            viewHolder.txtLoaiThietBi = view.findViewById(R.id.txtLoaiThietBi);
            viewHolder.imgLoaiThietBi = view.findViewById(R.id.imgLoaiThietBi);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        LoaiThietBi loaiThietBi = (LoaiThietBi) getItem(i);
        viewHolder.txtLoaiThietBi.setText(loaiThietBi.getTenThietBi());
        Picasso.with(context).load(loaiThietBi.getHinhAnhThietBi()).placeholder(R.drawable.no_image_ic).error(R.drawable.error_ic).into(viewHolder.imgLoaiThietBi);

        return view;
    }
}
