package vangiau.example.shoptech.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import vangiau.example.shoptech.R;
import vangiau.example.shoptech.activity.NguoiDungActivity;
import vangiau.example.shoptech.model.DanhGia;

public class DanhGiaAdapter extends BaseAdapter {
    Context context;
    ArrayList<DanhGia> arrDanhGia;

    public DanhGiaAdapter(Context context, ArrayList<DanhGia> arrDanhGia) {
        this.context = context;
        this.arrDanhGia = arrDanhGia;
    }

    @Override
    public int getCount() {
        return arrDanhGia.size();
    }

    @Override
    public Object getItem(int i) {
        return arrDanhGia.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public static class ViewHolder{
        CircleImageView civAvatarDG;
        TextView txtHoTenDG, txtNoiDungDG, txtNgayDG;
        RatingBar rbKetQuaDG;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_danhgia, null);
            viewHolder.civAvatarDG = view.findViewById(R.id.civAvatarDG);
            viewHolder.txtHoTenDG  = view.findViewById(R.id.txtHoTenDG);
            viewHolder.txtNoiDungDG= view.findViewById(R.id.txtNoiDungDG);
            viewHolder.txtNgayDG   = view.findViewById(R.id.txtNgayDG);
            viewHolder.rbKetQuaDG  = view.findViewById(R.id.rbKetQuaDG);
            view.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        DanhGia danhGia = (DanhGia) getItem(i);
        viewHolder.txtNgayDG.setText(danhGia.getNgaydanhgia());
        viewHolder.txtHoTenDG.setText(danhGia.getHoten());
        viewHolder.txtNoiDungDG.setText(danhGia.getNoidung());
        viewHolder.rbKetQuaDG.setRating((float) danhGia.getRating());
        Picasso.with(context).load(danhGia.getAvatar()).placeholder(R.drawable.ic_avatar).error(R.drawable.ic_avatar).into(viewHolder.civAvatarDG);
        return view;
    }
}
