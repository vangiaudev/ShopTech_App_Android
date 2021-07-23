package vangiau.example.shoptech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import vangiau.example.shoptech.R;
import vangiau.example.shoptech.adapter.LichSuMuaHangAdapter;
import vangiau.example.shoptech.general.Server;
import vangiau.example.shoptech.model.ChiTietDonHang;

public class LichSuMuaHangActivity extends AppCompatActivity {
    Toolbar                     tbLichSuMuaHang;
    ListView                    lvLichSuMuaHang;
    TextView                    txtThongBaoLS;
    ImageView                   imgGioHangTrongLS;
    LichSuMuaHangAdapter        lichSuMuaHangAdapter;
    ArrayList<ChiTietDonHang>   arrLichSuMuaHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_mua_hang);
        AddControls();
        ActionBar();
        GetData();
    }

    private void GetData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, Server.pathLichSuMuaHang, response -> {
            int ID, maDonHang, maThietBi, giaThietBi, soLuongThietBi;
            String tenThietBi, ngayDat, emailDat;

            if(response.equals("[]")){
                txtThongBaoLS.setVisibility(View.VISIBLE);
                imgGioHangTrongLS.setVisibility(View.VISIBLE);
            }
            else {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i< jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ID              = jsonObject.getInt("id");
                        maDonHang       = jsonObject.getInt("madonhang");
                        maThietBi       = jsonObject.getInt("mathietbi");
                        tenThietBi      = jsonObject.getString("tenthietbi");
                        giaThietBi      = jsonObject.getInt("giathietbi");
                        soLuongThietBi  = jsonObject.getInt("soluongthietbi");
                        ngayDat         = jsonObject.getString("ngaydat");
                        emailDat        = jsonObject.getString("emaildat");
                        arrLichSuMuaHang.add(new ChiTietDonHang(ID, maDonHang, maThietBi, tenThietBi, giaThietBi, soLuongThietBi, ngayDat, emailDat));
                        lichSuMuaHangAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> {

        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<String, String>();
                if(!DangNhapActivity.edtEmail.getText().toString().equals("")){
                    param.put("emaildat", DangNhapActivity.edtEmail.getText().toString());
                }
                else{
                    param.put("emaildat", DangNhapActivity.emailFB.trim());
                }
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ActionBar() {
        setSupportActionBar(tbLichSuMuaHang);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        tbLichSuMuaHang.setNavigationOnClickListener(view -> finish());
    }

    private void AddControls() {
        imgGioHangTrongLS           = findViewById(R.id.imgGioHangTrongLS);
        txtThongBaoLS               = findViewById(R.id.txtThongBaoLS);
        tbLichSuMuaHang             = findViewById(R.id.tbLichSu);
        lvLichSuMuaHang             = findViewById(R.id.lvLichSu);
        arrLichSuMuaHang            = new ArrayList<>();
        lichSuMuaHangAdapter        = new LichSuMuaHangAdapter(getApplicationContext(), arrLichSuMuaHang);
        lvLichSuMuaHang.setAdapter(lichSuMuaHangAdapter);
    }

}