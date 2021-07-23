package vangiau.example.shoptech.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import vangiau.example.shoptech.R;
import vangiau.example.shoptech.adapter.DanhMucAdapter;
import vangiau.example.shoptech.adapter.LoaiThietBiAdapter;
import vangiau.example.shoptech.adapter.FlashSaleAdapter;
import vangiau.example.shoptech.adapter.ThietBiAdapter;
import vangiau.example.shoptech.general.CheckConnect;
import vangiau.example.shoptech.general.Server;
import vangiau.example.shoptech.model.DanhMuc;
import vangiau.example.shoptech.model.GioHang;
import vangiau.example.shoptech.model.LoaiThietBi;
import vangiau.example.shoptech.model.ThietBi;
//
public class TrangChuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar                 tbManHinhChinh;
    ViewFlipper             vfManHinhChinh;
    RecyclerView            rvManHinhChinh, rvSanPhamMoiNhat, rvDanhMuc;
    NavigationView          nvManHinhChinh;
    DrawerLayout            dlManHinhChinh;
    TextView                txtHoTenNav, txtEmailNav;
    ArrayList<LoaiThietBi>  arrayLoaiThietBi;
    LoaiThietBiAdapter      adapterLoai;
    ArrayList<ThietBi>      arrayThietBi, arrayThietBiFS;
    List<DanhMuc>           arrayDanhMuc;
    FlashSaleAdapter        adapterThietBi;
    ThietBiAdapter          thietBiAdapterFS;
    CircleImageView         civUserNav;
    Button                  btnTimKiem;
    DanhMucAdapter          adapterDanhMuc;
    LinearLayout            llFlashSale;
    CircleImageView         icFB, icYTB, icTWI, icGIT, icINS;

    int    id             = 0;
    String tenLoaiThietBi = "";
    String hinhAnhLoai    = "";

    public  static ArrayList<GioHang> arrGioHang;
    private static final long START_TIME_IN_MILLIS = 80000000;
    private TextView mTextViewCountDown;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    public static String taiKhoanDangNhap, avatar, hoTen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
            AddControls();
            AddEvent();
            ActionBar();
            NavigationMenu();
            ActionViewFlipper();
            GetDataLoai();
            GetDataThietBiMoiNhat();
            GetDataThietBiFlashSale();
            GetAvatarUser();
            if(!DangNhapActivity.emailFB.trim().equals("")){
                GetDataLoginFacebook();
            }
        }else {
            CheckConnect.ToastMess(getApplicationContext(), "Lỗi kết nối");
            finish();
        }

        //Time Flash Sale
        if (mTimerRunning) {
            pauseTimer();
        } else {
            startTimer();
        }
        updateCountDownText();
        TimKiemChungToiTren();
    }

    private void AddEvent() {
        civUserNav.setOnClickListener(view -> {
            Intent i = new Intent(TrangChuActivity.this, NguoiDungActivity.class);
            i.putExtra("emailNguoiDung", DangNhapActivity.edtEmail.getText().toString().trim());
            startActivity(i);
        });

        btnTimKiem.setOnClickListener(view -> {
            Intent intent = new Intent(TrangChuActivity.this, TimKiemActivity.class);
            startActivity(intent);
        });
    }

    private void GetDataLoginFacebook() {
        txtHoTenNav.setText(DangNhapActivity.hoten);
        txtEmailNav.setText(DangNhapActivity.emailFB);
        Picasso.with(getApplicationContext()).load("https://i.imgur.com/VZwd48U.png").placeholder(R.drawable.ic_avatar_fb).error(R.drawable.ic_avatar_fb).into(civUserNav);
    }

    private void GetAvatarUser() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathGetNguoiDung, response -> {
            if(response != null){
                for(int i = 0; i< response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        taiKhoanDangNhap = jsonObject.getString("email");
                        avatar = jsonObject.getString("avatar");
                        hoTen  = jsonObject.getString("hoten");

                        if(taiKhoanDangNhap.equals(DangNhapActivity.edtEmail.getText().toString().trim())){
                            Picasso.with(getApplicationContext()).load(avatar).placeholder(R.drawable.ic_avatar).error(R.drawable.ic_avatar).into(civUserNav);
                            txtHoTenNav.setText(hoTen);
                            txtEmailNav.setText(taiKhoanDangNhap);
                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, error -> { });
        requestQueue.add(jsonArrayRequest);
    }

    private void TimKiemChungToiTren() {
        icFB.setOnClickListener(view -> goToURL("https://www.facebook.com/vangiau.recca"));
        icINS.setOnClickListener(view -> goToURL("https://www.instagram.com/vangiau.recca"));
        icTWI.setOnClickListener(view -> goToURL("https://twitter.com/vangiau_recca"));
        icYTB.setOnClickListener(view -> goToURL("https://www.youtube.com/channel/UCaxR1aMhRUId7JzXN3unNXQ?view_as=subscriber"));
        icGIT.setOnClickListener(view -> goToURL("https://github.com/vangiaurecca"));
    }

    private void goToURL(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
                llFlashSale.setVisibility(View.GONE);
                rvManHinhChinh.setVisibility(View.GONE);
            }
        }.start();
        mTimerRunning = true;

    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;

    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
    }

    private void updateCountDownText() {
        int hour    = (int) (mTimeLeftInMillis / 1000) / 3600;
        int minutes = (int) (mTimeLeftInMillis / 1000) % 3660 / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 3600 % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%d:%d:%d", hour,minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void NavigationMenu() {
        nvManHinhChinh.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dlManHinhChinh, tbManHinhChinh, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dlManHinhChinh.addDrawerListener(toggle);
        toggle.syncState();
        nvManHinhChinh.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if(dlManHinhChinh.isDrawerOpen(GravityCompat.START)){
            dlManHinhChinh.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuGioHang) {
            Intent intent = new Intent(TrangChuActivity.this, GioHangActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void GetDataThietBiMoiNhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathThietBiFlashSale, response -> {

            if(response != null){
                int ID;
                String tenThietBi;
                Integer giaTri, giaGoc;
                String hinhAnhThietBi, hinhAnhChiTiet2, hinhAnhChiTiet3;
                String moTa;
                int IDThietBi;

                for(int i = 0; i< response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        ID             = jsonObject.getInt("id");
                        tenThietBi     = jsonObject.getString("tenthietbi");
                        giaGoc         = jsonObject.getInt("giagoc");
                        giaTri         = jsonObject.getInt("giatri");
                        hinhAnhThietBi = jsonObject.getString("hinhanhthietbi");
                        moTa           = jsonObject.getString("mota");
                        IDThietBi      = jsonObject.getInt("idthietbi");
                        hinhAnhChiTiet2= jsonObject.getString("hinhanhchitiet2");
                        hinhAnhChiTiet3= jsonObject.getString("hinhanhchitiet3");

                        arrayThietBi.add(new ThietBi(ID, tenThietBi,giaGoc, giaTri, hinhAnhThietBi, moTa, IDThietBi, hinhAnhChiTiet2, hinhAnhChiTiet3));
                        adapterThietBi.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, error -> {

        });
        requestQueue.add(jsonArrayRequest);
    }
    private void GetDataThietBiFlashSale() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathThietBiMoiNhat, response -> {

            if(response != null){
                int ID;
                String tenThietBi;
                Integer giaTri, giaGoc;
                String hinhAnhThietBi, hinhAnhChiTiet2,hinhAnhChiTiet3;
                String moTa;
                int IDThietBi;

                for(int i = 0; i< response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        ID                    = jsonObject.getInt("id");
                        tenThietBi            = jsonObject.getString("tenthietbi");
                        giaGoc                = jsonObject.getInt("giagoc");
                        giaTri                = jsonObject.getInt("giatri");
                        hinhAnhThietBi        = jsonObject.getString("hinhanhthietbi");
                        moTa                  = jsonObject.getString("mota");
                        IDThietBi             = jsonObject.getInt("idthietbi");
                        hinhAnhChiTiet2= jsonObject.getString("hinhanhchitiet2");
                        hinhAnhChiTiet3= jsonObject.getString("hinhanhchitiet3");

                        arrayThietBiFS.add(new ThietBi(ID, tenThietBi,giaGoc, giaTri, hinhAnhThietBi, moTa, IDThietBi, hinhAnhChiTiet2, hinhAnhChiTiet3));
                        thietBiAdapterFS.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, error -> {

        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetDataLoai() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathLoaiThietBi, response -> {
            if(response != null){

                for (int i = 0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        id                    = jsonObject.getInt("id");
                        tenLoaiThietBi        = jsonObject.getString("ten");
                        hinhAnhLoai           = jsonObject.getString("hinhanh");
                        arrayLoaiThietBi.add(new LoaiThietBi(id, tenLoaiThietBi, hinhAnhLoai));
                        adapterLoai.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, error -> CheckConnect.ToastMess(getApplicationContext(), error.toString()));
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> arrQuangCao = new ArrayList<>();
        arrQuangCao.add("https://i.imgur.com/VeprSzA.png");
        arrQuangCao.add("https://i.imgur.com/ZbONzdl.png");
        arrQuangCao.add("https://i.imgur.com/kMsqIa0.png");
        arrQuangCao.add("https://i.imgur.com/oZudd4n.png");
        arrQuangCao.add("https://i.imgur.com/6fg0wsb.png");
        arrQuangCao.add("https://i.imgur.com/Amuc3yG.png");

        for(int i = 0; i <arrQuangCao.size(); i++)
        {
            ImageView imgQuangCao = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(arrQuangCao.get(i)).into(imgQuangCao); //load ảnh từ internet về
            imgQuangCao.setScaleType(ImageView.ScaleType.FIT_XY);
            vfManHinhChinh.addView(imgQuangCao);
        }
        vfManHinhChinh.setFlipInterval(5000); //khoảng thời gian chuyển đổi các hình
        vfManHinhChinh.setAutoStart(true);

        //set chuyển động cho các ảnh quảng cáo
        Animation anim_slide_in  = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation anim_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        vfManHinhChinh.setInAnimation(anim_slide_in);
        vfManHinhChinh.setOutAnimation(anim_slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(tbManHinhChinh);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        tbManHinhChinh.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        tbManHinhChinh.setNavigationOnClickListener(view -> dlManHinhChinh.openDrawer(GravityCompat.START));
    }

    private void AddControls() {
        llFlashSale      = findViewById(R.id.llFlashSale);
        tbManHinhChinh   = findViewById(R.id.tbManHinhChinh);
        vfManHinhChinh   = findViewById(R.id.vfManHinhChinh);
        rvManHinhChinh   = findViewById(R.id.rvManHinhChinh);
        nvManHinhChinh   = findViewById(R.id.nvManHinhChinh);
        dlManHinhChinh   = findViewById(R.id.dlManHinhChinh);
        btnTimKiem       = findViewById(R.id.btnTimKiem);
        rvSanPhamMoiNhat = findViewById(R.id.rvSanPhamMoiNhat);
        rvDanhMuc        = findViewById(R.id.rvDanhMuc);
        View navHeader   = nvManHinhChinh.getHeaderView(0);
        civUserNav       = navHeader.findViewById(R.id.civUserNav);
        txtHoTenNav      = navHeader.findViewById(R.id.txtHoTenNav);
        txtEmailNav      = navHeader.findViewById(R.id.txtEmailNav);
        arrayLoaiThietBi = new ArrayList<>();
        adapterLoai      = new LoaiThietBiAdapter(arrayLoaiThietBi, getApplicationContext());
        arrayThietBi     = new ArrayList<>();
        adapterThietBi   = new FlashSaleAdapter(getApplicationContext(), arrayThietBi);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvManHinhChinh.setHasFixedSize(true);
        rvManHinhChinh.setLayoutManager(layoutManager);
        rvManHinhChinh.setAdapter(adapterThietBi);

        arrayThietBiFS   = new ArrayList<>();
        thietBiAdapterFS = new ThietBiAdapter(getApplicationContext(), arrayThietBiFS);
        rvSanPhamMoiNhat.setHasFixedSize(true);
        rvSanPhamMoiNhat.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        rvSanPhamMoiNhat.setAdapter(thietBiAdapterFS);

        arrayDanhMuc     = new ArrayList<>();
        arrayDanhMuc.add(new DanhMuc("Điện thoại", R.drawable.smartphone));
        arrayDanhMuc.add(new DanhMuc("Macbook", R.drawable.macbook));
        arrayDanhMuc.add(new DanhMuc("Tablet", R.drawable.tablet));
        arrayDanhMuc.add(new DanhMuc("Đồng hồ", R.drawable.smartwatch));
        arrayDanhMuc.add(new DanhMuc("Khuyến mãi", R.drawable.news_tech));
        adapterDanhMuc   = new DanhMucAdapter(arrayDanhMuc, this);
        RecyclerView.LayoutManager layoutManagerDanhMuc = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvDanhMuc.setHasFixedSize(true);
        rvDanhMuc.setLayoutManager(layoutManagerDanhMuc);
        rvDanhMuc.setAdapter(adapterDanhMuc);

        icFB       = findViewById(R.id.imgFB);
        icYTB      = findViewById(R.id.imgYTB);
        icGIT      = findViewById(R.id.imgGIT);
        icINS      = findViewById(R.id.imgINS);
        icTWI      = findViewById(R.id.imgTWI);
        mTextViewCountDown = findViewById(R.id.txtDemNguoc);

        if(arrGioHang == null){
            arrGioHang = new ArrayList<>();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_trangchu:
                Intent i = new Intent(TrangChuActivity.this, TrangChuActivity.class);
                startActivity(i);
                dlManHinhChinh.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_dienthoai:
                if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                    Intent intent = new Intent(TrangChuActivity.this, DienThoaiActivity.class);
                    intent.putExtra("idloaithietbi", arrayLoaiThietBi.get(0).getId());
                    startActivity(intent);
                }
                else {
                    CheckConnect.ToastMess(getApplicationContext(), "Lỗi kết nối");
                }
                dlManHinhChinh.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_tablet:
                if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                    Intent intent = new Intent(TrangChuActivity.this, TabletActivity.class);
                    intent.putExtra("idloaithietbi", arrayLoaiThietBi.get(2).getId());
                    startActivity(intent);
                }
                else {
                    CheckConnect.ToastMess(getApplicationContext(), "Lỗi kết nối");
                }
                dlManHinhChinh.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_laptop:
                if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                    Intent intent = new Intent(TrangChuActivity.this, MacbookActivity.class);
                    intent.putExtra("idloaithietbi", arrayLoaiThietBi.get(1).getId());
                    startActivity(intent);
                }
                else {
                    CheckConnect.ToastMess(getApplicationContext(), "Lỗi kết nối");
                }
                dlManHinhChinh.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_dongho:
                if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                    Intent intent = new Intent(TrangChuActivity.this, DongHoActivity.class);
                    intent.putExtra("idloaithietbi", arrayLoaiThietBi.get(2).getId());
                    startActivity(intent);
                }
                else {
                    CheckConnect.ToastMess(getApplicationContext(), "Lỗi kết nối");
                }
                dlManHinhChinh.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_tintuc:
                if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                    Intent intent = new Intent(TrangChuActivity.this, TinTucActivity.class);
                    startActivity(intent);
                }
                else {
                    CheckConnect.ToastMess(getApplicationContext(), "Lỗi kết nối");
                }
                dlManHinhChinh.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_trogiup:
                if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                    Intent intent = new Intent(TrangChuActivity.this, TroGiupActivity.class);
                    startActivity(intent);
                }
                else {
                    CheckConnect.ToastMess(getApplicationContext(), "Lỗi kết nối");
                }
                dlManHinhChinh.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_thongtin:
                if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                    Intent intent = new Intent(TrangChuActivity.this, ThongTinActivity.class);
                    startActivity(intent);
                }
                else {
                    CheckConnect.ToastMess(getApplicationContext(), "Lỗi kết nối");
                }
                dlManHinhChinh.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_thoat:
                Intent intent = new Intent(getApplicationContext(), TrangChuActivity.class);
                startActivity(intent);

                // Tao su kien ket thuc app
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
        }
        return true;
    }

}