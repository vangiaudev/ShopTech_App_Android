package vangiau.example.shoptech.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.login.LoginManager;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import vangiau.example.shoptech.R;
import vangiau.example.shoptech.general.CheckConnect;
import vangiau.example.shoptech.general.Server;

public class NguoiDungActivity extends AppCompatActivity {

    public static EditText  edtHoTenUser, edtEmailUser, edtMatKhauUser, edtDiaChiUser;
    public static String    avatar;
    TextView                txtHoTenUser, txtEmailUser;
    Toolbar                 tbNguoiDung;
    FirebaseStorage         storage = FirebaseStorage.getInstance();
    AppCompatButton         btnCapNhatThongTin;
    CircleImageView         imgAvatar;
    FloatingActionButton    fabDangXuat, fabLichSuMuaHang;
    FloatingActionsMenu     fabMenu;
    LinearLayout            llEmail, llDiaChi;
    String                  id;
    int                     REQUEST_CODE_CAMARA = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);

        final StorageReference storageRef = storage.getReferenceFromUrl("gs://shoptech-306114.appspot.com");

        //add control
        edtHoTenUser        = findViewById(R.id.edtHoTenUser);
        edtEmailUser        = findViewById(R.id.edtEmailUser);
        edtMatKhauUser      = findViewById(R.id.edtMatKhauUser);
        edtDiaChiUser       = findViewById(R.id.edtDiaChiUser);
        txtHoTenUser        = findViewById(R.id.txtHoTenUser);
        txtEmailUser        = findViewById(R.id.txtEmailUser);
        tbNguoiDung         = findViewById(R.id.tbNguoiDung);
        btnCapNhatThongTin  = findViewById(R.id.btnCapNhatThongTin);
        imgAvatar           = findViewById(R.id.imgAvatar);
        llEmail             = findViewById(R.id.llEmail);
        llDiaChi            = findViewById(R.id.llDiaChi);
        fabLichSuMuaHang    = findViewById(R.id.fabLichSuMuaHang);
        fabMenu             = findViewById(R.id.fabMenu);
        fabDangXuat         = findViewById(R.id.fabDangXuat);

        //add event
        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
            ActionBar();
            if(!DangNhapActivity.emailFB.trim().equals("")){
                GetDataLoginFacebook();
            }
            else {
                GetThongTinNguoiDung();
            }
        }
        else {
            CheckConnect.ToastMessWarning(getApplicationContext(), "Lỗi kết nối");
            finish();
        }

        llEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckConnect.ToastMess(getApplicationContext(), "Bạn không thể thay đổi địa chỉ email");
            }
        });

        fabDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NguoiDungActivity.this, DangNhapActivity.class);
                startActivity(intent);
                LoginManager.getInstance().logOut();
            }
        });
        fabLichSuMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NguoiDungActivity.this, LichSuMuaHangActivity.class);
                startActivity(intent);
                fabMenu.collapse();
            }
        });
        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camara, REQUEST_CODE_CAMARA);
            }
        });

        btnCapNhatThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();

                StorageReference mountainsRef = storageRef.child("image"+ calendar.getTimeInMillis() + ".png");

                // Get the data from an ImageView as bytes
                imgAvatar.setDrawingCacheEnabled(true);
                imgAvatar.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) imgAvatar.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] data = baos.toByteArray();

                UploadTask uploadTask = mountainsRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(NguoiDungActivity.this, "Lỗi !!!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //lấy link hình ảnh vừa upload
                        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if (!task.isSuccessful()) {
                                    throw task.getException();
                                }
                                // Continue with the task to get the download URL
                                return mountainsRef.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    Uri downloadUri = task.getResult(); //link hình
                                    UpdateThongTinNguoiDung(Server.pathUpdateNguoiDung, downloadUri.toString());
                                } else {
                                    // Handle failures
                                    // ...
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    private void GetDataLoginFacebook() {
        Picasso.with(getApplicationContext()).load("https://i.imgur.com/VZwd48U.png").placeholder(R.drawable.ic_avatar_fb).error(R.drawable.ic_avatar_fb).into(imgAvatar);
        txtHoTenUser.setText(DangNhapActivity.ten);
        txtEmailUser.setText("ID: " + DangNhapActivity.id);
        edtHoTenUser.setText(DangNhapActivity.hoten);
        edtEmailUser.setText(DangNhapActivity.emailFB);
        edtMatKhauUser.setText("************");

        edtHoTenUser.setFocusable(false);
        edtEmailUser.setFocusable(false);
        edtMatKhauUser.setFocusable(false);
        llDiaChi.setVisibility(View.INVISIBLE);
        btnCapNhatThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckConnect.ToastMessError(NguoiDungActivity.this, "Bạn không thể sủa đổi thông tin");
            }
        });
    }

    private void UpdateThongTinNguoiDung(String urlUpdate, String urlAvatar){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlUpdate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.trim().equals("success")){
                    CheckConnect.ToastMessSuccess(getApplicationContext(), "Cập nhật thành công");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            GetThongTinNguoiDung();
                        }
                    }, 2000);
                }
                else{
                    Toast.makeText(NguoiDungActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                CheckConnect.ToastMessError(getApplicationContext(), "Lỗi Server");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("email", edtEmailUser.getText().toString().trim());
                params.put("matkhau", edtMatKhauUser.getText().toString().trim());
                params.put("hoten", edtHoTenUser.getText().toString().trim());
                params.put("diachi", edtDiaChiUser.getText().toString().trim());
                params.put("avatar", urlAvatar);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void GetThongTinNguoiDung() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathGetNguoiDung, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if(response != null){
                    String taiKhoanDangNhap = "";
                    String matKhauDangNhap = "";
                    String hoTenDangNhap = "";
                    String diaChiDangNhap = "";
                    avatar = "";

                    for(int i = 0; i< response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            taiKhoanDangNhap = jsonObject.getString("email");
                            matKhauDangNhap = jsonObject.getString("matkhau");
                            hoTenDangNhap = jsonObject.getString("hoten");
                            diaChiDangNhap = jsonObject.getString("diachi");
                            avatar = jsonObject.getString("avatar");

                            if(taiKhoanDangNhap.equals(DangNhapActivity.edtEmail.getText().toString().trim())){
                                edtEmailUser.setText(taiKhoanDangNhap);
                                edtHoTenUser.setText(hoTenDangNhap);
                                edtMatKhauUser.setText(matKhauDangNhap);
                                edtDiaChiUser.setText(diaChiDangNhap);

                                String[] tmp = hoTenDangNhap.trim().split("\\s+");
                                txtHoTenUser.setText(tmp[tmp.length - 1] + " " + tmp[0]);
                                txtEmailUser.setText(taiKhoanDangNhap);
                                Picasso.with(getApplicationContext()).load(avatar).placeholder(R.drawable.ic_avatar).error(R.drawable.ic_avatar).into(imgAvatar);
                                break;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMARA && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgAvatar.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void ActionBar() {
        setSupportActionBar(tbNguoiDung);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbNguoiDung.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}