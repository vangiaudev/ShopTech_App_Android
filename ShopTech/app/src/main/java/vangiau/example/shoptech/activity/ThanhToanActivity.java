package vangiau.example.shoptech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import vangiau.example.shoptech.R;
import vangiau.example.shoptech.general.CheckConnect;
import vangiau.example.shoptech.general.ProgressButton;
import vangiau.example.shoptech.general.Server;

public class ThanhToanActivity extends AppCompatActivity {

    Toolbar     tbThanhToan;
    EditText    edtTenKH, edtDiaChi, edtSDT;
    CheckBox    cbCamKet;
    RadioButton radNhanHang, radPaypal;
    View        viewDatHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        AddControls();
        AddEvent();
        ActionBar();
        radPaypal.setOnClickListener(view -> radNhanHang.setChecked(false));
        radNhanHang.setOnClickListener(view -> radPaypal.setChecked(false));

    }

    private void AddEvent() {

        if (CheckConnect.haveNetworkConnection(getApplicationContext())){
            EventButton();
        }
        else {
            CheckConnect.ToastMessWarning(getApplicationContext(), "Lỗi kết nối");
        }
    }

    private void EventButton() {
        viewDatHang.setOnClickListener(view -> {
            String ten    = edtTenKH.getText().toString().trim();
            String sdt    = edtSDT.getText().toString().trim();
            String diachi = edtDiaChi.getText().toString().trim();
            if(ten.length() > 0 && sdt.length() > 0 && diachi.length() > 0)
            {
                if(radNhanHang.isChecked()) {
                    if (cbCamKet.isChecked()) {
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathDonHang, madonhang -> {

                            if (Integer.parseInt(madonhang) > 0) {
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(Request.Method.POST, Server.pathChiTietDonHang, response -> {
                                    if (response.equals("1")) {
                                        ProgressButton progressButton = new ProgressButton(ThanhToanActivity.this, viewDatHang);
                                        progressButton.buttonActivatedDatHang();
                                        Handler handler = new Handler();
                                        handler.postDelayed(() -> {
                                            progressButton.buttonFinishedDatHang();
                                            TrangChuActivity.arrGioHang.clear();
                                            openDialog();
                                        }, 3000);
                                    } else {
                                        CheckConnect.ToastMessError(getApplicationContext(), "Dữ liệu giỏ hàng của bạn đã bị lỗi");
                                    }
                                }, error -> {

                                }) {
                                    @Override
                                    protected Map<String, String> getParams() {
                                        JSONArray jsonArray = new JSONArray();
                                        String madh = null;
                                        int matb = 0;
                                        StringBuilder tentb = new StringBuilder();
                                        long giatb = 0;
                                        int soluongtb = 0;
                                        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                                        for (int i = 0; i < TrangChuActivity.arrGioHang.size(); i++) {
                                            madh = madonhang;
                                            matb = TrangChuActivity.arrGioHang.get(i).getId();
                                            tentb.append(TrangChuActivity.arrGioHang.get(i).getTen()).append("\n");
                                            giatb += TrangChuActivity.arrGioHang.get(i).getGia();
                                            soluongtb += TrangChuActivity.arrGioHang.get(i).getSoLuong();
                                        }
                                        JSONObject jsonObject = new JSONObject();
                                        try {
                                            jsonObject.put("madonhang", madh);
                                            jsonObject.put("mathietbi", matb);
                                            jsonObject.put("tenthietbi", tentb.toString());
                                            jsonObject.put("giathietbi", giatb);
                                            jsonObject.put("soluongthietbi", soluongtb);
                                            jsonObject.put("ngaydat", currentDate);
                                            if(DangNhapActivity.edtEmail.getText().toString().trim().equals("")){
                                                jsonObject.put("emaildat", DangNhapActivity.emailFB.trim());
                                            }
                                            else {
                                                jsonObject.put("emaildat", DangNhapActivity.edtEmail.getText().toString().trim());
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        jsonArray.put(jsonObject);
                                        HashMap<String, String> hashMap = new HashMap<>();
                                        hashMap.put("json", jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
                            }
                        }, error -> {

                        }) {
                            @Override
                            protected Map<String, String> getParams() {
                                HashMap<String, String> param = new HashMap<>();
                                param.put("tenkhanhhang", ten);
                                param.put("sodienthoai", sdt);
                                param.put("diachi", diachi);
                                return param;
                            }
                        };
                        requestQueue.add(stringRequest);
                    } else {
                        CheckConnect.ToastMessWarning(getApplicationContext(), "Bạn chưa đồng ý với điều khoản");
                    }
                } else if(radPaypal.isChecked()){
                    Intent intent = new Intent(ThanhToanActivity.this, PaypalActivity.class);
                    startActivity(intent);
                }
                else {
                    CheckConnect.ToastMessWarning(getApplicationContext(), "Bạn chưa chọn phương thức thanh toán");
                }
            }else {
                CheckConnect.ToastMessError(getApplicationContext(), "Bạn chưa nhập đầy đủ thông tin");
            }

        });
    }
    private void ActionBar() {
        setSupportActionBar(tbThanhToan);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        tbThanhToan.setNavigationOnClickListener(view -> finish());
    }

    private void openDialog(){
        final Dialog dialog = new Dialog(ThanhToanActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_dialog_dathang);

        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        AppCompatButton btnOK = (AppCompatButton) dialog.findViewById(R.id.btnOK);
        AppCompatButton btnXemChiTiet = dialog.findViewById(R.id.btnXemChiTiet);

        btnOK.setOnClickListener(v -> {
            dialog.dismiss();
            Intent intent = new Intent(ThanhToanActivity.this, TrangChuActivity.class);
            startActivity(intent);
            CheckConnect.ToastMessSuccess(getApplicationContext(), "Mời bạn tiếp tục mua hàng");
        });

        btnXemChiTiet.setOnClickListener(view -> {
            Intent intent = new Intent(ThanhToanActivity.this, ChiTietDonHangActivity.class);
            startActivity(intent);
        });

        dialog.show();
    }

    private void AddControls() {
        edtTenKH    = findViewById(R.id.edtTenKhachHang);
        edtDiaChi   = findViewById(R.id.edtDiaChiKhachHang);
        edtSDT      = findViewById(R.id.edtSoDienThoai);
        cbCamKet    = findViewById(R.id.cbCamKet);
        tbThanhToan = findViewById(R.id.tbThanhToan);
        radNhanHang = findViewById(R.id.radNhanHang);
        radPaypal   = findViewById(R.id.radPaypal);
        viewDatHang = findViewById(R.id.pbtnDatHang);
    }
}