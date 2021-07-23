package vangiau.example.shoptech.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.material.textfield.TextInputEditText;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import vangiau.example.shoptech.R;
import vangiau.example.shoptech.general.CheckConnect;
import vangiau.example.shoptech.general.ProgressButton;

public class DangNhapActivity extends AppCompatActivity {

    ProfilePictureView   ppFacebook;
    CallbackManager      callbackManager;
    public static String emailFB = "", ten = "", id = "", hoten = "";

    @SuppressLint("StaticFieldLeak")
    public static       EditText edtEmail, edtPassword;


    LoginButton         lbtnDangKy, lbtnDangNhap;
    TabHost             tabHost;

    TextInputEditText   tieHoTen, tieEmail, tiePass, tieNhapLaiPass;
    TextView            txtDieuKhoanBaoMat;
    SwitchCompat        scpNhanKhuyenMai;
    CheckBox            cbChapNhanDieuKhoan;
    View                viewLogin, viewSignin;
    //    FirebaseAuth        mAuth;
    //    mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_dang_nhap);


        AddControl();
        SetUpTab();
        viewSignin.setOnClickListener(view -> DangKyTaiKhoan());
        viewLogin.setOnClickListener(view -> DangNhapTaiKhoan());
        XuLyButtonFacebook(lbtnDangNhap);

