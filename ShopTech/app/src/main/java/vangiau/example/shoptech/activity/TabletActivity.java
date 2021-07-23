package vangiau.example.shoptech.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

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
import vangiau.example.shoptech.adapter.TabletAdapter;
import vangiau.example.shoptech.general.CheckConnect;
import vangiau.example.shoptech.general.Server;
import vangiau.example.shoptech.model.ThietBi;

public class TabletActivity extends AppCompatActivity {

    Toolbar             tbTablet;
    ListView            lvTablet;
    TabletAdapter       tabletAdapter;
    ArrayList<ThietBi>  arrTablet;
    View                footerView;
    MyHandler           myHandler;
    boolean             isLoading, limitData;
    Spinner             spiSapXepTablet;
    int                 ID_TL = 3;
    int                 PAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablet);
        AddControls();
        CatchEventSpinner();
        ActionBar();
        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
            spiSapXepTablet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(spiSapXepTablet.getSelectedItem().toString().equals("Giá cao đến thấp")){
                        isLoading = false;
                        limitData = false;
                        ID_TL = 3;
                        PAGE = 1;
                        AddControls();
                        GetIDLoaiThietBi();
                        GetGiaGiamDan(PAGE);
                        LoadMoreData();
                    }
                    else if(spiSapXepTablet.getSelectedItem().toString().equals("Giá thấp đến cao")){
                        isLoading = false;
                        limitData = false;
                        ID_TL = 3;
                        PAGE = 1;
                        AddControls();
                        GetIDLoaiThietBi();
                        GetGiaTangDan(PAGE);
                        LoadMoreData();
                    }
                    else{
                        GetIDLoaiThietBi();
                        GetData(PAGE);
                        LoadMoreData();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        else {
            CheckConnect.ToastMessWarning(getApplicationContext(), "Lỗi kết nối");
            finish();
        }
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

    private void LoadMoreData() {
        lvTablet.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getApplicationContext(), ChiTietThietBiActivity.class);
            intent.putExtra("thongtinthietbi", arrTablet.get(i));
            startActivity(intent);
        });
        lvTablet.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }
            @Override
            public void onScroll(AbsListView absListView, int firstItem, int visiableItem, int totalItem) {
                if(firstItem + visiableItem == totalItem && totalItem != 0 && !isLoading && !limitData){
                    isLoading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();
                }
            }
        });
    }


    private void GetData(int page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, Server.pathDienThoai + page, response -> {
            int ID, giaDT, giaGocDT, idDT;
            String tenDT, hinhDT, hinhCT2, hinhCT3, moTaDT;
            if(response != null && response.length() != 2){
                lvTablet.removeFooterView(footerView);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i< jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ID       = jsonObject.getInt("id");
                        tenDT    = jsonObject.getString("tenthietbi");
                        giaGocDT = jsonObject.getInt("giagoc");
                        giaDT    = jsonObject.getInt("giatri");
                        hinhDT   = jsonObject.getString("hinhanhthietbi");
                        moTaDT   = jsonObject.getString("mota");
                        idDT     = jsonObject.getInt("idthietbi");
                        hinhCT2  = jsonObject.getString("hinhanhchitiet2");
                        hinhCT3  = jsonObject.getString("hinhanhchitiet3");
                        arrTablet.add(new ThietBi(ID, tenDT,giaGocDT, giaDT, hinhDT, moTaDT, idDT, hinhCT2, hinhCT3));
                        tabletAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                limitData = true;
                lvTablet.removeFooterView(footerView);
                CheckConnect.ToastMessConfusing(getApplicationContext(), "Đã hết dữ liệu");
            }

        }, error -> {

        }){
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> param = new HashMap<>();
                param.put("idthietbi", String.valueOf(3));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ActionBar() {
        setSupportActionBar(tbTablet);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        tbTablet.setNavigationOnClickListener(view -> finish());
    }

    private void GetIDLoaiThietBi() {
        ID_TL = getIntent().getIntExtra("idloaithietbi", -1);
    }

    @SuppressLint("InflateParams")
    private void AddControls() {
        tbTablet                = findViewById(R.id.tbTablet);
        lvTablet                = findViewById(R.id.lvTablet);
        spiSapXepTablet         = findViewById(R.id.spiSapXepTablet);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerView              = inflater.inflate(R.layout.progressbar, null);
        myHandler               = new MyHandler();
        arrTablet               = new ArrayList<>();
        tabletAdapter           = new TabletAdapter(getApplicationContext(), arrTablet);
        lvTablet.setAdapter(tabletAdapter);

    }

    @SuppressLint("HandlerLeak")
    public class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    lvTablet.addFooterView(footerView);
                    break;
                case 1:
                    if(spiSapXepTablet.getSelectedItem().toString().equals("Giá thấp đến cao")){
                        GetGiaTangDan(++PAGE);
                    }
                    else if(spiSapXepTablet.getSelectedItem().toString().equals("Giá cao đến thấp")){
                        GetGiaGiamDan(++PAGE);
                    }
                    else{
                        GetData(++PAGE);
                    }
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    public class ThreadData extends Thread{
        @Override
        public void run() {
            myHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = myHandler.obtainMessage(1);
            myHandler.sendMessage(message);
            super.run();
        }
    }

    private void CatchEventSpinner() {
        String[] cachSapXep = new String[]{"Chọn cách sắp xếp", "Giá thấp đến cao", "Giá cao đến thấp"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cachSapXep);
        spiSapXepTablet.setAdapter(arrayAdapter);
    }

    private void GetGiaGiamDan(int page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, Server.pathGiaGiamDan + page, response -> {
            int ID, giaDT, giaGocDT, idDT;
            String tenDT, hinhDT, hinhCT2, hinhCT3, moTaDT;
            if(response != null && response.length() != 2){
                lvTablet.removeFooterView(footerView);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i< jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ID       = jsonObject.getInt("id");
                        tenDT    = jsonObject.getString("tenthietbi");
                        giaGocDT = jsonObject.getInt("giagoc");
                        giaDT    = jsonObject.getInt("giatri");
                        hinhDT   = jsonObject.getString("hinhanhthietbi");
                        moTaDT   = jsonObject.getString("mota");
                        idDT     = jsonObject.getInt("idthietbi");
                        hinhCT2  = jsonObject.getString("hinhanhchitiet2");
                        hinhCT3  = jsonObject.getString("hinhanhchitiet3");
                        arrTablet.add(new ThietBi(ID, tenDT,giaGocDT, giaDT, hinhDT, moTaDT, idDT, hinhCT2, hinhCT3));
                        tabletAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                limitData = true;
                lvTablet.removeFooterView(footerView);
                CheckConnect.ToastMessConfusing(getApplicationContext(), "Đã hết dữ liệu");
            }
        }, error -> {

        }){
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> param = new HashMap<>();
                param.put("idthietbi", String.valueOf(ID_TL));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void GetGiaTangDan(int page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, Server.pathGiaTangDan + page, response -> {
            int ID, giaDT, giaGocDT, idDT;
            String tenDT, hinhDT, hinhCT2, hinhCT3, moTaDT;
            if(response != null && response.length() != 2){
                lvTablet.removeFooterView(footerView);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i< jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ID       = jsonObject.getInt("id");
                        tenDT    = jsonObject.getString("tenthietbi");
                        giaGocDT = jsonObject.getInt("giagoc");
                        giaDT    = jsonObject.getInt("giatri");
                        hinhDT   = jsonObject.getString("hinhanhthietbi");
                        moTaDT   = jsonObject.getString("mota");
                        idDT     = jsonObject.getInt("idthietbi");
                        hinhCT2  = jsonObject.getString("hinhanhchitiet2");
                        hinhCT3  = jsonObject.getString("hinhanhchitiet3");
                        arrTablet.add(new ThietBi(ID, tenDT,giaGocDT, giaDT, hinhDT, moTaDT, idDT, hinhCT2, hinhCT3));
                        tabletAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                limitData = true;
                lvTablet.removeFooterView(footerView);
                CheckConnect.ToastMessConfusing(getApplicationContext(), "Đã hết dữ liệu");
            }
        }, error -> {

        }){
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> param = new HashMap<>();
                param.put("idthietbi", String.valueOf(ID_TL));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
}