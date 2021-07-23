package vangiau.example.shoptech.general;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import vangiau.example.shoptech.R;

public class ProgressButton {
    private final CardView cardView;
    private final CardView cardView2;
    private final CardView cardView3;
    private final ConstraintLayout constraintLayout;
    private final ConstraintLayout constraintLayout2;
    private final ConstraintLayout constraintLayout3;
    private final ProgressBar progressBar;
    private final ProgressBar progressBar2;
    private final ProgressBar progressBar3;
    private final TextView textView;
    private final TextView textView2;
    private final TextView textView3;

    public ProgressButton(Context context, View view){
        cardView = view.findViewById(R.id.cvProgress);
        constraintLayout = view.findViewById(R.id.clProgress);
        progressBar = view.findViewById(R.id.pbDangNhap);
        textView = view.findViewById(R.id.textView);

        cardView2 = view.findViewById(R.id.cvProgress2);
        constraintLayout2 = view.findViewById(R.id.clProgress2);
        progressBar2 = view.findViewById(R.id.pbDangKy);
        textView2 = view.findViewById(R.id.textView2);

        cardView3 = view.findViewById(R.id.cvProgress3);
        constraintLayout3 = view.findViewById(R.id.clProgress3);
        progressBar3 = view.findViewById(R.id.pbDatHang);
        textView3 = view.findViewById(R.id.textView3);

    }
    @SuppressLint("SetTextI18n")
    public void buttonActivatedLogin(){
        progressBar.setVisibility(View.VISIBLE);
        textView.setText("ĐANG KIỂM TRA...");

    }

    @SuppressLint("SetTextI18n")
    public void buttonFinishedLogin(){
        progressBar.setVisibility(View.GONE);
        constraintLayout.setBackgroundColor(ContextCompat.getColor(cardView.getContext(), R.color.colorGreenDrank));
        textView.setText("HOÀN TẤT");
    }

    @SuppressLint("SetTextI18n")
    public void buttonActivatedSignin(){
        progressBar2.setVisibility(View.VISIBLE);
        textView2.setText("VUI LÒNG CHỜ...");

    }

    @SuppressLint("SetTextI18n")
    public void buttonFinishedSignin(){
        progressBar2.setVisibility(View.GONE);
        constraintLayout2.setBackgroundColor(ContextCompat.getColor(cardView2.getContext(), R.color.colorGreenDrank));
        textView2.setText("ĐĂNG KÝ THÀNH CÔNG");
    }

    @SuppressLint("SetTextI18n")
    public void buttonActivatedDatHang(){
        progressBar3.setVisibility(View.VISIBLE);
        textView3.setText("ĐANG TIẾN HÀNH...");

    }

    @SuppressLint("SetTextI18n")
    public void buttonFinishedDatHang(){
        progressBar3.setVisibility(View.GONE);
        constraintLayout3.setBackgroundColor(ContextCompat.getColor(cardView3.getContext(), R.color.colorGreenDrank));
        textView3.setText("HOÀN TẤT");
    }
}
