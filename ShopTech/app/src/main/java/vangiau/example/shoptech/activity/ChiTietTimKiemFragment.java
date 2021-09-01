package vangiau.example.shoptech.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import vangiau.example.shoptech.model.GioHang;
import vangiau.example.shoptech.model.HinhAnhChiTiet;


public class ChiTietTimKiemFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    String ten = "", gia ="", moTa ="", hinhAnh ="", id = "", giagoc = "", hinhCT2 = "", hinhCT3 = "";
    private String mParam1;
    private String mParam2;
    Spinner spiSoLuongHolder;

    ViewPager vpChiTiet;
    CircleIndicator ciChiTiet;
    HinhAnhChiTietAdapter hinhAnhChiTietAdapter;

    ScaleRatingBar rbDanhGiaSao;
    EditText edtNoiDungDanhGia;
    Button btnDanhGia;

    ListView lvDanhGia;
    DanhGiaAdapter danhGiaAdapter;
    ArrayList<DanhGia>  arrDanhGia;

    public ChiTietTimKiemFragment() {
        // Required empty public constructor
    }
    public ChiTietTimKiemFragment(String ten, String giagoc, String gia, String moTa, String hinhAnh, String id, String hinhCT2, String hinhCT3) {
        this.ten = ten;
        this.giagoc = giagoc;
        this.gia = gia;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.id = id;
        this.hinhCT2 = hinhCT2;
        this.hinhCT3 = hinhCT3;
    }


    public static ChiTietTimKiemFragment newInstance(String param1, String param2) {
        ChiTietTimKiemFragment fragment = new ChiTietTimKiemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_chi_tiet_thiet_bi, container, false);
        TextView txtTenHolder    = view.findViewById(R.id.txtTenChiTiet);
        TextView txtGiaHolder    = view.findViewById(R.id.txtGiaChiTiet);
        TextView txtGiaGocHolder = view.findViewById(R.id.txtGiaGocChiTiet);
        TextView txtMoTaHolder   = view.findViewById(R.id.txtMoTaChiTiet);
        BottomNavigationView navThemHolder = view.findViewById(R.id.navThemGioHang);
        spiSoLuongHolder         = view.findViewById(R.id.spiChiTiet);
        vpChiTiet                = view.findViewById(R.id.vpChiTiet);
        ciChiTiet                = view.findViewById(R.id.ciChiTiet);
        rbDanhGiaSao             = view.findViewById(R.id.rbDanhGiaSao);
        edtNoiDungDanhGia        = view.findViewById(R.id.edtNoiDungDanhGia);
        btnDanhGia               = view.findViewById(R.id.btnDanhGia);
        lvDanhGia                = view.findViewById(R.id.lvDanhGia);
        arrDanhGia               = new ArrayList<>();
        danhGiaAdapter           = new DanhGiaAdapter(getContext(), arrDanhGia);
        lvDanhGia.setAdapter(danhGiaAdapter);

        txtTenHolder.setText(ToUpper(ten));
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGiaHolder.setText("Giá : " + decimalFormat.format(Integer.parseInt(gia)) + " ₫");
        txtGiaGocHolder.setText(decimalFormat.format(Integer.parseInt(giagoc)) + " ₫");
        txtGiaGocHolder.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        txtMoTaHolder.setText(moTa);
        Integer[] soLuong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, soLuong);
        spiSoLuongHolder.setAdapter(arrayAdapter);
        navThemHolder.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        hinhAnhChiTietAdapter = new HinhAnhChiTietAdapter(getActivity(), getListPhoto());
        vpChiTiet.setAdapter(hinhAnhChiTietAdapter);
        ciChiTiet.setViewPager(vpChiTiet);
        hinhAnhChiTietAdapter.registerDataSetObserver(ciChiTiet.getDataSetObserver());

        Toolbar toolbar =view.findViewById(R.id.tbChiTiet);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view1 -> onBackPressed());

        GetDanhGiaTheoIDSanPham();
        btnDanhGia.setOnClickListener(view12 -> {
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
        return  view;
    }
    private void GetDanhGiaTheoIDSanPham() {
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
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
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, Server.pathGetDanhGia, response -> {
            int ID, idSanPham;
            String noiDung, ngayDanhGia, email;
            Double rating;
            if(response.equals("[]")){
                CheckConnect.ToastMessError(getContext(), "Lỗi kết nối server !");
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
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, Server.pathSetNoiDungDanhGia, response -> {
            if(response.equals("error")){
                CheckConnect.ToastMessError(getContext(), "Hệ thống lỗi, không thể thực hiện đánh giá");
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
                }
                else{
                    param.put("email", DangNhapActivity.emailFB);
                    param.put("avatar","https://i.imgur.com/VZwd48U.png");
                }
                param.put("hoten", TrangChuActivity.hoTen);

                param.put("idsanpham", String.valueOf(id));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private List<HinhAnhChiTiet> getListPhoto() {
        List<HinhAnhChiTiet> list = new ArrayList<>();
        list.add(new HinhAnhChiTiet(hinhAnh));
        list.add(new HinhAnhChiTiet(hinhCT2));
        list.add(new HinhAnhChiTiet(hinhCT3));
        return list;
    }

    private void XuLyThemGioHang(){
        if(TrangChuActivity.arrGioHang.size() > 0){
            int count = Integer.parseInt(spiSoLuongHolder.getSelectedItem().toString());
            boolean exists = false;
            for(int i = 0; i < TrangChuActivity.arrGioHang.size(); i++){
                if(TrangChuActivity.arrGioHang.get(i).getId() == Integer.parseInt(id)){
                    TrangChuActivity.arrGioHang.get(i).setSoLuong(TrangChuActivity.arrGioHang.get(i).getSoLuong() + count);
                    if(TrangChuActivity.arrGioHang.get(i).getSoLuong() >= 10){
                        TrangChuActivity.arrGioHang.get(i).setSoLuong(10);
                    }
                    TrangChuActivity.arrGioHang.get(i).setGia(Integer.parseInt(gia) * TrangChuActivity.arrGioHang.get(i).getSoLuong());
                    exists = true;
                }
            }
            if(!exists){
                int soLuong = Integer.parseInt(spiSoLuongHolder.getSelectedItem().toString());
                long giaMoi = soLuong * Integer.parseInt(gia);
                TrangChuActivity.arrGioHang.add(new GioHang(Integer.parseInt(id), ToUpper(ten), giaMoi, hinhAnh, soLuong));
            }
        }
        else {
            int soLuong = Integer.parseInt(spiSoLuongHolder.getSelectedItem().toString());
            long giaMoi = soLuong * Integer.parseInt(gia);
            TrangChuActivity.arrGioHang.add(new GioHang(Integer.parseInt(id), ToUpper(ten), giaMoi, hinhAnh, soLuong));
        }
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
                        Intent i = new Intent(getActivity(), ThanhToanActivity.class);
                        startActivity(i);
                        break;
                }
                return false;
    };

    public void onBackPressed()
    {
        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new TimKiemFragment()).addToBackStack(null).commit();

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
    private void openDiaLog() {
        @SuppressLint("InflateParams")
        View view = getLayoutInflater().inflate(R.layout.layout_bottom_sheet, null);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Objects.requireNonNull(getContext()));
        bottomSheetDialog.setContentView(view);

        ImageView imgHinhBottom,imgDropDown;
        TextView txtTenBottom, txtGiaBottom, txtGiaGocBottomm, txtSoLuongBottom;
        txtTenBottom = view.findViewById(R.id.txtTenBottom);
        txtGiaBottom = view.findViewById(R.id.txtGiaBottom);
        txtGiaGocBottomm = view.findViewById(R.id.txtGiaGocBottom);
        imgHinhBottom = view.findViewById(R.id.imgBottom);
        txtSoLuongBottom = view.findViewById(R.id.txtSoLuongBottom);
        imgDropDown = view.findViewById(R.id.imgDropDown);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTenBottom.setMaxLines(2);
        txtTenBottom.setEllipsize(TextUtils.TruncateAt.END);
        txtTenBottom.setText(ToUpper(ten));
        txtGiaGocBottomm.setText(decimalFormat.format(Integer.parseInt(giagoc)) + " ₫");
        txtGiaGocBottomm.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        txtGiaBottom.setText("Giá : " + decimalFormat.format(Integer.parseInt(gia)) + " ₫");
        txtSoLuongBottom.setText("Số Lượng: " + spiSoLuongHolder.getSelectedItem().toString());
        Picasso.with(getActivity()).load(hinhAnh).placeholder(R.drawable.no_image_ic).error(R.drawable.error_ic).into(imgHinhBottom);
        bottomSheetDialog.show();

        imgDropDown.setOnClickListener(view1 -> bottomSheetDialog.dismiss());

        Button btnXemGioHang = view.findViewById(R.id.btnXemGioBottom);
        btnXemGioHang.setOnClickListener(view12 -> {
            Intent intent = new Intent(getActivity(), GioHangActivity.class);
            startActivity(intent);
            bottomSheetDialog.dismiss();
        });
    }
}