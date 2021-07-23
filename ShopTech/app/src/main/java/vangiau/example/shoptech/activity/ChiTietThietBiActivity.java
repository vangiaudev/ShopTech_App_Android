package vangiau.example.shoptech.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;
import com.willy.ratingbar.ScaleRatingBar;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import me.relex.circleindicator.CircleIndicator;
import vangiau.example.shoptech.R;
import vangiau.example.shoptech.adapter.DanhGiaAdapter;
import vangiau.example.shoptech.adapter.HinhAnhChiTietAdapter;
import vangiau.example.shoptech.general.CheckConnect;
import vangiau.example.shoptech.general.Server;
import vangiau.example.shoptech.model.DanhGia;
import vangiau.example.shoptech.model.HinhAnhChiTiet;
import vangiau.example.shoptech.model.GioHang;
import vangiau.example.shoptech.model.ThietBi;

public class ChiTietThietBiActivity extends AppCompatActivity {

    Toolbar tbChiTiet;
    TextView txtTenChiTiet, txtGiaChiTiet, txtMoTaChiTiet, txtGiaGocChiTiet;
    Spinner spiChiTiet;
    BottomNavigationView navThemGioHang;

    int id = 0, giaChiTiet = 0, idThietBi = 0, giaGocChiTiet = 0;
    String tenChiTiet = "", hinhChiTiet = "", moTaChiTiet = "";

    ViewPager vpChiTiet;
    CircleIndicator ciChiTiet;
    HinhAnhChiTietAdapter hinhAnhChiTietAdapter;

    ScaleRatingBar rbDanhGiaSao;
    EditText edtNoiDungDanhGia;
    Button btnDanhGia;

