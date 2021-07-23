package vangiau.example.shoptech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Objects;

import vangiau.example.shoptech.R;
import vangiau.example.shoptech.adapter.GioHangAdapter;
import vangiau.example.shoptech.general.CheckConnect;

public class GioHangActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    public static TextView txtTongTien;
    ListView        lvGioHang;
    TextView        txtThongBao;
    ImageView       imgGioHangTrong;
    Button          btnThanhToan, btnTiepTuc;
    Toolbar         tbGioHang;
    GioHangAdapter  gioHangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        AddControls();
        ActionToolbar();
        CheckData();
        EventUtil();
        CatchOnItemListView();
        EventButton();

    }

    private void EventButton() {
        btnTiepTuc.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), TrangChuActivity.class);
            startActivity(intent);
        });
        btnThanhToan.setOnClickListener(view -> {
            if(TrangChuActivity.arrGioHang.size() > 0){
                Intent intent = new Intent(getApplicationContext(), ThanhToanActivity.class);
                startActivity(intent);
            }else {
                CheckConnect.ToastMessError(getApplicationContext(), "Giỏ hàng của bạn chưa có sản phẩm để thanh toán");
            }
        });
    }

    private void CatchOnItemListView() {
        lvGioHang.setOnItemLongClickListener((adapterView, view, pos, l) -> {
            final Dialog dialog = new Dialog(GioHangActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.layout_dialog_delete);

            Window window = dialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            AppCompatButton btnNo = (AppCompatButton) dialog.findViewById(R.id.btnNo);
            AppCompatButton btnYes = dialog.findViewById(R.id.btnYes);

            btnNo.setOnClickListener(v -> dialog.dismiss());

            btnYes.setOnClickListener(view1 -> {
                if(TrangChuActivity.arrGioHang.size() <= 0){
                    txtThongBao.setVisibility(View.VISIBLE);
                    imgGioHangTrong.setVisibility(View.VISIBLE);
                }
                else {
                    TrangChuActivity.arrGioHang.remove(pos);
                    gioHangAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                    EventUtil();
                    if(TrangChuActivity.arrGioHang.size() <= 0){
                        txtThongBao.setVisibility(View.VISIBLE);
                        imgGioHangTrong.setVisibility(View.VISIBLE);
                    }
                    else {
                        txtThongBao.setVisibility(View.INVISIBLE);
                        imgGioHangTrong.setVisibility(View.INVISIBLE);
                        gioHangAdapter.notifyDataSetChanged();
                        EventUtil();
                    }
                }
            });

            dialog.show();
            return true;
        });
    }

    @SuppressLint("SetTextI18n")
    public static void EventUtil() {
        long tongTien = 0;
        for (int i = 0; i < TrangChuActivity.arrGioHang.size(); i++){
            tongTien += TrangChuActivity.arrGioHang.get(i).getGia();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTongTien.setText(decimalFormat.format(tongTien) + " ₫");
    }

    private void CheckData() {
        if(TrangChuActivity.arrGioHang.size() <= 0){
            gioHangAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.VISIBLE);
            imgGioHangTrong.setVisibility(View.VISIBLE);
            lvGioHang.setVisibility(View.INVISIBLE);
        }
        else {
            gioHangAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.INVISIBLE);
            imgGioHangTrong.setVisibility(View.INVISIBLE);
            lvGioHang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(tbGioHang);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        tbGioHang.setNavigationOnClickListener(view -> finish());
    }

    private void AddControls() {
        lvGioHang = findViewById(R.id.lvGioHang);
        txtThongBao = findViewById(R.id.txtThongBao);
        imgGioHangTrong = findViewById(R.id.imgGioHangTrong);
        txtTongTien = findViewById(R.id.txtTongTien);
        btnThanhToan = findViewById(R.id.btnThanhToanGioHang);
        btnTiepTuc = findViewById(R.id.btnTiepTucMuaHang);
        tbGioHang = findViewById(R.id.tbGioHang);
        gioHangAdapter = new GioHangAdapter(GioHangActivity.this, TrangChuActivity.arrGioHang);
        lvGioHang.setAdapter(gioHangAdapter);
    }
}