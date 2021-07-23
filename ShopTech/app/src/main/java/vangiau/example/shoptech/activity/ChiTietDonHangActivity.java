package vangiau.example.shoptech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import vangiau.example.shoptech.R;
import vangiau.example.shoptech.general.CheckConnect;
import vangiau.example.shoptech.general.Server;

public class ChiTietDonHangActivity extends AppCompatActivity {

    TextView txtMaDonHang, txtTenSanPhamDonHang, txtSoLuongSanPhamDonHang, txtTongTienDonHang, txtNgayDatHang;
    TextView txtTenKH, txtDiaChiKH, txtSDTKH;
    ImageButton ibtnTiepTucMuaHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);

        AddControls();
        GetDataKhachHang();
        GetDataChiTietDonHang();
        AddEvent();
    }

    private void AddEvent() {
        ibtnTiepTucMuaHang.setOnClickListener(view -> {
            Intent intent = new Intent(ChiTietDonHangActivity.this, TrangChuActivity.class);
            startActivity(intent);
            CheckConnect.ToastMessSuccess(getApplicationContext(), "Mời bạn tiếp tục mua hàng");
        });
    }

    private void AddControls() {
        txtMaDonHang             = findViewById(R.id.txtMaDonHang);
        txtTenSanPhamDonHang     = findViewById(R.id.txtTenSanPhamDonHang);
        txtSoLuongSanPhamDonHang = findViewById(R.id.txtSoLuongSanPhamDonHang);
        txtTongTienDonHang       = findViewById(R.id.txtTongTienDonHang);
        txtNgayDatHang           = findViewById(R.id.txtNgayDatHang);
        txtTenKH                 = findViewById(R.id.txtTenKH);
        txtDiaChiKH              = findViewById(R.id.txtDiaChiKH);
        txtSDTKH                 = findViewById(R.id.txtSDTKH);
        ibtnTiepTucMuaHang       = findViewById(R.id.ibtnTiepTucMuaHang);
    }

    private void GetDataKhachHang() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        @SuppressLint("SetTextI18n") JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathGetKhachHang, response -> {

            if(response != null){
                String tenKH, soDT, dcKH;
                for(int i = 0; i< response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        tenKH = jsonObject.getString("tenkhachhang");
                        soDT = jsonObject.getString("sodienthoai");
                        dcKH = jsonObject.getString("diachi");

                        txtTenKH.setText(txtTenKH.getText().toString() + tenKH);
                        txtSDTKH.setText("(+84) " + txtSDTKH.getText().toString() + soDT);
                        txtDiaChiKH.setText(txtDiaChiKH.getText().toString() + dcKH);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, error -> {

        });
        requestQueue.add(jsonArrayRequest);
    }
    private void GetDataChiTietDonHang() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        @SuppressLint("SetTextI18n")
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathGetChiTietDonHang, response -> {

            if(response != null){
                String maDonHang, tenDonHang;
                Integer giaDonHang;
                int soLuongDonHang;

                for(int i = 0; i< response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        maDonHang = jsonObject.getString("madonhang");
                        tenDonHang = jsonObject.getString("tenthietbi");
                        giaDonHang = jsonObject.getInt("giathietbi");
                        soLuongDonHang = jsonObject.getInt("soluongthietbi");

                        txtMaDonHang.setText(txtMaDonHang.getText().toString() + "HD000" + maDonHang);
                        txtTenSanPhamDonHang.setText(txtTenSanPhamDonHang.getText().toString() + tenDonHang);
                        txtSoLuongSanPhamDonHang.setText(txtSoLuongSanPhamDonHang.getText().toString() + soLuongDonHang);
                        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                        txtTongTienDonHang.setText(decimalFormat.format(giaDonHang) + "₫ " );
                        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                        txtNgayDatHang.setText(txtNgayDatHang.getText().toString() + currentDate);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, error -> {

        });
        requestQueue.add(jsonArrayRequest);
    }
}