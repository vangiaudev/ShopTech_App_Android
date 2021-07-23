package vangiau.example.shoptech.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

import vangiau.example.shoptech.R;
import vangiau.example.shoptech.model.HinhAnhChiTiet;

public class HinhAnhChiTietAdapter extends PagerAdapter {
    private final Context context;
    private final List<HinhAnhChiTiet> hinhAnhChiTietList;

    public HinhAnhChiTietAdapter(Context context, List<HinhAnhChiTiet> hinhAnhChiTietList) {
        this.context = context;
        this.hinhAnhChiTietList = hinhAnhChiTietList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo_slider,container ,false);
        ImageView imgPhoto = view.findViewById(R.id.imgDemo);
        HinhAnhChiTiet hinhAnhChiTiet = hinhAnhChiTietList.get(position);
        if(hinhAnhChiTiet != null){
            Glide.with(context).load(hinhAnhChiTiet.getHinhAnhChiTiet()).into(imgPhoto);
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(hinhAnhChiTietList != null){
            return hinhAnhChiTietList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