        txtDieuKhoanBaoMat.setOnClickListener(view -> {
            Intent intent = new Intent(DangNhapActivity.this, DieuKhoanBaoMatActivity.class);
            startActivity(intent);
        });
    }

    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    private void DangNhapTaiKhoan() {
        emailFB = "";
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        if(email.equals("") || password.equals("")){
            CheckConnect.ToastMessError(getApplicationContext(), "Bạn chưa nhập thông tin tài khoản");
        }
        else {
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("http://vangiaurecca.000webhostapp.com/server/getNguoiDung.php", response -> {

                if(response != null){
                    String taiKhoanDangNhap;
                    String matKhauDangNhap;
                    boolean taiKhoanHopLe = false;

                    for(int i = 0; i< response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            taiKhoanDangNhap = jsonObject.getString("email");
                            matKhauDangNhap = jsonObject.getString("matkhau");

                            if(taiKhoanDangNhap.equals(email) && matKhauDangNhap.equals(password)){
                                taiKhoanHopLe = true;
                                break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    if(taiKhoanHopLe){
                        ProgressButton progressButton = new ProgressButton(DangNhapActivity.this, viewLogin);
                        progressButton.buttonActivatedLogin();
                        Handler handler = new Handler();
                        handler.postDelayed(() -> {
                            progressButton.buttonFinishedLogin();
                            CheckConnect.ToastMessSuccess(getApplicationContext(), "Đăng nhập thành công");
                            Intent intent = new Intent(DangNhapActivity.this,TrangChuActivity.class);
                            startActivity(intent);
                        }, 3000);
                    }
                    else {
                        CheckConnect.ToastMessError(getApplicationContext(), "Đăng nhập thất bại");
                    }
                }
            }, error -> {
            });
            requestQueue.add(jsonArrayRequest);
        }
    }

    private void DangKyTaiKhoan() {
        String email = Objects.requireNonNull(tieEmail.getText()).toString().trim();
        String password = Objects.requireNonNull(tiePass.getText()).toString().trim();
        String passwordConfirm = Objects.requireNonNull(tieNhapLaiPass.getText()).toString().trim();
        String hoTen = Objects.requireNonNull(tieHoTen.getText()).toString().trim();
        if(isValidEmailId(email)){
            if (cbChapNhanDieuKhoan.isChecked()) {
                if(password.length() >= 6){
                    if(password.equals(passwordConfirm)) {
                        if (email.equals("") || hoTen.equals("")) {
                            CheckConnect.ToastMessError(getApplicationContext(), "Bạn chưa nhập thông tin tài khoản");
                        } else {
                            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://vangiaurecca.000webhostapp.com/server/nguoidung.php", response -> {
                                if (!response.equals("error")) {
                                    ProgressButton progressButton = new ProgressButton(getApplicationContext(), viewSignin);
                                    progressButton.buttonActivatedSignin();
                                    Handler handler = new Handler();
                                    handler.postDelayed(() -> {
                                        progressButton.buttonFinishedSignin();
                                        CheckConnect.ToastMessSuccess(getApplicationContext(), "Đăng ký tài khoản thành công");
                                    }, 4000);

                                } else {
                                    CheckConnect.ToastMessError(getApplicationContext(), "Địa chỉ email đã tồn tại");
                                }
                            }, error -> {
                            }) {
                                @Override
                                protected Map<String, String> getParams() {
                                    HashMap<String, String> param = new HashMap<>();
                                    param.put("email", email);
                                    param.put("matkhau", password);
                                    param.put("hoten", hoTen);
                                    param.put("diachi", "Chưa cập nhật");
                                    param.put("avatar", "http://i.imgur.com/091BhBN.png");
                                    return param;
                                }
                            };
                            requestQueue.add(stringRequest);
                        }
                    }
                    else {
                        CheckConnect.ToastMessError(getApplicationContext(), "Mật khẩu bạn nhập không trùng khớp");
                    }
                }
                else {
                    CheckConnect.ToastMessError(getApplicationContext(), "Mật khẩu bạn nhập phải ít nhất 6 ký tự");
                }
            }
            else {
                CheckConnect.ToastMessWarning(getApplicationContext(), "Bạn chưa đồng ý với điều khoản bảo mật");
            }
        }
        else {
            CheckConnect.ToastMessWarning(getApplicationContext(), "Vui lòng nhập đúng định dạng Email");
        }

    }

    @SuppressLint("PackageManagerGetSignatures")
    private void XuLyButtonFacebook(LoginButton lbtnFacebook) {
        lbtnFacebook.setReadPermissions(Arrays.asList("public_profile", "email"));
        setLogin_Button(lbtnFacebook);
        try {
            PackageInfo info = null;
            try {
                info = getPackageManager().getPackageInfo(
                        "vangiau.example.shoptech",
                        PackageManager.GET_SIGNATURES);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            for (Signature signature : Objects.requireNonNull(info).signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
            }
        } catch (NoSuchAlgorithmException ignored) {

        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void SetUpTab() {
        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setContent(R.id.tabDangNhap);
        tab1.setIndicator("Đăng Nhập", getDrawable(R.drawable.ic_clear_black_24dp));
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tabDangKy);
        tab2.setIndicator("Đăng Ký", getDrawable(R.drawable.ic_check_black_24dp));
        tabHost.addTab(tab2);

        lbtnDangNhap.setTranslationY(300);
        tabHost.setTranslationY(300);
        lbtnDangNhap.setAlpha(0);
        tabHost.setAlpha(0);
        lbtnDangNhap.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        tabHost.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();

        tabHost.setOnTabChangedListener(s -> {
            if(s.equalsIgnoreCase("t1"))
            {
                XuLyButtonFacebook(lbtnDangNhap);
            }
            else if(s.equalsIgnoreCase("t2"))
            {
                XuLyButtonFacebook(lbtnDangKy);
            }
        });
    }


    private void setLogin_Button(LoginButton lbtnFacebook) {
        lbtnFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                lbtnFacebook.setVisibility(View.INVISIBLE);
                result();
            }
            @Override
            public void onCancel() {

            }
            @Override
            public void onError(FacebookException error) {
                CheckConnect.ToastMessError(getApplicationContext(), "Lỗi kết nối");
            }
        });
    }

    private void result() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), (object, response) -> {
            try {
                edtEmail.setText("");
                edtPassword.setText("");

                emailFB   = object.getString("email");
                hoten     = object.getString("name");
                id        = object.getString("id");
                ten       = object.getString("first_name");

                Intent i = new Intent(DangNhapActivity.this, TrangChuActivity.class);
                i.putExtra("email", emailFB);
                i.putExtra("hoten", hoten);
                i.putExtra("id", id);
                i.putExtra("ten", ten);
                startActivity(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,first_name");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    private void AddControl() {
        edtEmail            = findViewById(R.id.edtEmailLogin);
        edtPassword         = findViewById(R.id.edtPasswordLogin);
        ppFacebook          = findViewById(R.id.ppFacebook);
        lbtnDangNhap        = findViewById(R.id.lbtnDangNhapFB);
        lbtnDangKy          = findViewById(R.id.lbtnDangKyFB);
        tabHost             = findViewById(R.id.tabHost);
        tabHost.setup();
        tieHoTen            = findViewById(R.id.tieHoTen);
        tieEmail            = findViewById(R.id.tieEmail);
        tiePass             = findViewById(R.id.tiePass);
        tieNhapLaiPass      = findViewById(R.id.tieNhapLaiPass);
        txtDieuKhoanBaoMat  = findViewById(R.id.txtDieuKhoanBaoMat);
        scpNhanKhuyenMai    = findViewById(R.id.scpNhanKhuyenMai);
        cbChapNhanDieuKhoan = findViewById(R.id.cbChapNhanDieuKhoan);
        viewLogin           = findViewById(R.id.pbtnDangNhap);
        viewSignin          = findViewById(R.id.pbtnDangKy);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode, data);
    }

    @Override
    protected void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), DangNhapActivity.class);
        startActivity(intent);

        // Tao su kien ket thuc app
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startActivity(startMain);
        finish();
    }
    /* Đăng Nhập với AUTHENCATION FIREBASE */