    ListView lvDanhGia;
    DanhGiaAdapter danhGiaAdapter;
    ArrayList<DanhGia>  arrDanhGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_thiet_bi);

        AddControls();
        ActionToolbar();
        GetDataChiTiet();
        GetDanhGiaTheoIDSanPham();
        CatchEventSpinner();
        AddEvent();
    }

    private void AddEvent() {
        btnDanhGia.setOnClickListener(view -> {
            PostNoiDungDanhGia();
            arrDanhGia.clear();
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                LoadDanhGia();
                edtNoiDungDanhGia.getText().clear();
                edtNoiDungDanhGia.clearFocus();
                arrDanhGia.clear();
                GetDanhGiaTheoIDSanPham();
            }, 1000);
        });
    }

    private void GetDanhGiaTheoIDSanPham() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, Server.pathGetDanhGiaTheoIDSanPham, response -> {
            int ID, idSanPham;
            String noiDung, ngayDanhGia, email, hoTen, avatar;
            Double rating;
            if(!response.equals("[]")){
                try {
                    arrDanhGia.clear();
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i< jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ID              = jsonObject.getInt("id");
                        noiDung         = jsonObject.getString("noidung");
                        ngayDanhGia     = jsonObject.getString("ngaydanhgia");
                        email           = jsonObject.getString("email");
                        hoTen           = jsonObject.getString("hoten");
                        avatar          = jsonObject.getString("avatar");
                        rating          = jsonObject.getDouble("rating");
                        idSanPham       = jsonObject.getInt("idsanpham");
                        arrDanhGia.add(new DanhGia(ID, noiDung, rating, ngayDanhGia, email, hoTen, avatar, idSanPham));
                        danhGiaAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> {

        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<>();
                param.put("idsanpham", String.valueOf(id));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void LoadDanhGia() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, Server.pathGetDanhGia, response -> {
            int ID, idSanPham;
            String noiDung, ngayDanhGia, email;
            Double rating;
            if(response.equals("[]")){
                CheckConnect.ToastMessError(getApplicationContext(), "Lỗi kết nối server !");
            }
            else {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i< jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ID              = jsonObject.getInt("id");
                        noiDung         = jsonObject.getString("noidung");
                        ngayDanhGia     = jsonObject.getString("ngaydanhgia");
                        email           = jsonObject.getString("email");
                        rating          = jsonObject.getDouble("rating");
                        idSanPham       = jsonObject.getInt("idsanpham");
                        arrDanhGia.add(new DanhGia(ID, noiDung, rating, ngayDanhGia, email, TrangChuActivity.hoTen, TrangChuActivity.avatar, idSanPham));
                        danhGiaAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> {

        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<>();
                if(!DangNhapActivity.edtEmail.getText().toString().trim().equals("")){
                    param.put("email", DangNhapActivity.edtEmail.getText().toString().trim());
                }
                else{
                    param.put("email", DangNhapActivity.emailFB.trim());
                }
                param.put("idsanpham", String.valueOf(id));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void PostNoiDungDanhGia() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, Server.pathSetNoiDungDanhGia, response -> {
            if(response.equals("error")){
                CheckConnect.ToastMessError(getApplicationContext(), "Hệ thống lỗi, không thể thực hiện đánh giá");
            }
        }, error -> {

        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<>();
                double ratingValue = rbDanhGiaSao.getRating();
                String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                param.put("noidung", edtNoiDungDanhGia.getText().toString().trim());
                param.put("rating", String.valueOf(ratingValue));
                param.put("ngaydanhgia", currentDate);
                if(!DangNhapActivity.edtEmail.getText().toString().trim().equals("")){
                    param.put("email", DangNhapActivity.edtEmail.getText().toString().trim());
                    param.put("avatar", TrangChuActivity.avatar);
                    param.put("hoten", TrangChuActivity.hoTen);
                }
                else{
                    param.put("email", DangNhapActivity.emailFB);
                    param.put("avatar","https://i.imgur.com/VZwd48U.png");
                    param.put("hoten", DangNhapActivity.hoten);
                }
                param.put("idsanpham", String.valueOf(id));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private List<HinhAnhChiTiet> getListPhoto() {
        ThietBi dsThietBi = (ThietBi) getIntent().getSerializableExtra("thongtinthietbi");
        List<HinhAnhChiTiet> list = new ArrayList<>();
        list.add(new HinhAnhChiTiet(Objects.requireNonNull(dsThietBi).getHinhAnhThietBi()));
        list.add(new HinhAnhChiTiet(dsThietBi.getHinhAnhThietBi2()));
        list.add(new HinhAnhChiTiet(dsThietBi.getHinhAnhThietBi3()));
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuGioHang) {
            Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void CatchEventSpinner() {
        Integer[] soLuong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, soLuong);
        spiChiTiet.setAdapter(arrayAdapter);
    }

    @SuppressLint("SetTextI18n")
    private void GetDataChiTiet() {

        ThietBi thietBi = (ThietBi) getIntent().getSerializableExtra("thongtinthietbi");
        id            = Objects.requireNonNull(thietBi).getID();
        tenChiTiet    = thietBi.getTenThietBi();
        giaChiTiet    = thietBi.getGiaTri();
        giaGocChiTiet = thietBi.getGiaGoc();
        hinhChiTiet   = thietBi.getHinhAnhThietBi();
        moTaChiTiet   = thietBi.getMoTa();
        idThietBi     = thietBi.getIDThietBi();

        txtTenChiTiet.setText(tenChiTiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGiaChiTiet.setText("Giá : " + decimalFormat.format(giaChiTiet) + " ₫");
        txtGiaGocChiTiet.setText(decimalFormat.format(giaGocChiTiet) + " ₫");
        txtGiaGocChiTiet.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        txtMoTaChiTiet.setText(moTaChiTiet);
    }

    private void ActionToolbar() {
        setSupportActionBar(tbChiTiet);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        tbChiTiet.setNavigationOnClickListener(view -> finish());
    }

    private void XuLyThemGioHang(){
        if(TrangChuActivity.arrGioHang.size() > 0){
            int count = Integer.parseInt(spiChiTiet.getSelectedItem().toString());
            boolean exists = false;
            for(int i = 0; i < TrangChuActivity.arrGioHang.size(); i++){
                if(TrangChuActivity.arrGioHang.get(i).getId() == id){
                    TrangChuActivity.arrGioHang.get(i).setSoLuong(TrangChuActivity.arrGioHang.get(i).getSoLuong() + count);
                    if(TrangChuActivity.arrGioHang.get(i).getSoLuong() >= 10){
                        TrangChuActivity.arrGioHang.get(i).setSoLuong(10);
                    }
                    TrangChuActivity.arrGioHang.get(i).setGia(giaChiTiet * TrangChuActivity.arrGioHang.get(i).getSoLuong());
                    exists = true;
                }
            }
            if(!exists){
                int soLuong = Integer.parseInt(spiChiTiet.getSelectedItem().toString());
                long giaMoi = soLuong * giaChiTiet;
                TrangChuActivity.arrGioHang.add(new GioHang(id, tenChiTiet, giaMoi, hinhChiTiet, soLuong));
            }
        }
        else {
            int soLuong = Integer.parseInt(spiChiTiet.getSelectedItem().toString());
            long giaMoi = soLuong * giaChiTiet;
            TrangChuActivity.arrGioHang.add(new GioHang(id, tenChiTiet, giaMoi, hinhChiTiet, soLuong));
        }
    }
    @SuppressLint("SetTextI18n")
    private void openDiaLog() {
        @SuppressLint("InflateParams")
        View view = getLayoutInflater().inflate(R.layout.layout_bottom_sheet, null);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);

        ImageView imgHinhBottom,imgDropDown;
        TextView txtTenBottom, txtGiaBottom, txtGiaGocBottomm, txtSoLuongBottom;
        txtTenBottom     = view.findViewById(R.id.txtTenBottom);
        txtGiaBottom     = view.findViewById(R.id.txtGiaBottom);
        txtGiaGocBottomm = view.findViewById(R.id.txtGiaGocBottom);
        imgHinhBottom    = view.findViewById(R.id.imgBottom);
        txtSoLuongBottom = view.findViewById(R.id.txtSoLuongBottom);
        imgDropDown      = view.findViewById(R.id.imgDropDown);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTenBottom.setMaxLines(2);
        txtTenBottom.setEllipsize(TextUtils.TruncateAt.END);
        txtTenBottom.setText(tenChiTiet);
        txtGiaGocBottomm.setText(decimalFormat.format(giaGocChiTiet) + " ₫");
        txtGiaGocBottomm.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        txtGiaBottom.setText("Giá : " + decimalFormat.format(giaChiTiet) + " ₫");
        txtSoLuongBottom.setText("Số Lượng: " + spiChiTiet.getSelectedItem().toString());
        Picasso.with(getApplicationContext()).load(hinhChiTiet).placeholder(R.drawable.no_image_ic).error(R.drawable.error_ic).into(imgHinhBottom);
        bottomSheetDialog.show();

        imgDropDown.setOnClickListener(view12 -> bottomSheetDialog.dismiss());
        Button btnXemGioHang = view.findViewById(R.id.btnXemGioBottom);
        btnXemGioHang.setOnClickListener(view1 -> {
            Intent intent = new Intent(ChiTietThietBiActivity.this, GioHangActivity.class);
            startActivity(intent);
            bottomSheetDialog.dismiss();
        });
    }
    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
                    case R.id.nav_add_cart:
                        XuLyThemGioHang();
                        openDiaLog();
                        break;
                    case R.id.nav_buy_now:
                        XuLyThemGioHang();
                        Intent i = new Intent(getApplicationContext(), ThanhToanActivity.class);
                        startActivity(i);
                        break;
                }
                return false;
    };

    private void AddControls() {
        tbChiTiet        = findViewById(R.id.tbChiTiet);
        txtTenChiTiet    = findViewById(R.id.txtTenChiTiet);
        txtGiaChiTiet    = findViewById(R.id.txtGiaChiTiet);
        txtMoTaChiTiet   = findViewById(R.id.txtMoTaChiTiet);
        spiChiTiet       = findViewById(R.id.spiChiTiet);
        txtGiaGocChiTiet = findViewById(R.id.txtGiaGocChiTiet);
        rbDanhGiaSao     = findViewById(R.id.rbDanhGiaSao);
        edtNoiDungDanhGia= findViewById(R.id.edtNoiDungDanhGia);
        btnDanhGia       = findViewById(R.id.btnDanhGia);
        vpChiTiet        = findViewById(R.id.vpChiTiet);
        ciChiTiet        = findViewById(R.id.ciChiTiet);
        lvDanhGia        = findViewById(R.id.lvDanhGia);
        arrDanhGia       = new ArrayList<>();
        danhGiaAdapter   = new DanhGiaAdapter(getApplicationContext(), arrDanhGia);
        navThemGioHang   = findViewById(R.id.navThemGioHang);
        navThemGioHang.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        lvDanhGia.setAdapter(danhGiaAdapter);
        hinhAnhChiTietAdapter = new HinhAnhChiTietAdapter(this, getListPhoto());
        vpChiTiet.setAdapter(hinhAnhChiTietAdapter);
        ciChiTiet.setViewPager(vpChiTiet);
        hinhAnhChiTietAdapter.registerDataSetObserver(ciChiTiet.getDataSetObserver());
    }
}