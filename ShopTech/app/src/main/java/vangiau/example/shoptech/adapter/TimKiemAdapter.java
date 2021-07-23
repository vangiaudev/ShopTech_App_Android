package vangiau.example.shoptech.adapter;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.text.DecimalFormat;

import vangiau.example.shoptech.R;
import vangiau.example.shoptech.activity.ChiTietTimKiemFragment;
import vangiau.example.shoptech.model.DanhSachSanPham;

public class TimKiemAdapter extends FirebaseRecyclerAdapter<DanhSachSanPham, TimKiemAdapter.MyViewHolder> {

    public TimKiemAdapter(@NonNull FirebaseRecyclerOptions<DanhSachSanPham> options) {
        super(options);
    }

    public String ToUpper(String message) {
        char[] charArray = message.toCharArray();
        boolean foundSpace = true;
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isLetter(charArray[i])) {
                if (foundSpace) {
                    charArray[i] = Character.toUpperCase(charArray[i]);
                    foundSpace = false;
                }
            } else {
                foundSpace = true;
            }
        }
        message = String.valueOf(charArray);
        return message;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull DanhSachSanPham model) {

        holder.txtTenTK.setText(ToUpper(model.getTenthietbi()));
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiaGocTK.setText("₫ " + decimalFormat.format(Integer.parseInt(model.getGiagoc())));
        holder.txtGiaGocTK.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.txtGiaTK.setText("₫ " + decimalFormat.format(Integer.parseInt(model.getGiatri())));
        holder.txtMoTaTK.setMaxLines(2);
        holder.txtMoTaTK.setEllipsize(TextUtils.TruncateAt.END);
        holder.txtMoTaTK.setText(model.getMota());
        Glide.with(holder.imgTK.getContext()).load(model.getHinhanhthietbi()).into(holder.imgTK);
        holder.itemView.setOnClickListener(view -> {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new ChiTietTimKiemFragment(model.getTenthietbi(), model.getGiagoc(),model.getGiatri(), model.getMota(), model.getHinhanhthietbi(), model.getId(), model.getHinhanhchitiet2(), model.getHinhanhchitiet3())).addToBackStack(null).commit();
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ketqua_timkiem, parent, false);
        return new MyViewHolder(view);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imgTK;
        TextView txtTenTK, txtGiaTK, txtMoTaTK, txtGiaGocTK;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTK = itemView.findViewById(R.id.imgTimKiemTK);
            txtTenTK = itemView.findViewById(R.id.txtTimKiemTK);
            txtGiaTK = itemView.findViewById(R.id.txtGiaTimKiemTK);
            txtMoTaTK = itemView.findViewById(R.id.txtMoTaTimKiemTK);
            txtGiaGocTK = itemView.findViewById(R.id.txtGiaGocTimKiemTK);

        }
    }
}
