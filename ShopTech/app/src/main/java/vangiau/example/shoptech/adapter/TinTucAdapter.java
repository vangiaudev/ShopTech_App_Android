package vangiau.example.shoptech.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vangiau.example.shoptech.R;
import vangiau.example.shoptech.model.TinTuc;

public class TinTucAdapter extends ArrayAdapter<TinTuc> {

    public TinTucAdapter(Context context, int resource, List<TinTuc> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.dong_tintuc, null);
        }
        TinTuc p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txtTuaDe = (TextView) view.findViewById(R.id.txtTuaDe);
            TextView txtPubDate = view.findViewById(R.id.txtPubDate);
            txtTuaDe.setText(p.title);

            txtPubDate.setText("Ngày đăng: " + ((p.pubDate).substring(5, 25)));
            ImageView imageView = view.findViewById(R.id.imgHinh);
            Picasso.with(getContext()).load(p.image).into(imageView);

        }
        return view;
    }

}