//    private void DangNhap() {
//        String email = edtEmail.getText().toString().trim();
//        String password = edtPassword.getText().toString().trim();
//        if(email.equals("") || password.equals("")){
//            CheckConnect.ToastMessError(getApplicationContext(), "Bạn chưa nhập thông tin tài khoản");
//        }
//        else {
//            mAuth.signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if(task.isSuccessful()){
//                                ProgressButton progressButton = new ProgressButton(DangNhapActivity.this, viewLogin);
//                                progressButton.buttonActivatedLogin();
//                                Handler handler = new Handler();
//                                handler.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        progressButton.buttonFinishedLogin();
//                                        CheckConnect.ToastMessSuccess(getApplicationContext(), "Đăng nhập thành công");
//                                        emailFB = email;
//                                        Intent intent = new Intent(DangNhapActivity.this,TrangChuActivity.class);
//                                        intent.putExtra("email", emailFB);
//                                        startActivity(intent);
//                                    }
//                                }, 3000);
//                            }
//                            else {
//                                CheckConnect.ToastMessError(getApplicationContext(), "Đăng nhập thất bại");
//                            }
//                        }
//                    });
//        }
//
//    }

//    Đăng Ký với AUTHENCATION FIREBASE
//    private void DangKy() {
//        String email = tieEmail.getText().toString().trim();
//        String password = tiePass.getText().toString().trim();
//        String passwordConfirm = tieNhapLaiPass.getText().toString().trim();
//        if (cbChapNhanDieuKhoan.isChecked()) {
//            if(password.equals(passwordConfirm)) {
//                if (email.equals("") || password.equals("")) {
//                    CheckConnect.ToastMessError(getApplicationContext(), "Bạn chưa nhập thông tin tài khoản");
//                } else {
//
//                    mAuth.createUserWithEmailAndPassword(email, password)
//                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                    if (task.isSuccessful()) {
//                                        ProgressButton progressButton = new ProgressButton(getApplicationContext(), viewSignin);
//                                        progressButton.buttonActivatedSignin();
//                                        Handler handler = new Handler();
//                                        handler.postDelayed(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                progressButton.buttonFinishedSignin();
//                                                CheckConnect.ToastMessSuccess(getApplicationContext(), "Đăng ký tài khoản thành công");
//                                            }
//                                        }, 4000);
//
//                                    } else {
//                                        CheckConnect.ToastMessError(getApplicationContext(), "Đăng ký tài khoản thất bại");
//                                    }
//                                }
//                            });
//                }
//            }
//            else {
//                CheckConnect.ToastMessError(getApplicationContext(), "Mật khẩu bạn nhập không trùng khớp");
//            }
//        }
//        else {
//            CheckConnect.ToastMessWarning(getApplicationContext(), "Bạn chưa đồng ý với điều khoản bảo mật");
//        }
//
//    }
}